package com.creeperface.nukkitx.placeholders.utils.lp;

/**
 * Encapsulates platform specific placeholder behaviour
 */
public interface PlaceholderPlatform {

    /**
     * Format a unix timestamp according to the placeholder platforms rules.
     *
     * @param time the time
     * @return a formatted version of the time
     */
    String formatTime(int time);

    /**
     * Format a boolean according to the placeholder platforms rules.
     *
     * @param value the boolean value
     * @return a formatted representation of the boolean
     */
    String formatBoolean(boolean value);

}
