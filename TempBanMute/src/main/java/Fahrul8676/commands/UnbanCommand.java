package Fahrul8676.commands;

import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import Fahrul8676.TempBanMute;
import Fahrul8676.managers.BanManager;
import org.bson.Document;

public class UnbanCommand extends CommandManager {

    private TempBanMute plugin;

    public UnbanCommand(TempBanMute plugin) {
        super(plugin, "tempunban", "Batalkan blokir pemain.", "/tempunban");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.hasPermission("tempbanmute.command.tempunban")) {
            if (args.length == 1) {
                String player = args[0];
                if (TempBanMute.getInstance().getConfig().getBoolean("MongoDB")) {
                    Document document = new Document("name", player);
                    Document found = (Document) TempBanMute.getInstance().getBanCollection().find(document).first();
                    if (found == null) {
                        sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("PlayerNotBanned").replace("&", "§"));
                        return true;
                    }
                } else {
                    Config bans = new Config(TempBanMute.getInstance().getDataFolder() + "/bans.yml", Config.YAML);
                    if (!bans.exists("Player." + player)) {
                        sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("PlayerNotBanned").replace("&", "§"));
                        return true;
                    }
                }
                BanManager.unBan(player);
                sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("UnbanSuccess").replace("&", "§").replace("%player%", player));
            } else {
                sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("Usage.UnbanCommand").replace("&", "§"));
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("NoPermission").replace("&", "§"));
        }
        return false;
    }
}