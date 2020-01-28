package Broadcaster.Tasks;

import Broadcaster.Broadcaster;
import cn.nukkit.scheduler.PluginTask;

import java.util.ArrayList;
import java.util.List;

public class PopupTask extends PluginTask<Broadcaster> {

    private Broadcaster plugin;
    private int length;

    public PopupTask(Broadcaster plugin) {
        super(plugin);
        this.plugin = plugin;
        this.length = -1;
    }

    @Override
    public void onRun(int currentTick) {
        if (this.plugin.getConfig().getBoolean("popup-broadcast-enabled", true)) {
            this.length = this.length + 1;
            List popups;
            try {
                popups = this.plugin.getConfig().getList("popups");
            } catch (Exception e) {
                popups = new ArrayList<>();
            }
            String popup = (String) popups.get(this.length);
            if (this.length == popups.size() - 1) this.length = -1;
            this.plugin.getServer().getScheduler().scheduleRepeatingTask(new PopupDurationTask(this.plugin, this.plugin.broadcast(popup), null, this.plugin.getConfig().getInt("popup-duration")), 10);
        }
    }
}