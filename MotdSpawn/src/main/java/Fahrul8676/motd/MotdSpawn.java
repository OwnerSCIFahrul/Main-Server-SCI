package Fahrul8676.motd;

import Fahrul8676.motd.commands.ReloadCommand;
import cn.nukkit.command.Command;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import Fahrul8676.motd.manager.managers.player.MotdManager;
import Fahrul8676.motd.manager.managers.player.MessagesManager;
import Fahrul8676.motd.manager.managers.utils.PlaceholderManager;
import Fahrul8676.motd.manager.managers.utils.FileManager;

public class MotdSpawn extends PluginBase implements Listener {
    private static MotdSpawn instance;
    private FileManager fileManager;
    private PlaceholderManager placeholderManager;
    private MessagesManager messagesManager;
    private MotdManager motdManager;

    public static Config config;

    public void onEnable() {
        (MotdSpawn.instance = this).saveDefaultConfig();
        this.fileManager = new FileManager();
        this.placeholderManager = new PlaceholderManager();
        this.messagesManager = new MessagesManager();
        this.motdManager = new MotdManager();
        this.fileManager.onEnable(this);
        this.placeholderManager.onEnable(this);
        this.messagesManager.onEnable(this);
        this.motdManager.onEnable(this);
        try {
            this.reloadConfig();
            this.getLogger().info("Motd Konfigurasi dimuat");
            MotdSpawn.config = this.getConfig();
        } catch (Exception ex) {
            this.getLogger().warning("Motd Konfigurasi tidak dapat dimuat");
            ex.printStackTrace();
        }
        this.getServer().getCommandMap().register("Motd", (Command) new ReloadCommand("motdspawnreload"));
        this.getLogger().info("Pesan Motd diaktifkan");
    }

    @Override
    public void onDisable() {
        this.motdManager.onDisable(this);
        this.messagesManager.onDisable(this);
        this.placeholderManager.onDisable(this);
        this.fileManager.onDisable(this);

        this.getLogger().info("Pesan Motd dinonatifkan");
    }

    public void register(final Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, (Plugin)this);
    }

    public static MotdSpawn getInstance() {
        return MotdSpawn.instance;
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public PlaceholderManager getPlaceholderManager() {
        return this.placeholderManager;
    }

    public MotdManager getMotdManager() {
        return this.motdManager;
    }
}
