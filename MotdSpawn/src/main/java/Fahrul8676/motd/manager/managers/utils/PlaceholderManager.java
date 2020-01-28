package Fahrul8676.motd.manager.managers.utils;

import Fahrul8676.motd.MotdSpawn;
import Fahrul8676.motd.manager.managers.Manager;
import cn.nukkit.plugin.Plugin;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;

public class PlaceholderManager implements Manager {
  private PlaceholderAPI placeholderAPI;

  public PlaceholderManager() {
  }

  public void onEnable(MotdSpawn instance) {
    Plugin plugin = instance.getServer().getPluginManager().getPlugin("PlaceholderAPI");
    if (plugin.getConfig().getBoolean("motd.use-placeholder")) {
      if (plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
        plugin.getServer().getLogger().critical("Tidak ada PlaceholderAPI ditemukan! Silakan pasang plugin PlaceholderAPI (lihat config.yml)");
        plugin.getServer().getPluginManager().disablePlugin(plugin);
      } else {
        placeholderAPI = PlaceholderAPI.getInstance();
      }
    }
  }

  public void onDisable(MotdSpawn instance) {
  }

  public PlaceholderAPI getPlaceholderAPI() {
    return this.placeholderAPI;
  }
}
