package Fahrul8676.essentials.commands;

import Fahrul8676.essentials.Main;
import Fahrul8676.essentials.Utils;
import cn.nukkit.command.CommandSender;

public class CommandBroadcast extends EssentialsCommand {
    public CommandBroadcast(Main plugin) {
        super(plugin, "bc", "Siarkan Pesan", "bc");
        setPermission("essentials.broadcast");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;

        if(args.length < 1) {
            sender.sendMessage(f("<red>Pemakaian: /bc <Pesan>"));
            return false;
        }
        sender.getServer().broadcastMessage(f("<gold>[<darkred>Broadcast<gold>] <lime>" + Utils.split(args)));

        return true;
    }

}