package ru.nukkit.multiperms.permissions;

import ru.nukkit.multiperms.MultiPermsPlugin;

public class Group extends BaseNode {

    public Group(String name, Node node) {
        super(name, node);
    }

    public Group(String name) {
        this(name, MultiPermsPlugin.getCfg().defaultGroup.equalsIgnoreCase(name) ? 0 : MultiPermsPlugin.getCfg().groupPriority);
    }

    public Group(String name, int priority) {
        super(name);
        this.priority = priority;
    }

    public Group(String name, String prefix, String suffix, int priority) {
        super(name);
        this.prefix = prefix;
        this.suffix = suffix;
        this.priority = priority;
    }

    public boolean isDefault() {
        if (MultiPermsPlugin.getCfg().defaultGroup == null || MultiPermsPlugin.getCfg().defaultGroup.isEmpty())
            return false;
        return this.getName().equalsIgnoreCase(MultiPermsPlugin.getCfg().defaultGroup);
    }
}