package Fahrul8676.motd.manager.managers.player;

import Fahrul8676.motd.MotdSpawn;
import Fahrul8676.motd.manager.managers.Manager;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.utils.Utils;
import cn.nukkit.Player;
import java.io.IOException;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;

public class MotdManager implements Manager
{
    private MotdSpawn instance;
    private String motd;
    private boolean enabled;

    @Override
    public void onEnable(final MotdSpawn instance) {
        this.instance = instance;

        try {
            this.motd = Utils.readFile(instance.getFileManager().getMotdFile());
            this.enabled = instance.getConfig().getBoolean("messages.enable-motd");
            instance.register((Listener)this);
        }
        catch (IOException e) {
            instance.getLogger().error(String.format("File motd gagal dibaca (%s).", e.getMessage()));
        }
    }

    @Override
    public void onDisable(final MotdSpawn instance) {
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (this.enabled) {
            player.sendMessage(TextFormat.colorize('&', PlaceholderAPI.getInstance().translateString((this.motd), player)));
        }
    }
}