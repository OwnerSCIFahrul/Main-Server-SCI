package Fahrul8676.motd;

import cn.nukkit.utils.Config;

import java.util.List;

public class ConfigReader {
    private final MotdServer plugin;
    private static Config config;

    public ConfigReader(MotdServer plugin) {
        this.plugin = plugin;
        config = plugin.pluginConfig;
    }

    // Server MOTD

    public boolean isServerMotdEnabled() {
        return config.getBoolean("Server-Motd.enabled");
    }

    public String getServerMotdType() {
        return config.getString("Server-Motd.type");
    }

    public int getServerMotdChangeInterval() {
        return config.getInt("Server-Motd.change-interval");
    }

    public List<String> getServerMotdMessages() {
        return config.getStringList("Server-Motd.messages");
    }

    // Join Motd

    public boolean isJoinMotdEnabled() {
        return config.getBoolean("Join-Motd.enabled");
    }

    public String getJoinMotdType() {
        return config.getString("Join-Motd.type");
    }

    public String getJoinMotdMessage() {
        return config.getString("Join-Motd.message");
    }

    public String getJoinMotdSubMessage() {
        return config.getString("Join-Motd.sub-message");
    }
}
