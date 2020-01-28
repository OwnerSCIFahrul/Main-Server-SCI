package ru.nukkit.multiperms.util;

import cn.nukkit.Player;
import cn.nukkit.Server;

import java.util.Collection;

public class Util {

    public static String join(Collection<String> collection) {
        return join(collection, ", ");
    }

    public static String join(String[] ln, int num) {
        if (ln.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ln.length; i++) {
            if (i < num) continue;
            if (sb.length() > 0) sb.append(" ");
            sb.append(ln[i]);
        }
        return sb.toString();
    }

    public static String join(Collection<String> collection, String divider) {
        if (divider == null) divider = " ";
        StringBuilder sb = new StringBuilder();
        for (String s : collection) {
            if (sb.length() > 0) sb.append(divider);
            sb.append(s);
        }
        return sb.toString();
    }

    public static Player getPlayer(String userName) {
        return Server.getInstance().getPlayerExact(userName);
    }

    public static void informMessage(String userName, Message message, Object... o) {
        Player player = getPlayer(userName);
        if (player == null || !player.hasPermission("multiperms.informed-user")) return;
        message.print(player, o);
    }
}