package ru.nukkit.multiperms.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.WorldParam;

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "addperm|setperm|aperm|sp", "\\S+"}, permission = "multiperms.admin.groupsetperm", description = Message.CMD_GROUP_SETPERM)
public class GroupSetPerm extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[0];
        WorldParam wp = new WorldParam(args, 2);
        if (!Groups.exist(id)) return Message.GROUP_SETPERM_NOTEXIST.print(sender, id);
        Groups.setPerm(id, wp);
        return wp.message(Message.GROUP_SETPERM_OK, Message.GROUP_SETPERMW_OK).print(sender, id, wp.param, wp.world);
    }
}
