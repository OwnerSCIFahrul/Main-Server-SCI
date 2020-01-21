package ru.nukkit.multiperms.command.user;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.WorldParam;

@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = {"\\S+", "removeperm|rmvperm|rperm|rp", "\\S+"}, permission = "multiperms.admin.userremoveperm", description = Message.CMD_USER_REMOVEPERM)
public class UserRemovePerm extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String userName = args[0];
        Users.isRegistered(userName).whenComplete((registered, e) -> {
            if (e != null) {
                e.printStackTrace();
            } else {
                if (registered) {
                    WorldParam wp = new WorldParam(args[2]);
                    if (!Users.isPermissionSet(userName, wp)) {
                        wp.message(Message.USER_REMOVEPERM_NOTSET, Message.USER_REMOVEPERMW_NOTSET).print(sender, wp.param, userName, wp.world);
                    } else {
                        Users.removePermission(userName, wp);
                        wp.message(Message.USER_REMOVEPERM_OK_INFORM, Message.USER_REMOVEPERMW_OK_INFORM).print(Server.getInstance().getPlayerExact(userName), wp.param, wp.world);
                        wp.message(Message.USER_REMOVEPERM_OK, Message.USER_REMOVEPERMW_OK).print(sender, wp.param, userName, wp.world);
                    }
                } else {
                    Message.USER_REMOVEPERM_NOTFOUND.print(sender, userName);
                }
            }
        });
        return true;
    }
}