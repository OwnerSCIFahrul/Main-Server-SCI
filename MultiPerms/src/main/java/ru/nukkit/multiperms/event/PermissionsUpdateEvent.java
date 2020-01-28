package ru.nukkit.multiperms.event;

import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;

public class PermissionsUpdateEvent extends Event {

    private String user;

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }


    public PermissionsUpdateEvent() {
        this.user = null;
    }

    public PermissionsUpdateEvent(String userName) {
        this.user = userName;
    }


    /**
     * Get user, which permissions was changed.
     * Returns null permissions updated for all users.
     *
     * @return
     */
    public String getUser() {
        return this.user;
    }


    /**
     * Check is Permission update related to single player or it was a mass update
     * (for example changing groups)
     *
     * @return
     */
    public boolean isMassUpdate() {
        return user == null;
    }
}