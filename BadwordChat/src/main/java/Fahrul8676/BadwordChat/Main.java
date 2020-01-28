package Fahrul8676.BadwordChat;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.plugin.PluginBase;

import java.util.Iterator;
import java.util.List;

public class Main extends PluginBase implements Listener {
    public List<String> words;

    public Main() {
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getLogger().info("§aBadwordChat Diaktifkan!");
        this.initConfig();
        this.loadCfg();
    }

    public void onDisable() {
        this.getLogger().info("§cBadwordChat Dinonatifkan!");
    }

    public void initConfig() {
        this.saveResource("config.yml");
    }

    public void loadCfg() {
        this.reloadConfig();
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        this.words = this.getConfig().getList("words");
        Iterator var2 = this.words.iterator();

        while (var2.hasNext()) {
            String w = (String) var2.next();
            Player p = event.getPlayer();

            if (event.getMessage().toUpperCase().contains(w.toUpperCase())) {
                if (this.getConfig().getString("type").equalsIgnoreCase("cencor")) {
                    event.getPlayer().chat((new String(new char[w.length()])).replace("\u0000", "*"));
                    event.setCancelled();

                }

                if (this.getConfig().getString("type").equalsIgnoreCase("blocker")) {
                    p.sendMessage(getServer().getConfig().getString("blocker", getConfig().getString("reply").replace("&", "§")));
                    event.setCancelled();

                }
                if (this.getConfig().getString("type").equalsIgnoreCase("command")) {
                    event.getPlayer().sendMessage(getConfig().getString("message").replace("&", "§"));
                    event.getPlayer().sendChat(getConfig().getString("broadcast").replace("&", "§").replace("%m", event.getMessage()).replace("%p", p.getDisplayName()));
                    String cmd = this.getConfig().get("command").toString();
                    Server.getInstance().dispatchCommand(new ConsoleCommandSender(), cmd.replace("&", "§").replace("@p", p.getName()));
                    event.setCancelled();

                }
            }
        }
    }
}