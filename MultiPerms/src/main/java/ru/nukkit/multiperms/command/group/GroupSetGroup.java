package ru.nukkit.multiperms.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.WorldParam;

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "setgroup|setgrp|sgrp|sg", "\\S+"}, permission = "multiperms.admin.groupsetgroup", description = Message.CMD_GROUP_ADDGROUP)
public class GroupSetGroup extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id1 = args[0];
        WorldParam wp = new WorldParam(args, 2);
        if (!Groups.exist(id1)) Message.GROUP_SETGROUP_NOTEXIST.print(sender, id1);
        if (!Groups.exist(wp.param)) Message.GROUP_SETGROUP_NOTEXIST.print(sender, wp.param);
        Groups.setGroup(id1, wp);
        return wp.message(Message.GROUP_SETGROUP_OK, Message.GROUP_SETGROUPW_OK).print(sender, id1, wp.param, wp.world);
    }
}
