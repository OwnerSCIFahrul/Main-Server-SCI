package Fahrul8676.motd;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityLevelChangeEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.level.Level;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
import Fahrul8676.motd.tasks.ServerMotdTask;

import java.time.LocalTime;

public class MotdServer extends PluginBase implements Listener {

    public PlaceholderAPI placeholderAPI;

    public Config pluginConfig;
    public ConfigReader configReader;
    public static Config config;

    public void onEnable() {
        saveDefaultConfig();
        this.getConfig().getString("Motd").replace("%h%", String.valueOf(LocalTime.now().getHour())).replace("%m%", String.valueOf(LocalTime.now().getMinute()));
        pluginConfig = getConfig();

        configReader = new ConfigReader(this);

        try {
            this.reloadConfig();
            this.getLogger().info("Motd Konfigurasi dimuat");
            MotdServer.config = this.getConfig();
        } catch (Exception ex) {
            this.getLogger().warning("Motd Konfigurasi tidak dapat dimuat");
            ex.printStackTrace();
        }
        this.getServer().getCommandMap().register("Motd", (Command) new ReloadCommand("motdserverreload"));
        this.getLogger().info("Pesan Motd diaktifkan");

        if (pluginConfig.getBoolean("Motd.use-placeholder")) {
            if (this.getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
                this.getServer().getLogger().critical("Tidak ada PlaceholderAPI ditemukan! Silakan pasang plugin PlaceholderAPI (lihat config.yml)");
                this.getServer().getPluginManager().disablePlugin(this);
            } else {
                placeholderAPI = PlaceholderAPI.getInstance();
            }
        }

        if (this.isEnabled()) {
            if (configReader.isServerMotdEnabled()) {
                if (configReader.getServerMotdType().equals("dynamic")) {
                    this.getServer().getScheduler().scheduleRepeatingTask(new ServerMotdTask(this), configReader.getServerMotdChangeInterval() * 20);
                } else {
                    this.getServer().getNetwork().setName(TextFormat.colorize('&', placeholderAPI.translateString(configReader.getServerMotdMessages().get(0).replace("%h%", String.valueOf(LocalTime.now().getHour())).replace("%m%", String.valueOf(LocalTime.now().getMinute())))));
                }
            }

            this.getServer().getPluginManager().registerEvents(this, this);
        }
    }

    @EventHandler
    public void onDataPacketReceive(DataPacketReceiveEvent event) {
        Player player = event.getPlayer();
        if (event.getPacket() instanceof SetLocalPlayerAsInitializedPacket && configReader.isJoinMotdEnabled()) {
            if (configReader.getJoinMotdType().equals("single-world")) {
                (new NukkitRunnable() {
                    public void run() {
                        messageSender(player, configReader.getJoinMotdMessage(), configReader.getJoinMotdSubMessage());
                    }
                }).runTaskLater(this, 40);
            } else {
                String initialWorld = getServer().getProperty("level-name").toString();
                String message = pluginConfig.getString("Join-Motd.multi-world." + initialWorld + ".message");
                String subMessage = pluginConfig.getString("Join-Motd.multi-world." + initialWorld + ".sub-message");
                (new NukkitRunnable() {
                    public void run() {
                        messageSender(player, message, subMessage);
                    }
                }).runTaskLater(this, 40);
            }

        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityLevelChange(EntityLevelChangeEvent event) {
        Entity entity = event.getEntity();
        Level originLevel = event.getOrigin();
        Level targetLevel = event.getTarget();
        if (configReader.isJoinMotdEnabled() && configReader.getJoinMotdType().equals("multi-world")) {
            if (!(entity instanceof Player)) return;
            if (originLevel.equals(targetLevel)) return;

            Player player = (Player) entity;

            String message = pluginConfig.getString("Join-Motd.multi-world." + targetLevel.getName() + ".message");
            String subMessage = pluginConfig.getString("Join-Motd.multi-world." + targetLevel.getName() + ".sub-message");

            messageSender(player, message, subMessage);
        }
    }

    public void messageSender(Player player, String message, String subMessage) {
        if (placeholderAPI != null) {
            player.sendTitle(TextFormat.colorize('&', placeholderAPI.translateString(message, player)));
            player.setSubtitle(TextFormat.colorize('&', placeholderAPI.translateString(subMessage, player)));
        } else {
            player.sendTitle(TextFormat.colorize('&', message));
            player.setSubtitle(TextFormat.colorize('&', subMessage));
        }
    }

    public void onDisable() {
        this.getLogger().info("Pesan Motd dinonatifkan");
    }
}