package ru.nukkit.multiperms.permissions;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.permission.PermissionAttachment;
import ru.nukkit.multiperms.MultiPermsPlugin;
import ru.nukkit.multiperms.util.Message;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class User extends BaseNode {

    PermissionAttachment attachment;

    public User(String playerName) {
        super(playerName);
        this.priority = MultiPermsPlugin.getCfg().userPriority;
    }

    public User(String playerName, String prefix, String suffix, int priority) {
        super(playerName);
        this.prefix = prefix;
        this.suffix = suffix;
        this.priority = priority;
    }


    public User(String playerName, Node node) {
        super(playerName, node);
    }

    public User(String playerName, BaseNode node) {
        super(playerName, node);
    }

    public void recalculatePermissions() {
        emptyToDefault();
        Message.debugMessage("Hitung ulang izin:", this.getName());
        if (getAttachment() == null) return;

        Player player = Server.getInstance().getPlayerExact(this.name);
        String world = useWorlds() ? player.getLevel().getName() : null;

        Map<String, Boolean> perms = new LinkedHashMap<>();

        Set<BaseNode> nodes = this.getAllNodes(false);

        nodes.forEach(node -> {

            if (MultiPermsPlugin.getCfg().groupPermission && !node.equals(this))
                perms.put("permission.group." + node.getName(), true);

            node.getPermissions().forEach(perm -> perms.put(perm.getName(), perm.isPositive()));
            if (world != null) node.getPermissions(world).forEach(perm -> perms.put(perm.getName(), perm.isPositive()));
        });

        attachment.clearPermissions();

        player.recalculatePermissions();
        if (!perms.isEmpty()) attachment.setPermissions(perms);
        player.recalculatePermissions();
        if (Message.isDebug()) perms.entrySet().forEach(e -> {
            Message.debugMessage(e.getValue() ? "+" : "-", e.getKey());
        });

        Server.getInstance().getScheduler().scheduleDelayedTask(() -> player.sendCommandData(), 10);
    }

    public PermissionAttachment getAttachment() {
        Player player = Server.getInstance().getPlayerExact(this.name);
        attachment = player == null ? null : (attachment == null ? player.addAttachment(MultiPermsPlugin.getPlugin()) : attachment);
        return attachment;
    }

    private boolean useWorlds() {
        return MultiPermsPlugin.getCfg().enableWorldSupport;
    }

    public boolean isEmpty() {
        return this.permissions.isEmpty() &&
                (groups.isEmpty() || (groups.size() == 1 && (Groups.isDefault(groups.stream().toArray(String[]::new)[0])))) &&
                this.getWorldPass().isEmpty() &&
                this.prefix.isEmpty() && this.suffix.isEmpty() && (this.priority == MultiPermsPlugin.getCfg().userPriority);
    }

    private void emptyToDefault() {
        String groupDefault = MultiPermsPlugin.getCfg().defaultGroup;
        if (groupDefault == null || groupDefault.isEmpty()) return;
        if (!this.isEmpty()) return;
        this.setGroup(groupDefault);
    }
}
