package discordchat;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

public class Main extends PluginBase {

    public boolean debug;

    public static JDA jda;
    public static Guild server;
    public static TextChannel channel;
    public static Config config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        debug = config.getBoolean("debug", true);
        if (debug) getServer().getLogger().info("Mendaftarkan acara untuk PlayerListener");
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        try {
            if (debug) getServer().getLogger().info("Masuk dengan token robot " + config.getString("botToken", "\u00A7cbatal"));
            jda = new JDABuilder(AccountType.BOT).setToken(config.getString("botToken")).buildBlocking();
            if (debug) getServer().getLogger().info("Tetapkan id server ke " + config.getString("serverId", "\u00A7cbatal"));
            server = jda.getGuildById(config.getString("serverId"));
            if (debug) getServer().getLogger().info("Tetapkan id saluran server ke " + config.getString("channelId", "\u00A7cbatal"));
            channel = jda.getTextChannelById(config.getString("channelId"));
            jda.addEventListener(new DiscordListener());
            jda.getPresence().setGame(Game.of(Game.GameType.DEFAULT, config.getString("botStatus")));
        } catch (Exception e) {
            getLogger().error("Tidak dapat mengaktifkan Sinkronisasi obrolan Discord");
            if (debug) e.printStackTrace();
        }
        if (jda != null && config.getBoolean("startMessages")) channel.sendMessage("**✯ Server Online Kembali ✯**").queue();
        if (debug) getServer().getLogger().info("Plugin Diaktifkan");
    }

    @Override
    public void onDisable() {
        if (jda != null && config.getBoolean("stopMessages")) channel.sendMessage("**✯ Server Offline Lagi ✯**").queue();
        if (debug) getServer().getLogger().info("Plugin dinonatifkan");
    }
}