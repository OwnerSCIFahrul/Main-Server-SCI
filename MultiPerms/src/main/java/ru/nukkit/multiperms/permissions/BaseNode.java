package ru.nukkit.multiperms.permissions;

import cn.nukkit.utils.ConfigSection;
import ru.nukkit.multiperms.MultiPermsPlugin;
import ru.nukkit.multiperms.util.HighBase;
import ru.nukkit.multiperms.util.LowBase;

import java.util.*;


public abstract class BaseNode extends Node {

    protected String name;
    private Map<String, Node> worldPass;

    public BaseNode(String name) {
        super();
        this.name = name;
        this.worldPass = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public BaseNode(String playerName, Node node) {
        super(node);
        this.name = playerName;
        this.worldPass = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public BaseNode(BaseNode node) {
        this(node.getName(), node);
        this.worldPass.putAll(node.worldPass);
    }

    public Set<Group> getAllGroups() {
        Set<Group> algroups = new TreeSet<>(new HighBase());
        getGroups().forEach(group -> {
            if (group != null && !algroups.contains(group)) {
                algroups.add(group);
                algroups.addAll(group.getAllGroups());
            }
        });
        return algroups;
    }

    public Set<BaseNode> getAllNodes() {
        return getAllNodes(true);
    }

    public Set<BaseNode> getAllNodes(boolean acsending) {
        Set<BaseNode> nodes = new TreeSet<>(acsending ? new HighBase() : new LowBase());//new TreeSet<>(new HighBase());
        nodes.add(this);
        getGroups().forEach(group -> {
            if (group != null && !nodes.contains(group)) {
                nodes.addAll(group.getAllNodes());
            }
        });
        return nodes;
    }

    public Map<String, List<Permission>> getPermissionsMap() {
        Map<String, List<Permission>> permMap = new LinkedHashMap<>();
        permissions.forEach(p -> {
            if (!permMap.containsKey("")) permMap.put("", new ArrayList<>());
            permMap.get("").add(p);
        });
        this.worldPass.entrySet().forEach(e -> {
            if (!permMap.containsKey(e.getKey())) permMap.put(e.getKey(), new ArrayList<>());
            e.getValue().getPermissions().forEach(p -> permMap.get(e.getKey()).add(p));
        });
        return permMap;
    }

    public Collection<Permission> getPermissions(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getPermissions() : Collections.EMPTY_SET;
    }

    public void setPermissions(String world, Collection<Permission> permissions) {
        getWorldPassOrCreate(world).setPermissions(permissions);
    }

    public int getPriority(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getPriority() : 0;
    }

    public void setPriority(String world, int priority) {
        getWorldPassOrCreate(world).setPriority(priority);
    }

    public String getPrefix(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getPrefix() : "";
    }

    public void setPrefix(String world, String prefix) {
        getWorldPassOrCreate(world).setPrefix(prefix);
    }

    public String getSuffix(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getSuffix() : "";
    }

    public void setSuffix(String world, String suffix) {
        getWorldPassOrCreate(world).setSuffix(suffix);
    }

    public void addPermission(String world, String perm, boolean positive) {
        setPermission(world, new Permission(perm, positive));
    }

    public void addPermission(String world, String perm) {
        setPermission(world, new Permission(perm));
    }

    public void addPermission(String world, Permission permission) {
        getWorldPassOrCreate(world).setPermission(permission);
    }

    public Map<String, List<String>> getGroupMap() {
        Map<String, List<String>> groupMap = new HashMap<>();

        groups.forEach(g -> {
            if (!groupMap.containsKey("")) groupMap.put("", new ArrayList<>());
            groupMap.get("").add(g);
        });
        this.worldPass.entrySet().forEach(e -> {
            e.getValue().getGroupList().forEach(g -> {
                if (!groupMap.containsKey(e.getKey())) groupMap.put(e.getKey(), new ArrayList<>());
                groupMap.get(e.getKey()).add(g);
            });
        });
        return groupMap;
    }

    public List<String> getGroupList(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getGroupList() : Collections.EMPTY_LIST;
    }

    public List<String> getPermissionList(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getPermissionList() : Collections.EMPTY_LIST;
    }

    public void addGroup(String world, String group) {
        addGroup(world, Groups.getGroup(group));
    }

    public void addGroup(String world, Group group) {
        if (group == null) return;
        getWorldPassOrCreate(world).addGroup(group);
    }

    public void setGroup(String world, String groupStr) {
        setGroup(world, Groups.getGroup(world));
    }

    public void setGroup(String world, Group group) {
        if (group == null) return;
        getWorldPassOrCreate(world).setGroup(group);
    }


    public boolean isPermissionSet(String world, String permStr) {
        return hasWorldPass(world) && getWorldPass(world).isPermissionSet(permStr);
    }

    public void setPermissionsList(String world, List<String> permissions) {
        getWorldPassOrCreate(world).setPermissionsList(permissions);
    }

    public void setGroupList(String world, List<String> groupList) {
        getWorldPassOrCreate(world).setGroupList(groupList);
    }

    public String getName() {
        return name;
    }

    public Node getWorldPassOrCreate(String world) {
        if (world == null || world.isEmpty()) return this;
        Node node = getWorldPass(world);
        if (node == null) {
            node = new Node();
            this.worldPass.put(world, node);
        }
        return node;
    }

    public boolean hasWorldPass(String world) {
        return getWorldPass(world) != null;
    }


    private String getMirrorWorld(String currentWorld) {
        ConfigSection mirros = MultiPermsPlugin.getCfg().mirros;
        if (mirros != null & !mirros.isEmpty())
            for (Map.Entry<String, Object> e : mirros.entrySet())
                if (e.getValue() instanceof String) {
                    String[] worlds = ((String) e.getValue()).split(",\\s+");
                    for (String w : worlds)
                        if (w.equalsIgnoreCase(currentWorld)) return e.getKey();
                }
        return currentWorld;
    }

    public Node getWorldPass(String world) {
        if (world == null || world.isEmpty()) return null;
        String targetWorld = getMirrorWorld(world);
        return worldPass.containsKey(targetWorld) ? worldPass.get(targetWorld) : null;
    }

    public Map<String, Node> getWorldPass() {
        return worldPass;
    }


    public void setWorldPass(String world, Node node) {
        this.worldPass.put(world, node);
    }


    public void setPermission(String world, String perm, boolean positive) {
        setPermission(world, new Permission(perm, positive));
    }

    public void setPermission(String world, String perm) {
        setPermission(world, new Permission(perm));
    }

    public void setPermission(String world, Permission permission) {
        getWorldPassOrCreate(world).setPermission(permission);
    }

    public void removePermission(String world, String perm) {
        removePermission(world, new Permission(perm));
    }

    public void removePermission(String world, Permission permission) {
        if (hasWorldPass(world)) return;
        getWorldPass(world).removePermission(permission);
    }


    public boolean removeGroup(String world, String group) {
        if (hasWorldPass(world)) return false;
        return getWorldPass(world).removeGroup(group);
    }

    public void removeGroup(String world, Group group) {
        if (hasWorldPass(world)) return;
        getWorldPass(world).removeGroup(group);
    }

    public boolean inGroup(String world, String groupStr) {
        if (world == null) return false;
        Group group = Groups.getGroup(groupStr);
        if (group == null) return false;
        Node node = getWorldPass(world);
        if (node == null) return false;
        return node.inGroup(group.getName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        BaseNode that = (BaseNode) o;
        return name != null && name.equalsIgnoreCase(that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String baseToString(BaseNode baseNode) {
        StringBuilder sb = new StringBuilder();
        for (Group g : baseNode.getGroups()) {
            if (sb.length() == 0) sb.append(baseNode.getName()).append(" [").append(g.getName());
            else sb.append(", ").append(g.getName());
        }
        return sb.append("]").toString();
    }


}
