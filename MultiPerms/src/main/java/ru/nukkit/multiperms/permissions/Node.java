package ru.nukkit.multiperms.permissions;

import ru.nukkit.multiperms.util.Message;

import java.util.*;

public class Node {
    Set<String> groups;
    Set<Permission> permissions;
    int priority;
    String prefix;
    String suffix;


    public Node() {
        this.permissions = new HashSet<>();
        this.groups = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        this.prefix = "";
        this.suffix = "";
        this.priority = 0;
    }

    public Node(Node node) {
        groups = node.groups;
        permissions = node.permissions;
        priority = node.priority;
        prefix = node.prefix;
        suffix = node.suffix;
    }

    public boolean removeGroup(Group group) {
        if (group == null) return false;
        return removeGroup(group.getName());
    }

    public boolean removeGroup(String group) {
        if (!this.groups.contains(group)) return false;
        this.groups.remove(group);
        return true;
    }

    public void removePermission(String perm) {
        removePermission(new Permission(perm));
    }

    public void removePermission(Permission permission) {
        if (this.permissions.contains(permission))
            this.permissions.remove(permission);
    }

    public Collection<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions.clear();
        this.permissions.addAll(permissions);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setPermission(String perm, boolean positive) {
        setPermission(new Permission(perm, positive));
    }

    public void setPermission(String perm) {
        setPermission(new Permission(perm));
    }

    public void setPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public List<String> getGroupList() {
        return new ArrayList<>(groups);
    }

    public List<String> getPermissionList() {
        List<String> permList = new ArrayList<>();
        permissions.forEach(p -> permList.add(p.toString()));
        return permList;
    }

    public void addGroup(Group group2) {
        this.groups.add(group2.getName());
    }

    public void addGroup(String groupStr) {
        this.groups.add(groupStr);
    }

    public void setGroup(String groupStr) {
        Group group = Groups.getGroup(groupStr);
        if (group == null) return;
        setGroup(group);
    }

    public void setGroup(Group group2) {
        this.groups.clear();
        addGroup(group2);
    }

    public boolean isPermissionSet(String permStr) {
        return permissions.contains(new Permission(permStr));
    }

    public void setPermissionsList(List<String> permissions) {
        this.permissions.clear();
        permissions.forEach(p -> this.permissions.add(new Permission(p)));
    }

    public void setGroupList(List<String> groupList) {
        this.groups.clear();
        this.groups.addAll(groupList);
    }

    public Set<Group> getGroups() {
        Set<Group> groups = new HashSet<>();
        this.groups.forEach(g -> {
            Group group = Groups.getGroup(g);
            if (group != null) groups.add(group);
            else {
                Message m = Message.LOG_UNKNOWN_GROUP_DETECTED;
                String logId = g;
                String id = "";
                if (this instanceof User) {
                    User user = (User) this;
                    m = Message.LOG_UNKNOWN_GROUP_DETECTED_USER;
                    logId = g + user.getName();
                    id = user.getName();
                } else if (this instanceof Group) {
                    Group subgroup = (Group) this;
                    id = subgroup.getName();
                    m = Message.LOG_UNKNOWN_GROUP_DETECTED_GROUP;
                    logId = g + subgroup.getName();
                }
                m.logOnce(logId, id);
            }
        });
        return groups;
    }

    public boolean inGroup(String groupStr) {
        Group group = Groups.getGroup(groupStr);
        if (group == null) return false;
        return groups.contains(group.getName());
    }
}