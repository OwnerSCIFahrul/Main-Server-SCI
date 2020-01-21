package ru.nukkit.multiperms.data.yaml;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import ru.nukkit.multiperms.MultiPermsPlugin;
import ru.nukkit.multiperms.data.DataProvider;
import ru.nukkit.multiperms.permissions.Group;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.permissions.Node;
import ru.nukkit.multiperms.permissions.User;
import ru.nukkit.multiperms.util.Message;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class YamlSource extends DataProvider {

    private File userDir;
    private File groupFile;

    public YamlSource() {
        this.userDir = new File(MultiPermsPlugin.getPlugin().getDataFolder() + File.separator + "users");
        this.userDir.mkdirs();
        this.groupFile = new File(MultiPermsPlugin.getPlugin().getDataFolder() + File.separator + "groups.yml");
    }

    private File getUserFile(String user) {
        return new File(userDir + File.separator + user.toLowerCase() + ".yml");
    }

    @Override
    public User loadUser(String playerName) {
        Message.debugMessage("Memuat izin", playerName);
        File file = getUserFile(playerName);
        User user = new User(playerName);
        Config cfg = new Config(Config.YAML);
        if (file.exists()) {
            cfg.load(file.toString());
            Node node = sectionToPass(cfg.getRootSection());
            user = new User(playerName, node);

            ConfigSection worlds = cfg.getSection("worlds");
            for (String world : worlds.getKeys(false)) {
                ConfigSection ws = worlds.getSection(world);
                if (ws.isEmpty()) continue;
                Node wpass = sectionToPass(ws);
                user.setWorldPass(world, wpass);
            }
        }
        return user;
    }

    @Override
    public void removeUser(String playerName) {
        File file = getUserFile(playerName);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void saveUser(User user) {
        File file = getUserFile(user.getName());
        Message.debugMessage("Menyimpan file pengguna: ", file.toString());
        if (user.isEmpty()) {
            if (file.exists()) {
                file.delete();
            }
            Message.debugMessage(user.getName(), "data kosong. File dihapus");
        } else {
            Config cfg = new Config(file, Config.YAML);
            ConfigSection cfgSection = passToSection(user, true);
            cfg.setAll(cfgSection);
            ConfigSection worlds = new ConfigSection();
            user.getWorldPass().entrySet().forEach(e -> {
                worlds.set(e.getKey(), passToSection(e.getValue(), false));
            });
            cfg.set("worlds", worlds);
            Message.debugMessage(user.getName(), " disimpankan.");
            cfg.save(file);
        }
    }

    @Override
    public void saveUsers(Collection<User> users) {
        for (User u : users) {
            saveUser(u);
        }
    }

    @Override
    public void saveGroup(Group group) {
        saveGroups(Groups.getAll());
    }

    @Override
    public void removeGroup(String groupId) {
        saveGroups(Groups.getAll()); // просто сохраняем, поскольку сама группа из списка уже удалена
    }

    @Override
    public void saveGroups(Collection<Group> groups) {
        Config cfg = new Config();
        groups.forEach(g -> {
            ConfigSection group = passToSection(g, true);
            ConfigSection worlds = new ConfigSection();
            g.getWorldPass().entrySet().forEach(e -> {
                worlds.set(e.getKey(), passToSection(e.getValue(), false));
            });
            group.set("worlds", worlds);
            cfg.set(g.getName(), group);
        });
        cfg.save(this.groupFile);
    }

    @Override
    public Collection<Group> loadGroups() {
        List<Group> groups = new ArrayList<>();
        if (!groupFile.exists()) return groups;
        Config cfg = new Config(groupFile, Config.YAML);
        cfg.getSections().entrySet().forEach(e -> {
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
        Message.debugMessage(groups.size(), "pangkat dimuat");
        return groups;
    }

    @Override
    public boolean isStored(String userName) {
        return getUserFile(userName).exists();
    }

    @Override
    public Collection<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (String fileName : userDir.list()) {
            if (!fileName.matches(".+\\.yml$")) continue;
            users.add(loadUser(fileName.substring(0, fileName.length() - 4)));
        }
        return users;
    }

    @Override
    public void clearUsers() {
        for (File file : userDir.listFiles()) {
            if (!file.isDirectory()) file.delete();
        }
    }

    @Override
    public void clearGroups() {
        if (groupFile.exists()) groupFile.delete();
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    private ConfigSection passToSection(Node node, boolean saveAdditions) {
        ConfigSection section = new ConfigSection();
        section.set("groups", node.getGroupList());
        section.set("permissions", node.getPermissionList());
        if (saveAdditions) {
            section.set("prefix", node.getPrefix());
            section.set("suffix", node.getSuffix());
            section.set("priority", node.getPriority());
        }
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