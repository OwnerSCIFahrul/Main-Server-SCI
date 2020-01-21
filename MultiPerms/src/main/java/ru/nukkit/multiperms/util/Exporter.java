package ru.nukkit.multiperms.util;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import ru.nukkit.multiperms.MultiPermsPlugin;
import ru.nukkit.multiperms.data.Providers;
import ru.nukkit.multiperms.permissions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Exporter {

    private File file;

    public Exporter(String filename) {
        file = new File(MultiPermsPlugin.getPlugin().getDataFolder() + File.separator + filename + ".yml");
    }

    public Exporter() {
        this("import");
    }


    public boolean importPermissions(boolean overwrite) {
        if (!file.exists()) return false;
        Config cfg = new Config(file, Config.YAML);
        ConfigSection groupCfg = cfg.getSection("groups");
        ConfigSection userCfg = cfg.getSection("users");
        if (groupCfg.isEmpty() && userCfg.isEmpty()) return false;
        List<Group> groups = new ArrayList<>();

        groupCfg.getSections().entrySet().forEach(e -> {
            Node node = sectionToPass((ConfigSection) e.getValue());
            Group group = new Group(e.getKey(), node);
            ConfigSection worlds = cfg.getSection("worlds");
            for (String world : worlds.getKeys(false)) {
                ConfigSection ws = worlds.getSection(world);
                if (ws.isEmpty()) continue;
                Node wpass = sectionToPass(ws);
                group.setWorldPass(world, wpass);
            }
            groups.add(group);
        });

        List<User> users = new ArrayList<>();
        userCfg.getSections().entrySet().forEach(e -> {
            Node node = sectionToPass((ConfigSection) e.getValue());
            User user = new User(e.getKey(), node);
            ConfigSection worlds = cfg.getSection("worlds");
            for (String world : worlds.getKeys(false)) {
                ConfigSection ws = worlds.getSection(world);
                if (ws.isEmpty()) continue;
                Node wpass = sectionToPass(ws);
                user.setWorldPass(world, wpass);
            }
            users.add(user);
        });
        if (groups.isEmpty() && users.isEmpty()) return false;
        Groups.updateGroups(groups, overwrite);
        Users.updateUsers(users, overwrite);
        Providers.saveGroups();
        Providers.saveUsers(users);
        return true;
    }


    public boolean exportPermissions() {
        ConfigSection groupSection = new ConfigSection();
        Groups.getAll().forEach(g -> {
            ConfigSection group = passToSection(g);
            ConfigSection worlds = new ConfigSection();
            g.getWorldPass().entrySet().forEach(e -> {
                worlds.set(e.getKey(), passToSection(e.getValue()));
            });
            if (!worlds.isEmpty()) group.set("worlds", worlds);
            groupSection.set(g.getName(), group);
        });

        ConfigSection userSection = new ConfigSection();
        Providers.getCurrentProvider().getAllUsers().forEach(u -> {
            ConfigSection users = passToSection(u);
            ConfigSection worlds = new ConfigSection();
            u.getWorldPass().entrySet().forEach(e -> {
                worlds.set(e.getKey(), passToSection(e.getValue()));
            });
            if (!worlds.isEmpty()) users.set("worlds", worlds);
            userSection.set(u.getName(), users);
        });
        if (groupSection.isEmpty() && userSection.isEmpty()) return false;
        if (!file.exists()) file.delete();
        Config cfg = new Config();
        if (!groupSection.isEmpty()) cfg.set("groups", groupSection);
        if (!userSection.isEmpty()) cfg.set("users", userSection);
        cfg.save(file);
        return true;
    }


    private ConfigSection passToSection(Node node) {
        ConfigSection section = new ConfigSection();
        section.set("groups", node.getGroupList());
        section.set("permissions", node.getPermissionList());
        section.set("prefix", node.getPrefix());
        section.set("suffix", node.getSuffix());
        section.set("priority", node.getPriority());
        return section;
    }

    private Node sectionToPass(ConfigSection section) {
        Node node = new Node();
        node.setGroupList(section.getStringList("groups"));
        node.setPermissionsList(section.getStringList("permissions"));
        node.setPrefix(section.getString("prefix", ""));
        node.setSuffix(section.getString("suffix", ""));
        node.setPriority(section.getInt("priority", 0));
        return node;
    }

}
