package Fahrul8676.motd.tasks;

import Fahrul8676.motd.MotdServer;
import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.utils.TextFormat;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;

import java.time.LocalTime;
import java.util.List;

public class ServerMotdTask extends PluginTask<MotdServer> {

    private int seq = 0;

    public ServerMotdTask(MotdServer owner) {
        super(owner);
    }

    @Override
    public void onRun(int i) {
        MotdServer plugin = this.getOwner();
        List<String> allMessages = plugin.configReader.getServerMotdMessages();

        while(true) {
            if (this.seq < allMessages.size()) {
                plugin.getServer().getNetwork().setName(TextFormat.colorize('&', PlaceholderAPI.getInstance().translateString(allMessages.get(this.seq).replace("%h%", String.valueOf(LocalTime.now().getHour())).replace("%m%", String.valueOf(LocalTime.now().getMinute())))));
                this.seq++;
                break;
            } else {
                this.seq = 0;
            }
        }


    }
}
