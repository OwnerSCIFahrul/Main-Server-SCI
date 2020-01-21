package ru.nukkit.multiperms.permissions;

import cn.nukkit.Server;
import ru.nukkit.multiperms.MultiPermsPlugin;
import ru.nukkit.multiperms.data.Providers;
import ru.nukkit.multiperms.event.PermissionsUpdateEvent;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.WorldParam;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Groups {

    private static ConcurrentMap<String, Group> groups;

    public static void init() {
        loadGroups();
    }

    public static Group getGroup(String name) {
        return groups.containsKey(name) ? groups.get(name) : null;
    }

    public static boolean exist(String id) {
        return groups.containsKey(id);
    }

    public static boolean create(String id) {
        return create(id, -1);
    }

    public static boolean create(String id, int priority) {
        return create(id, priority, true);
    }

    public static boolean create(String id, int priority, boolean save) {
        if (id == null || id.isEmpty() || groups.containsKey(id)) return false;
        Group group = priority < 0 ? new Group(id) : new Group(id, priority);
        groups.put(id, group);
        if (save) saveGroup(group);
        return true;
    }

    public static boolean remove(String id) {
        if (!groups.containsKey(id)) return false;
        groups.remove(id);
        Providers.removeGroup(id);
        Users.recalculatePermissions();
        return true;
    }

    public static Collection<Group> getAll() {
        return groups.values();
    }


    public static boolean addGroup(String groupId, WorldParam wp) {
        return wp.hasWorld() ? addGroup(groupId, wp.world, wp.param) : addGroup(groupId, wp.param);
    }

    public static boolean addGroup(String groupId1, String world, String groupId2) {
        Group group1 = getGroup(groupId1);
        Group group2 = getGroup(groupId2);
        if (group1 == null || group2 == null) return false;
        group1.getWorldPassOrCreate(world).addGroup(group2);
        saveGroup(group1);
        return true;
    }

    public static boolean addGroup(String groupId1, String groupId2) {
        Group group1 = getGroup(groupId1);
        Group group2 = getGroup(groupId2);
        if (group1 == null || group2 == null) return false;
        group1.addGroup(group2);
        saveGroup(group1);
        return true;
    }

    public static boolean setPerm(String id, WorldParam wp) {
        return wp.hasWorld() ? setPerm(id, wp.world, wp.param) : setPerm(id, wp.param);
    }

    private static boolean setPerm(String id, String world, String perm) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.setPermission(world, perm);
        saveGroup(group);
        return true;
    }

    public static boolean setPerm(String id, String permStr) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.setPermission(permStr);
        saveGroup(group);
        return true;
    }

    public static boolean setGroup(String id1, WorldParam wp) {
        return wp.hasWorld() ? setGroup(id1, wp.world, wp.param) : setGroup(id1, wp.param);
    }

    public static boolean setGroup(String id1, String world, String param) {
        Group group1 = getGroup(id1);
        Group group2 = getGroup(param);
        if (group1 == null || group2 == null) return false;
        group1.getWorldPassOrCreate(world).setGroup(group2);
        saveGroup(group1);
        return true;
    }

    public static boolean setGroup(String id1, String id2) {
        Group group1 = getGroup(id1);
        Group group2 = getGroup(id2);
        if (group1 == null || group2 == null) return false;
        group1.setGroup(group2);
        saveGroup(group1);
        return true;
    }

    public static boolean removeGroup(String id1, WorldParam wp) {
        return wp.hasWorld() ? removeGroup(id1, wp.world, wp.param) : removeGroup(id1, wp.param);
    }

    public static boolean removeGroup(String id1, String world, String id2) {
        Group group = getGroup(id1);
        if (group == null) return false;
        if (group.removeGroup(world, id2)) {
            saveGroup(group);
            return true;
        } else {
            return false;
        }
    }

    public static boolean removeGroup(String id1, String id2) {
        Group group = getGroup(id1);
        if (group == null) return false;
        if (group.removeGroup(id2)) {
            saveGroup(group);
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPermissionSet(String id, String permStr) {
        Group group = getGroup(id);
        if (group == null) return false;
        return group.isPermissionSet(permStr);
    }

    public static boolean setPrefix(String id, String prefix) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.setPrefix(prefix);
        saveGroup(group);
        return true;
    }

    public static boolean setSuffix(String id, String suffix) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.setSuffix(suffix);
        saveGroup(group);
        PermissionsUpdateEvent event = new PermissionsUpdateEvent();
        Server.getInstance().getPluginManager().callEvent(event);
        return true;
    }

    public static void saveGroups() {
        Providers.saveGroups();
        Users.recalculatePermissions();
    }

    public static void saveGroup(Group group) {
        Providers.saveGroup(group);
        Users.recalculatePermissions();
    }

    public static void loadGroups() {
        Message.debugMessage("Memuat pangkat");
        groups = new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);
        Providers.loadGroups().whenComplete((resultGroups, e) -> {
            if (e != null) {
                e.printStackTrace();
            } else {
                groups.putAll(resultGroups);
                createDefaultGroup();
                Users.recalculatePermissions();
            }
        });
    }

    public static boolean removePermission(String id, String permStr) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.removePermission(permStr);
        saveGroup(group);
        return true;
    }

    public static boolean removePermission(String id, String world, String permStr) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.removePermission(world, permStr);
        saveGroup(group);
        return true;
    }

    public static boolean removePermission(String id, WorldParam wp) {
        return wp.hasWorld() ? removePermission(id, wp.world, wp.param) : removePermission(id, wp.param);
    }

    public static boolean isDefault(String groupStr) {
        if (!groups.containsKey(groupStr)) return false;
        return groups.get(groupStr).isDefault();
    }

    public static void updateGroups(Collection<Group> newGroups) {
        updateGroups(newGroups, true);
    }

    public static void updateGroups(Collection<Group> newGroups, boolean removeExist) {
        if (newGroups == null) return;
        if (removeExist) {
            groups.clear();
        }
        newGroups.forEach(g -> {
            if (g != null) {
                groups.put(g.getName(), g);
            }
        });
        createDefaultGroup();
        Users.recalculatePermissions();
    }

    private static void createDefaultGroup() {
        if (MultiPermsPlugin.getCfg().defaultGroup.isEmpty()) return;
        if (exist(MultiPermsPlugin.getCfg().defaultGroup)) return;
        Message.debugMessage("Membuat pangkat standar", "[" + MultiPermsPlugin.getCfg().defaultGroup + "]");
        Groups.create(MultiPermsPlugin.getCfg().defaultGroup, -1, false);
    }
}
