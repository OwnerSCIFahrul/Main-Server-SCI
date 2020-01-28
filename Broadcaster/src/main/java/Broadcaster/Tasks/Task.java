package Broadcaster.Tasks;

import Broadcaster.Broadcaster;
import cn.nukkit.Server;
import cn.nukkit.scheduler.PluginTask;

import java.util.ArrayList;
import java.util.List;

public class Task extends PluginTask<Broadcaster> {

    private Broadcaster plugin;
    private int length;

    public Task(Broadcaster plugin) {
        super(plugin);
        this.plugin = plugin;
        this.length = -1;
    }

    @Override
    public void onRun(int currentTick) {
        if (this.plugin.getConfig().getBoolean("broadcast-enabled", true)) {
            this.length = this.length + 1;
            List messages;
            try {
                messages = this.plugin.getConfig().getList("messages");
            } catch (Exception e) {
                messages = new ArrayList<>();
            }
            String message = (String) messages.get(this.length);
            if (this.length == messages.size() - 1) this.length = -1;
            Server.getInstance().broadcastMessage(this.plugin.broadcast(message));
        }
    }
}