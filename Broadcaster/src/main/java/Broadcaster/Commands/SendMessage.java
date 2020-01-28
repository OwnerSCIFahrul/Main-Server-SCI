package Broadcaster.Commands;

import Broadcaster.Broadcaster;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class SendMessage extends Command {

    private Broadcaster plugin;

    public SendMessage(Broadcaster plugin) {
        super("sendmessage", "Kirim pesan ke pemain yang ditentukan atau gunakan * untuk semua pemain", "/sendmessage <player|*> <Pesan>");
        this.setAliases(new String[]{"sm", "smsg"});
        this.setPermission("broadcaster.sendmessage");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(TextFormat.colorize("&cKamu tidak memiliki izin untuk menggunakan perintah ini!"));
        } else {
            if (args.length > 1) {
                if (args[0].equals("*")) {
                    if (sender instanceof CommandSender) {
                        for (Player onlineplayers : this.plugin.getServer().getOnlinePlayers().values()) {
                            onlineplayers.sendMessage(TextFormat.colorize(this.plugin.messagebyConsole(sender, this.plugin.getMessagefromArray(args), null)));
                        }
                    } else if (sender instanceof Player) {
                        for (Player onlineplayers : this.plugin.getServer().getOnlinePlayers().values()) {
                            onlineplayers.sendMessage(TextFormat.colorize(this.plugin.messagebyPlayer((Player) sender, this.plugin.getMessagefromArray(args))));
                        }
                    }
                } else {
                    if (this.plugin.getServer().getPlayerExact(args[0]) != null) {
                        Player receiver = this.plugin.getServer().getPlayerExact(args[0]);
                        if (sender instanceof CommandSender) {
                            receiver.sendMessage(TextFormat.colorize(this.plugin.messagebyConsole(sender, this.plugin.getMessagefromArray(args), null)));
                        } else if (sender instanceof Player) {
                            receiver.sendMessage(TextFormat.colorize(this.plugin.messagebyPlayer((Player) sender, this.plugin.getMessagefromArray(args))));

                        }
                    } else {
                        sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&cPemain tidak ditemukan"));
                    }
                }
            } else {
                sender.sendMessage(TextFormat.colorize(Broadcaster.PREFIX + "&cPemakaian: /sm <player|*> <Pesan>"));
            }
        }
        return true;
    }
}