package ru.nukkit.multiperms.util;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import ru.nukkit.multiperms.MultiPermsPlugin;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public enum Message {

    //Default (lang) messages
    LNG_LOAD_FAIL("Gagal memuat bahasa dari file. Pesan standar digunakan"),
    LNG_SAVE_FAIL("Gagal menyimpan file bahasa"),
    LNG_PRINT_FAIL("Gagal mencetak pesan %1%. Objek pengirim adalah nol."),
    LNG_CONFIG("[MESSAGES] Pesan: %1% Bahasa: %2% Simpan file terjemahan: %1% Mode debug: %3%"),
    WORD_UNKNOWN("Tidak dikenal"),
    WRONG_PERMISSION("Kamu tidak memiliki cukup izin untuk menjalankan perintah ini"),
    PERMISSION_FAIL("Kamu tidak memiliki cukup izin untuk menjalankan perintah ini", 'c'),
    PLAYER_COMMAD_ONLY("Kamu dapat menggunakan perintah ini hanya dalam permainan!", 'c'),
    CMD_REGISTERED("Perintah terdaftar: %1%"),
    CMD_FAILED("Gagal menjalankan perintah. Mengetik %1% untuk mendapatkan bantuan!"),
    HLP_TITLE("%1% | Bantuan"),


    CMD_PERM_DESC("Perintah MultiPerms, ketik /perm help untuk info lebih lengkap"),
    CMD_PERM_HELP("/perm help [halaman] - menunjukkan bantuan"),
    CMD_PERM_UPDATE("/perm refresh - hitung ulang izin untuk semua pemain"),
    CMD_PERM_RELOAD("/perm reload - memuat ulang konfigurasi MultiPerms"),
    CMD_USER("/user <pemain> - tampilkan info tentang izin pemain"),
    CMD_USER_REMOVE("/user remove <pengguna> - hapus pengguna"),
    CMD_USER_SETPERM("/user <pemain> setperm [dunia] <izin> - tambahkan izin ke pemain"),
    CMD_USER_REMOVEPERM("/user <pemain> rmvperm [dunia] <izin> - hapus izin pemain"),
    CMD_USER_SETGROUP("/user <pemain> setgroup [dunia] <pangkat> - pindahkan pemain dalam pangkat"),
    CMD_USER_ADDGROUP("/user <pemain> addgroup [dunia] <pangkat> - tambahkan pemain dalam pangkat"),
    CMD_USER_REMOVEGROUP("/user <pemain> removegroup [dunia] <pangkat> - hapus pemain dari pangkat"),
    CMD_GROUP_CREATE("/group create <pangkat> - buat pangkat baru"),
    CMD_GROUP_ADDGROUP("/group <pangkat1> addgroup [dunia] <pangkat2> - tambahkan pangkat2 ke pangkat1"),
    CMD_GROUP_REMOVEGROUP("/group <pangkat1> remove [dunia] <pangkat2> - hapus pangkat1 dari pangkat2"),
    CMD_GROUP_SETPERM("/group <pangkat> setperm [dunia] <izin> - tambahkan izin ke pangkat"),
    CMD_GROUP_SETPREFIX("/group <pangkat> setprefix <awalan> - atur awalan untuk pangkat"),
    CMD_GROUP_SETSUFFIX("/group <pangkat> setsuffix <akhiran> - atur akhiran untuk pangkat"),
    CMD_GROUP_REMOVEPERM("/group <pangkat> removeperm <izin> - hapus izin dari pangkat"),
    CMD_GROUP_REMOVE("/group remove <pangkat> - hapus pangkat"),
    CMD_PERM_CHECK("/perm check <pemain> <izin> - periksa garis izin pemain"),
    CMD_USER_SETPREFIX("/user <pemain> setprefix <pengguna awalan> - atur awalan"),
    CMD_USER_SETSUFFIX("/user <pemain> setsuffix <pengguna akhiran> - atur akhiran"),
    CMD_PERM_EXPORT("/perm export [Nama file] - ekspor semua izin ke file"),
    CMD_PERM_IMPORT("/perm import [menimpa] [nama file] - impor izin dari file"),

    PERM_USER_NOTREGISTER("Pengguna %1% tidak terdaftar di server ini!"),

    PERM_USER_INFO("%1% info Perizinan"),
    PERM_USER_GROUPS("Pangkat: %1%"),
    PERM_USER_PERMS("Perizinan:"),

    PERM_GROUP_NOTFOUND("Pangkat %1% tidak diketahui!"),
    PERM_GROUP_GROUPS("Pangkat: %1%"),
    PERM_GROUP_PERMS("Perizinan:"),
    PERM_GROUP_INFO("info %1% Pangkat"),
    USER_SETPERM_OK("Pengguna %1% izin dikonfigurasi: %2%"),
    USER_SETPERMW_OK("Pangkat %1% izin dikonfigurasi: %2% (dunia %3%)"),
    USER_SETPERM_OK_INFORM("Izin kamu telah ditetapkan: %1%"),
    USER_SETPERMW_OK_INFORM("Izin kamu telah ditetapkan: %1% (dunia: %3%)"),

    USER_REMOVEPERM_NOTFOUND("Pengguna %1% tidak ditemukan. Tidak bisa menghapus izin"),
    USER_REMOVEPERM_NOTSET("Gagal menghapus izin %1% dari pengguna %2%. Itu tidak dikonfigurasi", 'c', '4'),
    USER_REMOVEPERMW_NOTSET("Gagal menghapus izin %1% (dunia %3) dari pengguna %2%. Itu tidak dikonfigurasi", 'c', '4'),
    USER_REMOVEPERM_OK("Izin %1% dihapus dari pengguna %2%"),
    USER_REMOVEPERMW_OK("Izin %1% (dunia %3%) dihapus dari pengguna %2%"),
    USER_REMOVEPERM_OK_INFORM("Izin %1% telah dihapus dari kamu"),
    USER_REMOVEPERMW_OK_INFORM("Izin %1% dihapus dari pengguna %2%"),
    USER_SETGROUP_OK("Pemain %1% dipindahkan ke pangkat %2%"),
    USER_SETGROUPW_OK("Pemain %1% dipindahkan ke pangkat %2% (dunia %3%)"),

    USER_SETGROUP_OK_INFORM("Kamu pindah ke pangkat %1%"),
    USER_SETGROUPW_OK_INFORM("Kamu pindah ke pangkat %1% (dunia %2%)"),
    USER_ADDGROUP_OK("Pemain %1% ditambahkan ke pangkat %2%"),
    USER_ADDGROUPW_OK("Pemain %1% ditambahkan ke pangkat %2% (dunia %3%)"),
    USER_ADDGROUP_OK_INFORM("Kamu ditambahkan ke pangkat %1%"),
    USER_ADDGROUPW_OK_INFORM("Kamu ditambahkan ke pangkat %1% (dunia %2%)"),

    USER_REMOVEGROUP_NOTFOUND("Pengguna %1% tidak ditemukan. Tidak dapat menghapusnya dari pangkat", 'c', '4'),
    USER_REMOVEGROUP_NOTSET("Gagal menghapus pemain %2% dari pangkat %1%. Itu tidak dikonfigurasi", 'c', '4'),
    USER_REMOVEGROUPW_NOTSET("Gagal menghapus pemain %2% dari pangkat %1% (dunia %3%). Itu tidak dikonfigurasi", 'c', '4'),
    USER_REMOVEGROUP_OK("Pemain %2% dihapus dari pangkat %1%"),
    USER_REMOVEGROUPW_OK("Pemain %2% dihapus dari pangkat %1% (dunia %3%)"),
    USER_REMOVEGROUP_OK_INFORM("Kamu telah dihapus dari pangkat %1%"),
    USER_REMOVEGROUPW_OK_INFORM("Kamu telah dihapus dari pangkat %1% (dunia %2%)"),
    USER_NOTEXIST("Gagal menghapus pengguna. Pengguna %1% tidak ada", 'c', '4'),
    USER_REMOVE_OK("Pengguna %1% dihapus"),

    GROUP_EXIST("Gagal membuat pangkat baru. Pangkat %1% sudah ada", 'c', '4'),
    GROUP_NOTEXIST("Gagal menghapus pangkat. Pangkat %1% tidak ada", 'c', '4'),
    GROUP_CREATE_OK("Pangkat %1% dibuat"),
    GROUP_REMOVE_OK("Pangkat %1% dihapus"),
    GROUP_ADDGROUP_NOTEXIST("Pangkat %1% tidak ada. Silakan periksa nama pangkat atau buat pangkat baru", 'c', '4'),
    GROUP_ADDGROUP_OK("Pangkat %2% telah ditambahkan ke pangkat %1%"),
    GROUP_ADDGROUPW_OK("Pangkat %2% telah ditambahkan ke pangkat %1% (pangkat %3%)"),

    GROUP_REMOVEGROUP_NOTEXIST("Pangkat %1% tidak ada. Silakan periksa nama pangkat atau buat pangkat baru", 'c', '4'),
    GROUP_REMOVEGROUP_OK("Pangkat %2% telah dihapus dari pangkat %1%"),
    GROUP_REMOVEGROUPW_OK("Pangkat %2% telah dihapus dari pangkat %1% (dunia %3%)"),

    GROUP_SETGROUP_NOTEXIST("Pangkat %1% tidak ada. Silakan periksa nama pangkat atau buat pangkat baru", 'c', '4'),
    GROUP_SETGROUP_OK("Pangkat %2% telah ditambahkan ke pangkat %1%. Semua pangkat lain telah dihapus"),
    GROUP_SETGROUPW_OK("Pangkat %2% telah ditambahkan ke pangkat %1% (dunia %3%). Semua pangkat lain telah dihapus"),

    GROUP_SETPERM_NOTEXIST("Gagal menetapkan izin. Pangkat %1% tidak ada", 'c', '4'),
    GROUP_SETPERM_OK("Pangkat %1% izin dikonfigurasi: %2%"),
    GROUP_SETPERMW_OK("Pangkat %1% izin dikonfigurasi: %2% (dunia: %3%)"),

    GROUP_REMOVEPERM_NOTEXIST("Gagal menghapus izin. Pangkat %1% tidak ada", 'c', '4'),
    GROUP_REMOVEPERM_PERMUNSET("Gagal menghapus izin. Perizinan %2% tidak dikonfigurasikan untuk pangkat %1%", 'c', '4'),
    GROUP_REMOVEPERM_OK("Perizinan %2% dihapus dari pangkat %1%"),
    GROUP_REMOVEPERMW_OK("Perizinan %2% dihapus dari pangkat %1% (dunia: %3%)"),

    GROUP_SETPREFIX_NOTEXIST("Gagal mengatur awalan. Pangkat %1% tidak ada", 'c', '4'),
    GROUP_SETSUFFIX_NOTEXIST("Gagal mengatur akhiran. Pangkat %1% tidak ada", 'c', '4'),
    GROUP_SETPREFIX_OK("Awalan pangkat %1% diubah menjadi %2%"),
    GROUP_SETSUFFIX_OK("Akhiran pangkat %1% diubah menjadi %2%"),

    GROUP_LIST("Pangkat yang tersedia: %1%"),
    GROUP_LISTNOTFOUND("Gagal menemukan pangkat apa pun!"),
    GROUP_INFOUNKNOWN("Gagal menampilkan info pangkat. Pangkat %1% tidak ditemukan"),
    GROUP_INFO_GROUPS("Pangkat: %1%"),
    GROUP_INFO_TITLE("info %1% Pangkat"),
    GROUP_INFO_PERMTITLE("Perizinan:"),
    PERM_CHECK_NULLPLAYER("Gagal memeriksa izin. Pemain %1% sedang tidak main"),
    PERM_CHECK_HAS("Pemain %1% memiliki izin %2%"),
    PERM_CHECK_HASNT("Pemain %1% tidak memiliki izin %2%"),
    PERM_RELOADED("Konfigurasi dimuat ulang!"),
    PERM_UPDATED("Perizinan pemain diperbarui"),
    REMOVED_GROUP_DETECTED("Pengguna %1% sedang mencoba menggunakan pangkat yang tidak dikenal %2%. Silakan periksa perizinan/pangkat pengguna"),
    PERM_USER_PREFIX("Awalan: %1%"),
    PERM_USER_SUFFIX("Akhiran: %1%"),
    PROVIDER_FAILED("Gagal init penyedia data: %1%", 'c'),
    USER_PREFIX_OK("Awalan pengguna %1% diatur ke %2%"),
    USER_PREFIX_OK_INFORM("Awalan kamu diubah menjadi %1%"),
    USER_SUFFIX_OK("Akhiran pengguna %1% diatur ke %2%"),
    USER_SUFFIX_OK_INFORM("Akhiran kamu diubah menjadi %1%", 'c'),

    USER_ADDGROUP_NOTEXIST("Gagal menambahkan pengguna %1% ke pangkat %2%. Pangkat tidak ada", 'c'),
    USER_SETGROUP_NOTEXIST("Gagal memindahkan pengguna %1% ke pangkat %2%. Pangkat tidak ada", 'c'),
    LOG_UNKNOWN_GROUP_DETECTED("Subpangkat tidak dikenal terdeteksi: %1%. Silakan periksa konfigurasi pengguna/pangkat kamu", 'c'),
    LOG_UNKNOWN_GROUP_DETECTED_USER("Pengguna %2% adalah anggota dari pangkat yang tidak dikenal: %1%. Silahkan periksa konfigurasi pengguna", 'c'),
    LOG_UNKNOWN_GROUP_DETECTED_GROUP("Pangkat %2% mengandung subpangkat yang tidak dikenal %1%. Silakan periksa konfigurasi pangkatn", 'c'),

    DB_DBLIB_NOTFOUND("DbLib diperlukan untuk dukungan MySQL/SQLite", 'c'),
    LOG_UNKNOWN_DATAPROVIDER("Penyedia data tidak dikenal: %1%. Akan menggunakan penyedia YAM", 'c', '4'),
    LOG_DATAPROVIDER("Penyedia data: %1%"),

    PERM_EXPORT_OK("Perizinan diekspor ke file: %1%"),
    PERM_EXPORT_FAILED("Ekspor perizinan gagal", 'c'),
    PERM_IMPORT_OK("Perizinan diimpor dari file: %1%"),
    PERM_IMPORT_FAILED("Impor perizinan gagal", 'c'),
    LOG_DATAPROVIDER_FAIL("Gagal mengaktifkan Penyedia Data. Silakan periksa konfigurasi kamu", 'c');


    private static boolean debugMode = false;
    private static String language = "default";
    private static boolean saveLanguage = false;
    private static char c1 = 'a';
    private static char c2 = '2';

    private static PluginBase plugin = null;

    private static Map<String, Long> logOnce = new HashMap<>();

    /**
     * This is my favorite debug routine :) I use it everywhere to print out variable values
     *
     * @param s - array of any object that you need to print out.
     *          Example:
     *          Message.BC ("variable 1:",var1,"variable 2:",var2)
     */
    public static void BC(Object... s) {
        if (!debugMode) return;
        if (s.length == 0) return;
        StringBuilder sb = new StringBuilder("&3[").append(plugin.getDescription().getName()).append("]&f ");
        for (Object str : s)
            sb.append(str.toString()).append(" ");
        plugin.getServer().broadcastMessage(TextFormat.colorize(sb.toString().trim()));
    }

    /**
     * Send current message to log files
     *
     * @param s
     * @return — always returns true.
     * Examples:
     * Message.ERROR_MESSAGE.log(variable1); // just print in log
     * return Message.ERROR_MESSAGE.log(variable1); // print in log and return value true
     */
    public boolean log(Object... s) {
        plugin.getLogger().info(getText(s));
        return true;
    }

    public boolean logOnce(Object... s) {
        return logOnce(86400, s);
    }

    public boolean logOnce(int seconds, Object... s) {
        String msg = getText(s);
        long curTime = System.currentTimeMillis();
        if (!logOnce.containsKey(msg) || logOnce.get(msg) + (seconds * 1000) < curTime) {
            log(s);
            logOnce.put(msg, curTime);
        }
        return true;
    }

    /**
     * Same as log, but will printout nothing if debug mode is disabled
     *
     * @param s
     * @return — always returns true.
     */
    public boolean debug(Object... s) {
        if (debugMode) plugin.getLogger().info(TextFormat.clean(getText(s)));
        return true;
    }

    /**
     * Show a message to player in center of screen (this routine unfinished yet)
     *
     * @param seconds — how much time (in seconds) to show message
     * @param sender  — Player
     * @param s
     * @return — always returns true.
     */
    public boolean tip(int seconds, CommandSender sender, Object... s) {
        if (sender == null) return Message.LNG_PRINT_FAIL.log(this.name());
        final Player player = sender instanceof Player ? (Player) sender : null;
        final String message = getText(s);
        if (player == null) sender.sendMessage(message);
        else for (int i = 0; i < seconds; i++)
            Server.getInstance().getScheduler().scheduleDelayedTask(new Runnable() {
                @SuppressWarnings("deprecation")
                public void run() {
                    if (player.isOnline()) player.sendTip(message);
                }
            }, 20 * i);
        return true;
    }

    /**
     * Show a message to player in center of screen
     *
     * @param sender — Player
     * @param s
     * @return — always returns true.
     */
    @SuppressWarnings("deprecation")
    public boolean tip(CommandSender sender, Object... s) {
        if (sender == null) return Message.LNG_PRINT_FAIL.log(this.name());
        Player player = sender instanceof Player ? (Player) sender : null;
        String message = getText(s);
        if (player == null) sender.sendMessage(message);
        else player.sendTip(message);
        return true;
    }

    /**
     * Send message to Player or to ConsoleSender
     *
     * @param sender
     * @param s
     * @return — always returns true.
     */
    public boolean print(CommandSender sender, Object... s) {
        if (sender == null) return Message.LNG_PRINT_FAIL.debug(this.name());
        sender.sendMessage(getText(s));
        return true;
    }

    /**
     * Send message to all players or to players with defined permission
     *
     * @param permission
     * @param s
     * @return — always returns true.
     * <p>
     * Examples:
     * Message.MSG_BROADCAST.broadcast ("pluginname.broadcast"); // send message to all players with permission "pluginname.broadcast"
     * Message.MSG_BROADCAST.broadcast (null); // send message to all players
     */
    public boolean broadcast(String permission, Object... s) {
        for (Player player : plugin.getServer().getOnlinePlayers().values()) {
            if (permission == null || player.hasPermission(permission)) print(player, s);
        }
        return true;
    }


    /**
     * Get formated text.
     *
     * @param keys * Keys - are parameters for message and control-codes.
     *             Parameters will be shown in position in original message according for position.
     *             This keys are used in every method that prints or sends message.
     *             <p>
     *             Example:
     *             <p>
     *             EXAMPLE_MESSAGE ("Message with parameters: %1%, %2% and %3%");
     *             Message.EXAMPLE_MESSAGE.getText("one","two","three"); //will return text "Message with parameters: one, two and three"
     *             <p>
     *             * Color codes
     *             You can use two colors to define color of message, just use character symbol related for color.
     *             <p>
     *             Message.EXAMPLE_MESSAGE.getText("one","two","three",'c','4');  // this message will be red, but word one, two, three - dark red
     *             <p>
     *             * Control codes
     *             Control codes are text parameteres, that will be ignored and don't shown as ordinary parameter
     *             - "SKIPCOLOR" - use this to disable colorizing of parameters
     *             - "NOCOLOR" (or "NOCOLORS") - return uncolored text, clear all colors in text
     *             - "FULLFLOAT" - show full float number, by default it limit by two symbols after point (0.15 instead of 0.1483294829)
     * @return
     */
    public String getText(Object... keys) {
        char[] colors = new char[]{color1 == null ? c1 : color1, color2 == null ? c2 : color2};
        if (keys.length == 0) return TextFormat.colorize("&" + colors[0] + this.message);
        String str = this.message;
        boolean noColors = false;
        boolean skipDefaultColors = false;
        boolean fullFloat = false;
        String prefix = "";
        int count = 1;
        int c = 0;
        DecimalFormat fmt = new DecimalFormat("####0.##");
        for (int i = 0; i < keys.length; i++) {
            String s = keys[i].toString();
            if (c < 2 && keys[i] instanceof Character) {
                colors[c] = (Character) keys[i];
                c++;
                continue;
            } else if (s.startsWith("prefix:")) {
                prefix = s.replace("prefix:", "");
                continue;
            } else if (s.equals("SKIPCOLOR")) {
                skipDefaultColors = true;
                continue;
            } else if (s.equals("NOCOLORS") || s.equals("NOCOLOR")) {
                noColors = true;
                continue;
            } else if (s.equals("FULLFLOAT")) {
                fullFloat = true;
                continue;
            } else if (keys[i] instanceof Location) {
                Location loc = (Location) keys[i];
                if (fullFloat)
                    s = loc.getLevel().getName() + "[" + loc.getX() + ", " + loc.getY() + ", " + loc.getZ() + "]";
                else
                    s = loc.getLevel().getName() + "[" + fmt.format(loc.getX()) + ", " + fmt.format(loc.getY()) + ", " + fmt.format(loc.getZ()) + "]";
            } else if (keys[i] instanceof Double || keys[i] instanceof Float) {
                if (!fullFloat) s = fmt.format((Double) keys[i]);
            }

            String from = (new StringBuilder("%").append(count).append("%")).toString();
            String to = skipDefaultColors ? s : (new StringBuilder("&").append(colors[1]).append(s).append("&").append(colors[0])).toString();
            str = str.replace(from, to);
            count++;
        }
        str = TextFormat.colorize(prefix.isEmpty() ? "&" + colors[0] + str : prefix + " " + "&" + colors[0] + str);
        if (noColors) str = TextFormat.clean(str);
        return str;
    }

    private void initMessage(String message) {
        this.message = message;
    }

    private String message;
    private Character color1;
    private Character color2;

    Message(String msg) {
        message = msg;
        this.color1 = null;
        this.color2 = null;
    }

    Message(String msg, char color1, char color2) {
        this.message = msg;
        this.color1 = color1;
        this.color2 = color2;
    }

    Message(String msg, char color) {
        this(msg, color, color);
    }

    @Override
    public String toString() {
        return this.getText("NOCOLOR");
    }

    /**
     * Initialize current class, load messages, etc.
     * Call this file in onEnable method after initializing plugin configuration
     *
     * @param plg
     */

    public static void init(PluginBase plg) {
        plugin = plg;
        language = MultiPermsPlugin.getCfg().language;
        if (language.equalsIgnoreCase("default")) language = Server.getInstance().getLanguage().getLang();
        else if (language.length() > 3) language = language.substring(0, 3);
        debugMode = MultiPermsPlugin.getCfg().debugMode;
        saveLanguage = MultiPermsPlugin.getCfg().saveLanguage;

        initMessages();
        if (saveLanguage) saveMessages();
        LNG_CONFIG.debug(Message.values().length, language, true, debugMode);
    }

    /**
     * Enable debugMode
     *
     * @param debug
     */
    public static void setDebugMode(boolean debug) {
        debugMode = debug;
    }

    public static boolean isDebug() {
        return debugMode;
    }


    private static void initMessages() {
        File f = new File(plugin.getDataFolder() + File.separator + language + ".lng");
        Config lng = null;
        if (!f.exists()) {
            lng = new Config(f, Config.YAML);
            InputStream is = plugin.getClass().getResourceAsStream("/lang/" + language + ".lng");
            lng.load(is);
            if (!f.delete()) {
                System.gc();
                f.delete();
            }
        } else lng = new Config(f, Config.YAML);
        for (Message key : Message.values())
            key.initMessage(lng.getString(key.name().toLowerCase(), key.message));
    }

    private static void saveMessages() {
        File f = new File(plugin.getDataFolder() + File.separator + language + ".lng");
        Config lng = new Config(f, Config.YAML);
        for (Message key : Message.values())
            lng.set(key.name().toLowerCase(), key.message);
        try {
            lng.save();
        } catch (Exception e) {
            LNG_SAVE_FAIL.log();
            if (debugMode) e.printStackTrace();
            return;
        }
    }

    /**
     * Send message (formed using join method) to server log if debug mode is enabled
     *
     * @param s
     */
    public static boolean debugMessage(Object... s) {
        if (debugMode) plugin.getLogger().info(TextFormat.clean(join(s)));
        return true;
    }

    /**
     * Join object array to string (separated by space)
     *
     * @param s
     */
    public static String join(Object... s) {
        StringBuilder sb = new StringBuilder();
        for (Object o : s) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(o.toString());
        }
        return sb.toString();
    }

    public static String color1(String message) {
        return TextFormat.colorize("&" + c1 + message);
    }

    public static String color2(String message) {
        return TextFormat.colorize("&" + c2 + message);
    }

}