package ru.nukkit.multiperms.command.user;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.WorldParam;


@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = {"\\S+", "setgroup|setgrp|sgrp|sg", "\\S+"}, permission = "multiperms.admin.usersetgroup", description = Message.CMD_USER_SETGROUP)
public class UserSetGroup extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String userName = args[0];
        WorldParam wp = new WorldParam(args, 2);
        if (!Groups.exist(wp.param)) return Message.USER_SETGROUP_NOTEXIST.print(sender, userName, wp.param);
        Users.setGroup(userName, wp);
        wp.message(Message.USER_SETGROUP_OK_INFORM, Message.USER_SETGROUPW_OK_INFORM).print(Server.getInstance().getPlayerExact(userName), wp.param, wp.world);
        return wp.message(Message.USER_SETGROUP_OK, Message.USER_SETGROUPW_OK).print(sender, userName, wp.param, wp.world);
    }
}
