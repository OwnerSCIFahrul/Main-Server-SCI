package Broadcaster.Tasks;

import Broadcaster.Broadcaster;
import cn.nukkit.Player;
import cn.nukkit.scheduler.PluginTask;

public class TitleDurationTask extends PluginTask<Broadcaster> {

    private Broadcaster plugin;
    private Player player;
    private String message;
    private int duration;
    private int current;

    public TitleDurationTask(Broadcaster plugin, String message, Player player, int duration) {
        super(plugin);
        this.plugin = plugin;
        this.player = player;
        this.message = message;
        this.duration = duration;
        this.current = 0;
    }

    @Override
    public void onRun(int currentTick) {
        if (this.current <= this.duration) {
            if (this.player != null) {
                message = message.replace("{PLAYER}", this.player.getName());
                this.player.sendTitle(this.plugin.broadcastTitle(this.message));
            } else {
                for (Player player : this.plugin.getServer().getOnlinePlayers().values()) {
                    message = message.replace("{PLAYER}", "*");
                    player.sendTitle(this.plugin.broadcastTitle(this.message));

                }
            }
        } else {
            this.plugin.getServer().getScheduler().cancelTask(this.getTaskId());
        }
        this.current += 1;
    }
}