package com.creeperface.nukkitx.placeholders.utils.lp;

import cn.nukkit.Player;

/**
 * Generic interface for a object that handles placeholder requests
 */
@FunctionalInterface
public interface PlaceholderProvider {

    /**
     * Handles a placeholder request
     *
     * @param player      the player placeholders are being requested for
     * @param placeholder the placeholder string
     * @return the resultant value
     */
    String onPlaceholderRequest(Player player, String placeholder);

}
