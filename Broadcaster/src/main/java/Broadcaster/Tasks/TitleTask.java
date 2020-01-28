package Broadcaster.Tasks;

import Broadcaster.Broadcaster;
import cn.nukkit.scheduler.PluginTask;

import java.util.ArrayList;
import java.util.List;

public class TitleTask extends PluginTask<Broadcaster> {

    private Broadcaster plugin;
    private int length;

    public TitleTask(Broadcaster plugin) {
        super(plugin);
        this.plugin = plugin;
        this.length = -1;
    }

    @Override
    public void onRun(int currentTick) {
        if (this.plugin.getConfig().getBoolean("title-broadcast-enabled", true)) {
            this.length = this.length + 1;
            List titles;
            try {
                titles = this.plugin.getConfig().getList("titles");
            } catch (Exception e) {
                titles = new ArrayList<>();
            }
            String title = (String) titles.get(this.length);
            if (this.length == titles.size() - 1) this.length = -1;
            this.plugin.getServer().getScheduler().scheduleRepeatingTask(new TitleDurationTask(this.plugin, this.plugin.broadcast(title), null, this.plugin.getConfig().getInt("title-duration")), 10);
        }
    }
}