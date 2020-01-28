package Fahrul8676.essentials.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

import Fahrul8676.essentials.Main;

public class CommandKickallplayer extends EssentialsCommand {

    public CommandKickallplayer(Main plugin) {
        super(plugin, "kickallplayer", "Tendang Semua Pemain Dari Server", null, new String[]{"kickallplayer"});
        setPermission("essentials.kickallplayer");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!testPermission(sender)) return true;

        if(sender.getServer().getOnlinePlayers().values().isEmpty()) {
            sender.sendMessage(f("<gold>Tidak ada pemain di <lightpurple>server."));
            return true;
        }

        for(Player p : sender.getServer().getOnlinePlayers().values()) {
            if(p != sender && !p.isOp()) {
                p.kick("<red>Ditendang dari <lightpurple>server", false);
            }
        }
        sender.getServer().broadcastMessage(f("<gold>Semua pemain telah ditendang oleh <yellow>" + sender.getName()));
        return true;
    }
}
