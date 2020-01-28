package Fahrul8676.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import Fahrul8676.TempBanMute;
import Fahrul8676.managers.BanManager;
import Fahrul8676.utils.BanUtil;
import Fahrul8676.utils.MessageUtil;
import org.bson.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BanCommand extends CommandManager {

    private TempBanMute plugin;

    public BanCommand(TempBanMute plugin) {
        super(plugin, "tempban", "Blokirkan waktu pemain.", "/tempban");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.hasPermission("tempbanmute.command.tempban")) {
            if (args.length == 2) {
                String player = args[0];
                String number = args[1];
                try {
                    int n = Integer.parseInt(args[1]);
                    int seconds = plugin.getConfig().getInt("BanReasons." + number + ".Seconds");
                    String reason = plugin.getConfig().getString("BanReasons." + number + ".Reason");
                    int max = plugin.getConfig().getInt("BanReasons.Count");
                    if (TempBanMute.getInstance().getConfig().getBoolean("MongoDB")) {
                        Document document = new Document("name", player);
                        Document found = (Document) TempBanMute.getInstance().getBanCollection().find(document).first();
                        if (found != null) {
                            sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("AlreadyBanned").replace("&", "§"));
                            return true;
                        }
                    } else {
                        Config bans = new Config(TempBanMute.getInstance().getDataFolder() + "/bans.yml", Config.YAML);
                        if (bans.exists("Player." + player)) {
                            sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("AlreadyBanned").replace("&", "§"));
                            return true;
                        }
                    }
                    if (!(n > max)) {
                        if (n >= 1) {
                            BanManager.setBanned(player, reason, getBanID(), sender.getName(), getDate(), seconds);
                            sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("BanSuccess").replace("%player%", player).replace("&", "§"));
                            Player target = TempBanMute.getInstance().getServer().getPlayer(player);
                            BanUtil banUtil = TempBanMute.getInstance().banManager.getPlayer(player);
                            if (target != null) {
                                target.kick(MessageUtil.banScreen(banUtil.getReason(), banUtil.getId(), BanManager.getRemainingTime(banUtil.getEnd()), banUtil.getBanner()), false);
                            }
                        } else {
                            sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("Reasonlimit").replace("&", "§"));
                        }
                    } else {
                        sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("Reasonlimit").replace("&", "§"));
                    }
                } catch (NumberFormatException var1) {
                    sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("MustNumber").replace("&", "§").replace("%max%", plugin.getConfig().getString("BanReasons.Count")));
                    var1.getMessage();
                }
            } else {
                MessageUtil.sendBanHelp(sender, TempBanMute.getInstance());
                sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("Usage.BanCommand").replace("&", "§"));
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("Prefix").replace("&", "§") + plugin.getConfig().getString("NoPermission").replace("&", "§"));
        }
        return false;
    }

    private String getBanID() {
        String string = "";
        int lastrandom = 0;
        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            int rand = random.nextInt(9);
            while (rand == lastrandom) {
                rand = random.nextInt(9);
            }
            lastrandom = rand;
            string = string + rand;
        }
        return string;
    }

    private String getDate() {
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
        String now1 = dateFormat.format(now);
        return now1;
    }
}
