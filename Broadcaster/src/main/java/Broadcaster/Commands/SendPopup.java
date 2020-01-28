package Broadcaster.Commands;

import Broadcaster.Broadcaster;
import Broadcaster.Tasks.PopupDurationTask;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class SendPopup extends Command {

    private Broadcaster plugin;

    public SendPopup(Broadcaster plugin) {
        super("sendpopup", "Kirim bacaan ke pemain yang ditentukan atau gunakan * untuk semua pemain", "/sendpopup <player|*> <Pesan>");
        this.setAliases(new String[]{"sp", "spop"});
        this.setPermission("broadcaster.sendpopup");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(TextFormat.colorize("&cKamu tidak memiliki izin untuk menggunakan perintah ini!"));
        } else {
            if (args.length > 1) {
                if (args[0].equals("*")) {
                    if (sender instanceof CommandSender) {
                        this.plugin.getServer().getScheduler().scheduleRepeatingTask(new PopupDurationTask(this.plugin, this.plugin.popupbyConsole(sender, this.plugin.getMessagefromArray(args)), null, this.plugin.getConfig().getInt("popup-duration")), 10);
                    } else if (sender instanceof Player) {
                        this.plugin.getServer().getScheduler().scheduleRepeatingTask(new PopupDurationTask(this.plugin, this.plugin.popupbyPlayer((Player) sender, this.plugin.getMessagefromArray(args)), null, this.plugin.getConfig().getInt("popup-duration")), 10);
                    }
                } else {
                    if (this.plugin.getServer().getPlayerExact(args[0]) != null) {
                        Player receiver = this.plugin.getServer().getPlayerExact(args[0]);
                        if (sender instanceof CommandSender) {
                            this.plugin.getServer().getScheduler().scheduleRepeatingTask(new PopupDurationTask(this.plugin, this.plugin.popupbyConsole(sender, this.plugin.getMessagefromArray(args)), receiver, this.plugin.getConfig().getInt("popup-duration")), 10);
                        } else if (sender instanceof Player) {
                            this.plugin.getServer().getScheduler().scheduleRepeatingTask(new PopupDurationTask(this.plugin, this.plugin.popupbyPlayer((Player) sender, this.plugin.getMessagefromArray(args)), receiver, this.plugin.getConfig().getInt("popup-duration")), 10);
                        }
                    } else {
                        sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&cPemain tidak ditemukan"));
                    }
                }
            } else {
                sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&cPemakaian: /sp <player|*> <Pesan>"));
            }
        }
        return true;
    }
}