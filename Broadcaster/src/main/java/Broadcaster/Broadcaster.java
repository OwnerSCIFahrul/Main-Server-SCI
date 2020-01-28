package Broadcaster;

import Broadcaster.Commands.Commands;
import Broadcaster.Commands.SendMessage;
import Broadcaster.Commands.SendPopup;
import Broadcaster.Commands.SendTitle;
import Broadcaster.Tasks.PopupTask;
import Broadcaster.Tasks.Task;
import Broadcaster.Tasks.TitleTask;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Broadcaster extends PluginBase {

    public static String PREFIX = "&9[&eBroadcaster&9] ";

    private TaskHandler task;
    private TaskHandler ptask;
    private TaskHandler ttask;
    public Config config;

    public void onEnable() {
        this.saveDefaultConfig();
        config = getConfig();
        this.broadcastCommand();
        this.broadcastEnable();
        this.getLogger().info(TextFormat.colorize("&aBroadcaster berhasil diaktifkan!"));
    }

    private void broadcastCommand() {
        this.getServer().getCommandMap().register("broadcaster", new Commands(this));
        this.getServer().getCommandMap().register("broadcaster", new SendMessage(this));
        this.getServer().getCommandMap().register("broadcaster", new SendPopup(this));
        this.getServer().getCommandMap().register("broadcaster", new SendTitle(this));
    }

    public void broadcastEnable() {
        int time = config.getInt("time") * 20;
        int ptime = config.getInt("popup-time") * 20;
        int ttime = config.getInt("title-time") * 20;
        task = this.getServer().getScheduler().scheduleRepeatingTask(new Task(this), time);
        ptask = this.getServer().getScheduler().scheduleRepeatingTask(new PopupTask(this), ptime);
        ttask = this.getServer().getScheduler().scheduleRepeatingTask(new TitleTask(this), ttime);
    }

    public void broadcastDisable() {
        this.task.cancel();
        this.ptask.cancel();
        this.ttask.cancel();
    }

    public String broadcast(String message) {
        message = message.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        message = message.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        message = message.replace("{PREFIX}", config.getString("prefix"));
        message = message.replace("{SUFFIX}", config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("datetime-format"));
        message = message.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize(message);
    }

    public String messagebyPlayer(Player player, String message) {
        String format = config.getString("sendmessage-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize(format);
    }

    public String messagebyConsole(CommandSender player, String message, Player p) {
        String format = config.getString("sendmessage-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize(format);
    }

    public String broadcastPopup(String message) {
        message = message.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        message = message.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        message = message.replace("{PREFIX}", config.getString("prefix"));
        message = message.replace("{SUFFIX}", config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("datetime-format"));
        message = message.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize(message);
    }

    public String popupbyPlayer(Player player, String message) {
        String format = config.getString("sendmessage-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize(format);
    }

    public String popupbyConsole(CommandSender player, String message) {
        String format = config.getString("sendpopup-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize(format);
    }

    public String broadcastTitle(String message) {
        message = message.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        message = message.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        message = message.replace("{PREFIX}", config.getString("prefix"));
        message = message.replace("{SUFFIX}", config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("datetime-format"));
        message = message.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize(message);
    }

    public String titlebyPlayer(Player player, String message) {
        String format = config.getString("sendmessage-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize(format);
    }

    public String titlebyConsole(CommandSender player, String message) {
        String format = config.getString("sendtitle-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize(format);
    }
    public String getMessagefromArray(String[] array) {
        String msg = "";
        for (int i = 1; i < array.length; i++) {
            msg += array[i] + " ";
        }
        if (msg.length() > 0) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }
}