package Fahrul8676.motd.manager.managers.player;

import Fahrul8676.motd.MotdSpawn;
import Fahrul8676.motd.manager.managers.Manager;
import cn.nukkit.event.EventHandler;
import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.utils.TextFormat;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;

public class MessagesManager implements Manager {

    private MotdSpawn instance;
    private boolean enabledJoin;
    private boolean enabledQuit;
    private boolean enabledFirstJoin;

    @Override
    public void onEnable(final MotdSpawn instance) {
        this.instance = instance;
        this.enabledJoin = !instance.getConfig().getString("messages.join-message").equals("");
        this.enabledQuit = !instance.getConfig().getString("messages.leave-message").equals("");
        this.enabledFirstJoin = !instance.getConfig().getString("messages.first_join-message").equals("");
        instance.register((Listener) this);
    }

    @Override
    public void onDisable(MotdSpawn instance) {
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (this.enabledJoin) {
            event.setJoinMessage(TextFormat.colorize('&', PlaceholderAPI.getInstance().translateString((this.instance.getConfig().getString("messages.join-message")), player)));
        }
        if (event.getPlayer().isOp()) {
            if (this.enabledFirstJoin) {
                if (MotdSpawn.config.getBoolean("silentfirstjoin_enabled"))
                    return;
            }
            if (!event.getPlayer().playedBefore) {
                event.getPlayer().getServer().broadcastMessage(TextFormat.colorize('&', PlaceholderAPI.getInstance().translateString((this.instance.getConfig().getString("messages.first_join-message")), player)));
            }
        }
    }
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        if (this.enabledQuit) {
            event.setQuitMessage(TextFormat.colorize('&', PlaceholderAPI.getInstance().translateString((this.instance.getConfig().getString("messages.leave-message")), player)));
        }
    }
}