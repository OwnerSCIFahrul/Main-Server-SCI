package Fahrul8676.essentials;

import cn.nukkit.Player;

public class Utils {
    public Utils() {
    }

    public static String toggleFlight(Player player) {
        String state;
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            state = "disabled";
        } else {
            player.setAllowFlight(true);
            state = "enabled";
        }

        return state;
    }

    public static String split(String[] args) {
        StringBuilder sb = new StringBuilder();
        String[] var2 = args;
        int var3 = args.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String arg = var2[var4];
            sb.append(arg + " ");
        }

        return sb.toString();
    }
}