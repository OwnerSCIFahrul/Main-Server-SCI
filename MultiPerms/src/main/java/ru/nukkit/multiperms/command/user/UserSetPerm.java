package ru.nukkit.multiperms.command.user;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.Util;
import ru.nukkit.multiperms.util.WorldParam;

@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = {"\\S+", "setperm|sperm|sp|addperm|aperm|ap", "\\S+"}, permission = "multiperms.admin.usersetperm", description = Message.CMD_USER_SETPERM)
public class UserSetPerm extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String userName = args[0];
        WorldParam wp = new WorldParam(args, 2);
        Users.setPermission(userName, wp);
        wp.message(Message.USER_SETPERM_OK_INFORM, Message.USER_SETPERMW_OK_INFORM).print(Util.getPlayer(userName), wp.param, wp.world);
        return wp.message(Message.USER_SETPERM_OK, Message.USER_SETPERMW_OK).print(sender, userName, wp.param, wp.world);
    }
}
