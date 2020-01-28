package Fahrul8676.motd.manager.managers;

import Fahrul8676.motd.MotdSpawn;
import cn.nukkit.event.Listener;

public interface Manager extends Listener {
    void onEnable(MotdSpawn var1);

    void onDisable(MotdSpawn var1);
}

