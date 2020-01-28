package Fahrul8676.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import Fahrul8676.TempBanMute;
import Fahrul8676.managers.MuteManager;
import org.bson.Document;

public class UnmuteCommand extends CommandManager {

    private TempBanMute plugin;

    public UnmuteCommand(TempBanMute plugin) {
        super(plugin, "tempunmute", "Batalkan bisu pemain.", "/tempunmute");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.hasPermission("tempbanmute.command.tempunmute")) {
            if (args.length == 1) {
                String player = args[0];
                if (TempBanMute.getInstance().getConfig().getBoolean("MongoDB")) {
                    Document document = new Document("name", player);
                    Document found = (Document) TempBanMute.getInstance().getMuteCollection().find(document).first();
                    if (found == null) {
                        sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("PlayerNotMuted").replace("&", "§"));
                        return true;
                    }
                } else {
                    Config bans = new Config(TempBanMute.getInstance().getDataFolder() + "/mutes.yml", Config.YAML);
                    if (!bans.exists("Player." + player)) {
                        sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("PlayerNotMuted").replace("&", "§"));
                        return true;
                    }
                }
                MuteManager.unMute(player);
                Player online = TempBanMute.getInstance().getServer().getPlayer(player);
                if (online != null) {
                    TempBanMute.getInstance().mutedCache.remove(online);
                }
                sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("UnmuteSuccess").replace("&", "§").replace("%player%", player));
            } else {
                sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("Usage.UnmuteCommand").replace("&", "§"));
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("NoPermission").replace("&", "§"));
        }
        return false;
    }
}