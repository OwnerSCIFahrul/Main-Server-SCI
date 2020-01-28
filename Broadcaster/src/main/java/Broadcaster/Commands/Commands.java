package Broadcaster.Commands;

import Broadcaster.Broadcaster;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class Commands extends Command {

    private Broadcaster plugin;

    public Commands(Broadcaster plugin) {
        super("broadcaster", "Perintah Penyiar.", "/broadcaster <on|off|reload|info>");
        this.setPermission("broadcaster");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(TextFormat.colorize("&cKamu tidak memiliki izin untuk menggunakan perintah ini!"));
        } else {
            if (args.length > 0) {
                switch (args[0]) {
                    case "on":
                        if (sender.hasPermission("broadcaster.on")) {
                            if (args.length > 1) {
                                if (args[1].equalsIgnoreCase("broadcast")) {
                                    this.plugin.config.set("broadcast-enabled", true);
                                    this.plugin.config.save();
                                    sender.sendMessage(TextFormat.colorize("&aSiaran berhasil diaktifkan!"));
                                } else if (args[1].equalsIgnoreCase("popup")) {
                                    this.plugin.config.set("popup-broadcast-enabled", true);
                                    this.plugin.config.save();
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&aSiaran bacaan berhasil diaktifkan!"));
                                } else if (args[1].equalsIgnoreCase("title")) {
                                    this.plugin.config.set("title-broadcast-enabled", true);
                                    this.plugin.config.save();
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&aSiaran judul berhasil diaktifkan!"));
                                } else {
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&cPemakaian: /broadcaster on <broadcast|popup|title>"));
                                }
                            } else {
                                if (!this.plugin.config.getBoolean("broadcast-enabled") || !this.plugin.config.getBoolean("popup-broadcast-enabled", !this.plugin.config.getBoolean("title-broadcast-enabled"))) {
                                    this.plugin.config.set("broadcast-enabled", true);
                                    this.plugin.config.set("popup-broadcast-enabled", true);
                                    this.plugin.config.set("title-broadcast-enabled", true);
                                    this.plugin.config.save();
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&aSiaran berhasil diaktifkan!"));
                                } else {
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&cSiaran sudah diaktifkan!"));
                                }
                            }
                        } else {
                            sender.sendMessage(TextFormat.colorize("&cKamu tidak memiliki izin untuk menggunakan perintah ini!"));
                        }
                        break;
                    case "off":
                        if (sender.hasPermission("broadcaster.off")) {
                            if (args.length > 1) {
                                if (args[1].equalsIgnoreCase("broadcast")) {
                                    this.plugin.config.set("broadcast-enabled", false);
                                    this.plugin.config.save();
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&aSiaran berhasil dinonaktifkan!"));
                                } else if (args[1].equalsIgnoreCase("popup")) {
                                    this.plugin.config.set("popup-broadcast-enabled", false);
                                    this.plugin.config.save();
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&aSiaran bacaan berhasil dinonaktifkan!"));
                                } else if (args[1].equalsIgnoreCase("title")) {
                                    this.plugin.config.set("title-broadcast-enabled", false);
                                    this.plugin.config.save();
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&aSiaran judul berhasil dinonaktifkan!"));
                                } else {
                                    sender.sendMessage(TextFormat.colorize("&cPemakaian: /broadcaster off <broadcast|popup|title>"));
                                }
                            } else {
                                if (this.plugin.config.getBoolean("broadcast-enabled") || this.plugin.config.getBoolean("popup-broadcast-enabled", !this.plugin.config.getBoolean("title-broadcast-enabled"))) {
                                    this.plugin.config.set("broadcast-enabled", false);
                                    this.plugin.config.set("popup-broadcast-enabled", false);
                                    this.plugin.config.set("title-broadcast-enabled", false);
                                    this.plugin.config.save();
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&aSiaran berhasil dinonaktifkan!"));
                                } else {
                                    sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&cSiaran sudah dinonaktifkan!"));
                                }
                            }
                        } else {
                            sender.sendMessage(TextFormat.colorize("&cKamu tidak memiliki izin untuk menggunakan perintah ini!"));
                        }
                        break;
                    case "reload":
                        if (sender.hasPermission("broadcaster.reload")) {
                            this.plugin.reloadConfig();
                            this.plugin.broadcastDisable();
                            this.plugin.broadcastEnable();
                            sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&aConfiguration Reloaded."));
                        } else {
                            sender.sendMessage(TextFormat.colorize("&cKamu tidak memiliki izin untuk menggunakan perintah ini!"));
                        }
                        break;
                    case "info":
                        if (sender.hasPermission("broadcaster.info")) {
                            sender.sendMessage(TextFormat.colorize("&9[&eInfo&9] &a" + this.plugin.getDescription().getName() + " &bVersi" + this.plugin.getDescription().getVersion()));
                            sender.sendMessage(TextFormat.colorize("&ePencipta: &c" + this.plugin.getDescription().getAuthors()));
                            sender.sendMessage(TextFormat.colorize("&eSitus: &c" + this.plugin.getDescription().getWebsite()));
                        } else {
                            sender.sendMessage(TextFormat.colorize("&cKamu tidak memiliki izin untuk menggunakan perintah ini!"));
                        }
                        break;
                    default:
                        sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&cSubcommand &b" + args[0] + "&c tidak ditemukan. Gunakan &e/broadcaster &cuntuk melihat perintah yang tersedia"));
                        break;
                }
            } else {
                sender.sendMessage(TextFormat.colorize("&7- &aPerintah Yang Tersedia &7-"));
                sender.sendMessage(TextFormat.colorize("&e/broadcaster on <broadcast|popup> &7- &aAktifkan pesan siaran"));
                sender.sendMessage(TextFormat.colorize("&e/broadcaster off <broadcast|popup> &7- &aNonaktifkan pesan siaran"));
                sender.sendMessage(TextFormat.colorize("&e/broadcaster info &7- &aTampilkan informasi tentang plugin ini"));
                sender.sendMessage(TextFormat.colorize("&e/broadcaster reload &7- &aMuat ulang konfigurasi"));
                sender.sendMessage(TextFormat.colorize("&e/sendmessage &7- &aKirim pesan ke pemain yang ditentukan atau gunakan * untuk semua pemain"));
                sender.sendMessage(TextFormat.colorize("&e/sendpopup &7- &aKirim bacaan ke pemain yang ditentukan atau gunakan * untuk semua pemain"));
                sender.sendMessage(TextFormat.colorize("&e/sendtitle &7- &aKirim judul ke pemain yang ditentukan atau gunakan * untuk semua pemain"));
            }
        }
        return true;
    }
}