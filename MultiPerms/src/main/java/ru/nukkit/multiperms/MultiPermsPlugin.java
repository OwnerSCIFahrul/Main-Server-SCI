package ru.nukkit.multiperms;

import cn.nukkit.plugin.PluginBase;
import ru.nukkit.multiperms.command.Commander;
import ru.nukkit.multiperms.data.Providers;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Cfg;
import ru.nukkit.multiperms.util.Message;

public class MultiPermsPlugin extends PluginBase {

    private static MultiPermsPlugin instance;
    private Cfg cfg;

    public static MultiPermsPlugin getPlugin() {
        return instance;
    }

    public static Cfg getCfg() {
        return instance.cfg;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveResources();
        cfg = new Cfg();
        cfg.load();
        getServer().getPluginManager().registerEvents(new LeelooListener(), this);
        Message.init(this);
        Commander.init(this);
        Providers.init();
        Groups.init();
        reloadConfig();
        MultiPermsPlugin.getCfg().load();
        Groups.loadGroups();
        Users.reloadUsers();
    }

    private void saveResources() {
        this.getDataFolder().mkdirs();
        this.saveDefaultConfig();
        this.saveResource("groups.yml", false);
    }

}