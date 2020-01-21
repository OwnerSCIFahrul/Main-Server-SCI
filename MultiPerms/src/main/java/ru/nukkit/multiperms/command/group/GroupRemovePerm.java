package ru.nukkit.multiperms.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.WorldParam;

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "removeperm|rmvperm|rperm|rp", "\\S+"}, permission = "multiperms.admin.groupremoveperm", description = Message.CMD_GROUP_REMOVEPERM)
public class GroupRemovePerm extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[0];
        if (!Groups.exist(id)) return Message.GROUP_REMOVEPERM_NOTEXIST.print(sender, id);
        WorldParam wp = new WorldParam(args, 2);
        if (!Groups.isPermissionSet(id, wp.param))
            return Message.GROUP_REMOVEPERM_PERMUNSET.print(sender, id, wp.param);
        Groups.removePermission(id, wp);
        return wp.message(Message.GROUP_REMOVEPERM_OK, Message.GROUP_REMOVEPERMW_OK).print(sender, id, wp.param, wp.world);
    }
}
