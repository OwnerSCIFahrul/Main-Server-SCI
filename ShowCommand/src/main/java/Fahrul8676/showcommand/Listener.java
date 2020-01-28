package Fahrul8676.showcommand;

import cn.nukkit.event.EventPriority;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;

public class Listener implements cn.nukkit.event.Listener
{
    private Main main;

    public Listener(final Main main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void getcommandmessage(final PlayerCommandPreprocessEvent e) {
            this.main.getServer().broadcast("§e" + e.getPlayer().getName() + " §bMenggunakan Perintah §c" + e.getMessage(), "showcommand.console");
        }
    }