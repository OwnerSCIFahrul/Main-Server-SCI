package ru.nukkit.multiperms.util;

public class WorldParam {
    public String world;
    public String param;

    public WorldParam(String param) {
        world = "";
        this.param = param;
        if (param.contains(":")) {
            String[] ln = param.split(":");
            world = ln[0];
            param = ln[1];
        }
    }

    public WorldParam(String[] args, int num) {
        if (args.length <= num + 1) {
            this.world = "";
            this.param = args[num];
        } else {
            this.world = args[num];
            this.param = args[num + 1];
        }
    }

    public boolean hasWorld() {
        return !world.isEmpty();
    }

    public Message message(Message msg, Message worldMsg) {
        return world.isEmpty() ? msg : worldMsg;
    }

}
