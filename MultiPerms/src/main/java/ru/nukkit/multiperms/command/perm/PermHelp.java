package ru.nukkit.multiperms.command.perm;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.command.Commander;
import ru.nukkit.multiperms.util.Message;

@CmdDefine(command = "perm", alias = "permission", allowConsole = true, subCommands = "help", permission = "multiperms.admin.permhelp", description = Message.CMD_PERM_HELP)
public class PermHelp extends Cmd {

    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        int page = args.length == 2 && args[1].matches("\\d+") ? Integer.parseInt(args[1]) : 1;
        Commander.printHelp(sender, page);
        return true;
    }
}
