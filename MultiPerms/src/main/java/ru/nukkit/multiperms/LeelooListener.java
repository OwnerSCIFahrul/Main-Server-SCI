package ru.nukkit.multiperms;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.player.PlayerTeleportEvent;
import ru.nukkit.multiperms.permissions.Users;

public class LeelooListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLogin(PlayerLoginEvent event) {
        Users.loadUser(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onQuit(PlayerQuitEvent event) {
        Users.closeUser(event.getPlayer());
    }

    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getFrom().getLevel().equals(event.getTo().getLevel())) return;
        Users.recalculatePermissions(event.getPlayer().getName());
    }
}