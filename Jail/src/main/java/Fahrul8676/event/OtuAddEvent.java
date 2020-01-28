package Fahrul8676.event;

import cn.nukkit.plugin.Plugin;
import Fahrul8676.OtuEntry;

import java.time.OffsetDateTime;

public class OtuAddEvent extends OtuEvent {

    public OtuAddEvent(Plugin plugin, OtuEntry entry) {
        super(plugin, entry);
    }

    public String getName() {
        return this.entry.getName();
    }

    public String getSource() {
        return this.entry.getSource();
    }

    public OffsetDateTime getCreationDate() {
        return this.entry.getCreationDate();
    }
}
