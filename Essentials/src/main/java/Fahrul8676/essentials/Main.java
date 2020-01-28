package Fahrul8676.essentials;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandMap;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;

import static cn.nukkit.level.Level.TIME_DAY;
import static cn.nukkit.level.Level.TIME_NIGHT;
import static cn.nukkit.utils.TextFormat.*;
import Fahrul8676.essentials.commands.*;

public class Main extends PluginBase {

    public void onEnable() {

        loadCommands();

        getServer().getLogger().info(GREEN + "Essentials Buatan Fahrul8676 Memuat!");

        getServer().getLogger().info("-----------------------");
        getServer().getLogger().info("|     Diaktifkan!      |");
        getServer().getLogger().info("|     Essentials    |");
        getServer().getLogger().info("|        oleh          |");
        getServer().getLogger().info("|     Fahrul8676     |");
        getServer().getLogger().info("-----------------------");

        this.getLogger().info(String.valueOf(this.getDataFolder().mkdirs()));

        this.saveResource("rules.txt");
    }

    public void onDisable() {


        getServer().getLogger().info("-----------------------");
        getServer().getLogger().info("|    Dinonatifkan!      |");
        getServer().getLogger().info("|     Essentials    |");
        getServer().getLogger().info("|        oleh          |");
        getServer().getLogger().info("|     Fahrul8676     |");
        getServer().getLogger().info("-----------------------");

    }

    private void loadCommands() {
        CommandMap map = getServer().getCommandMap();

        // Normal commands
        map.register("kickallplayer", new CommandKickallplayer(this));
        map.register("rules", new CommandRules(this));
        map.register("broadcast", new CommandBroadcast(this));

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = null;
        if ((sender instanceof Player)) {
            player = (Player) sender;
        }
        if (cmd.getName().equalsIgnoreCase("clearglobalchat")) {
            if (player == null) {
                sender.sendMessage("Anda harus menjadi pemain");
                return true;
            }
            if (player != null) {
                if (player.hasPermission("essentials.clearglobalchat")) {
                    for (int i = 0; i < 121; i++) {
                        Server.getInstance().broadcastMessage("");
                    }
                }
                Server.getInstance().broadcastMessage("Obrolan Global Dihapus");
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("clearprivatechat")) {
            if (player == null) {
                sender.sendMessage("Anda harus menjadi pemain");
                return true;
            }
            if (player != null) {
                if (player.hasPermission("essentials.clearprivatechat")) {
                    for (int i = 0; i < 121; i++) {
                        sender.sendMessage("");
                    }
                    ;
                }
                sender.sendMessage("Anda Mengosongkan Obrolan Anda");
                return true;
            }

        }
        if (cmd.getName().equalsIgnoreCase("timeday")) {
            if (player == null) {
                sender.sendMessage("Anda harus menjadi pemain");
                return true;
            }
            if (player != null) {
                if (player.hasPermission("essentials.timeday")) {
                    player.getLevel().setTime(TIME_DAY);
                    sender.sendMessage("Waktu Diatur Ke Siang Hari");
                }
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("timenight")) {
            if (player == null) {
                sender.sendMessage("Anda harus menjadi pemain");
                return true;
            }
            if (player != null) if (player.hasPermission("essentials.timenight")) {
                player.getLevel().setTime(TIME_NIGHT);
                sender.sendMessage("Waktu Diatur Ke Malam Hari");
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("weathersun")) {
            if (player == null) {
                sender.sendMessage("Anda harus menjadi pemain");
                return true;
            }
            if (player != null) if (player.hasPermission("essentials.weathersun")) {
                player.getLevel().setRainTime(0);
                sender.sendMessage("Cuaca Diatur Ke Cerah Hari");
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("weatherrain")) {
            if (player == null) {
                sender.sendMessage("Anda harus menjadi pemain");
                return true;
            }
            if (player != null) if (player.hasPermission("essentials.weatherrain")) {
                player.getLevel().setRainTime(100);
                sender.sendMessage("Cuaca Diatur Ke Badai Hari");
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("stoptime")) {
            if (player == null) {
                sender.sendMessage("Anda harus menjadi pemain");
                return true;
            }
            if (player != null) if (player.hasPermission("essentials.stoptime")) {
                player.getLevel().stopTime();
                sender.sendMessage("Anda Menghentikan Waktu");
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("starttime")) {
            if (player == null) {
                sender.sendMessage("Anda harus menjadi pemain");
                return true;
            }
            if (player != null) if (player.hasPermission("essentials.starttime")) {
                player.getLevel().startTime();
                sender.sendMessage("Anda Memulaikan Waktu");
                return true;
            }
        }

        return false;
    }
}