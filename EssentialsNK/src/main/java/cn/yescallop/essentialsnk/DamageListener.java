package cn.yescallop.essentialsnk;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;

public class DamageListener
implements Listener {
    EssentialsAPI plugin;

    public DamageListener(EssentialsAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=false)
    public void onEntityDamage(EntityDamageEvent event) {
        Entity player;
        if (event instanceof EntityDamageByEntityEvent && (player = ((EntityDamageByEntityEvent)event).getDamager()) instanceof Player) {
            if (((Player)player).getGamemode() == 1) {
            if (this.plugin.getPlayerGod((Player)player)) {
                Language.translate("commands.god.blockdamage",(CommandSender)((Player)player));
                event.setCancelled();
                return;
            }
            }
        }
    }
}

