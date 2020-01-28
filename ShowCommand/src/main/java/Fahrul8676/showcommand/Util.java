package Fahrul8676.showcommand;

import Fahrul8676.showcommand.Main;
import cn.nukkit.utils.TextFormat;

public class Util {
    Main main;
    public static final String info;
    public static final String error;
    public static final String debug;
    public static final String load;
    public static final String enable;
    public static final String disable;

    public Util() {
    }

    static {
        info = TextFormat.RESET + "[" + TextFormat.GOLD + "ShowCommand" + TextFormat.YELLOW + " INFO" + TextFormat.RESET + "] " + TextFormat.AQUA;
        error = TextFormat.RESET + "[" + TextFormat.GOLD + "ShowCommand" + TextFormat.RED + " WARN" + TextFormat.RESET + "] ";
        debug = TextFormat.RESET + "[" + TextFormat.GOLD + "ShowCommand" + TextFormat.GREEN + " DEBUG" + TextFormat.RESET + "] ";
        load = info + "Pemuatan...";
        enable = info + "Mengaktifkan...";
        disable = info + "Menonaktifkan...";
    }
}
