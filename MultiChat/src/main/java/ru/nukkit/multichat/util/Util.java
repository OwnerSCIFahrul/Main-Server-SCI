package ru.nukkit.multichat.util;

import cn.nukkit.Player;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.TextFormat;
import ru.nukkit.multichat.MultiChat;
import ru.nukkit.multiperms.MultiPerms;

import java.time.LocalTime;

public class Util {

    public static String getChatFormat(Player player) {
        return replacePlaceholders(player, getCustomFormat(player));
    }

    public static String getCustomFormat(Player player) {
        return getCusomParam(player, true);
    }

    public static String getCustomNameTag(Player player) {
        String nameTag = getCusomParam(player, false);
        return MultiChat.getCfg().nametagStripColor ? TextFormat.clean(nameTag) : nameTag;
    }

    private static String getCusomParam(Player player, boolean getFormat) {
        String param = getFormat ? ".chat" : ".name-tag";
        String result = getFormat ? MultiChat.getCfg().chatFormat : MultiChat.getCfg().nametagFormat;
        ConfigSection section = MultiChat.getCfg().customGroups;
        for (String key : section.getKeys(false)) {
            if (isPlayerInGroup(player, key) && section.exists(key + param)) {
                result = section.getString(key + param);
            }
        }
        return result;
    }

    private static String getCustomDisplayName(Player player) {
        String result = MultiChat.getCfg().displayNameFormat;
        String param = ".display-name";
        ConfigSection section = MultiChat.getCfg().customGroups;
        for (String key : section.getKeys(false)) {
            if (isPlayerInGroup(player, key) && section.exists(key + param)) {
                result = section.getString(key + param);
            }
        }
        return MultiChat.getCfg().isDisplayNameNoColors ? TextFormat.clean(result) : result;
    }

    private static boolean isPlayerInGroup(Player player, String group) {
        if (!MultiChat.getCfg().useSubGroup) return MultiPerms.isInGroup(player, group);
        for (String g : MultiPerms.getGroups(player))
            if (group.equalsIgnoreCase(g)) return true;
        return false;
    }

    public static String getNametag(Player player) {
        return replacePlaceholders(player, getCustomNameTag(player))
                .replace("{%0}", player.getName()).replace("{%1}", "");
    }

    private static String replacePlaceholders(Player player, String str) {
        String format = new String(str);
        format = format.replace("%prefix%", MultiPerms.getPrefix(player));
        format = format.replace("%suffix%", MultiPerms.getSuffix(player));
        format = format.replace("%player%", player.getName()).replace("%message%", "{%1}");
        format = format.replace("%display%", player.getDisplayName()).replace("%message%", "{%1}");
        format = format.replace("%world%", player.getLevel().getName());
        format = format.replace("%faction%", getFName(player));
        format = format.replace("%time%", TextFormat.ESCAPE + LocalTime.now().getHour() + "§f:§r" + LocalTime.now().getMinute() + "§f:§r" + LocalTime.now().getSecond());
        return TextFormat.colorize(format).trim();
    }

    public static String join(String[] args, int num) {
        if (args.length <= num) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = num; i < args.length; i++) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(args[i]);
        }
        return sb.toString();
    }

    public static void setNameTag(Player player, String nameTag) {
        if (nameTag == null || nameTag.isEmpty()) {
            player.setNameTagVisible(false);
        } else {
            player.isNameTagVisible();
            player.setNameTag(TextFormat.colorize(nameTag));
        }
    }

    public static void setDisplayName(Player player, String displayName) {
        player.setDisplayName(MultiChat.getCfg().isDisplayNameNoColors ? TextFormat.clean(displayName) : displayName);
    }

    public static String getDisplayName(Player player) {
        return replacePlaceholders(player, getCustomDisplayName(player))
                .replace("{%0}", player.getName()).replace("{%1}", "");
    }

    public static String getFName(Player player) {
        try {
            Class.forName("com.massivecraft.factions.P");
            return com.massivecraft.factions.P.p.getPlayerFactionTag(player);
        } catch (Exception e) {
            return "\u00A7cTidak dapat menemukan faction";
        }
    }
}
