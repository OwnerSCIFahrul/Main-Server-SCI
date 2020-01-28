package Fahrul8676.event;

import cn.nukkit.event.Cancellable;
import cn.nukkit.plugin.Plugin;
import Fahrul8676.OtuEntry;

public class OtuEvent extends OtuPluginEvent implements Cancellable {

    protected OtuEntry entry;

    public OtuEvent(Plugin plugin, OtuEntry entry) {
        super(plugin);

        this.entry = entry;
    }

    public OtuEntry getEntry() {
        return this.entry;
    }

    public void setEntry(OtuEntry entry) {
        this.entry = entry;
    }

    public int getMode() {
        return this.entry.getMode();
    }
}
