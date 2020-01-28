package Fahrul8676.event;

import cn.nukkit.plugin.Plugin;
import Fahrul8676.OtuEntry;

public class RunaRemoveEvent extends RunaEvent {
    public RunaRemoveEvent(Plugin plugin, OtuEntry entry) {
        super(plugin, entry);
    }

    public String getName() {
        return this.entry.getName();
    }
}
