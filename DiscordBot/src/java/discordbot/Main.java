package discordbot;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.LocalTime;

public class Main
extends PluginBase {
    static Config config;
    static JDA jda;
    static String channelId;
    static boolean debug;

    public void onEnable() {
        block18 : {
            this.saveDefaultConfig();
            config = this.getConfig();
            this.checkAndUpdateConfig();
            debug = config.getBoolean("debug");
            if (debug) {
                this.getServer().getLogger().info("Mendaftarkan acara untuk PlayerListener");
            }
            this.getServer().getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)this);
            try {
                if (debug) {
                    this.getServer().getLogger().info("Tetapkan token robot ke " + config.getString("botToken", "null"));
                }
                jda = new JDABuilder(config.getString("botToken")).build();
                if (debug) {
                    this.getServer().getLogger().info("Menunggu JDA...");
                }
                jda.awaitReady();
                if (debug) {
                    this.getServer().getLogger().info("Tetapkan id saluran server ke " + config.getString("channelId", "null"));
                }
                channelId = config.getString("channelId");
                if (debug) {
                    this.getServer().getLogger().info("Mendaftarkan acara untuk DiscordListener");
                }
                jda.addEventListener(new DiscordListener());
                if (config.getBoolean("discordConsole")) {
                    if (debug) {
                        this.getServer().getLogger().info("Mendaftarkan acara untuk DiscordConsole");
                    }
                    jda.addEventListener(new DiscordConsole());
                }
                if (debug) {
                    this.getServer().getLogger().info("Tetapkan status Robot Discord ke " + config.getString("botStatus"));
                }
                jda.getPresence().setActivity(Activity.of(Activity.ActivityType.DEFAULT, config.getString("botStatus")));
                if (!config.getString("channelTopic").isEmpty()) {
                    TextChannel ch;
                    if (debug) {
                        this.getServer().getLogger().info("Tetapkan saluran topik ke " + config.getString("channelTopic"));
                    }
                    if ((ch = jda.getTextChannelById(channelId)) != null) {
                        ch.getManager().setTopic(config.getString("channelTopic")).queue();
                    } else if (debug) {
                        this.getLogger().error("TextChannel gagal");
                    }
                }
                if (debug && jda.getGuilds().isEmpty()) {
                    this.getServer().getLogger().warning("Robot Discord kamu tidak ada di server mana pun");
                }
                if (debug) {
                    this.getServer().getLogger().info("Menjalankan berhasil dilakukan");
                }
            }
            catch (Exception e) {
                this.getLogger().error("Tidak dapat mengaktifkan Sinkronisasi Robot Discord");
                if (!debug) break block18;
                e.printStackTrace();
            }
        }
        if (config.getBoolean("startMessages")) {
            API.sendMessage(config.getString("status_server_started").replace("%time%", LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()));
        }
        if (debug) {
            this.getServer().getLogger().info("Mengaktifkan plugin");
        }
    }

    public void onDisable() {
        if (config.getBoolean("stopMessages")) {
            API.sendMessage(config.getString("status_server_stopped").replace("%time%", LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()));
        }
        if (debug) {
            this.getServer().getLogger().info("Menonaktifkan plugin");
        }
    }

    private void checkAndUpdateConfig() {
        if (config.getInt("configVersion") != 2) {
            this.saveResource("config.yml", true);
            config = this.getConfig();
            this.getLogger().warning("File konfigurasi yang lama udah diganti. kamu perlu menghapus file config.yml dan mengatur lagi.");
        }
    }
}

