package ru.nukkit.multiperms.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.WorldParam;

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "addgroup|addgrp|agrp|ag", "\\S+"}, permission = "multiperms.admin.groupaddgroup", description = Message.CMD_GROUP_ADDGROUP)
public class GroupAddGroup extends Cmd {


    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id1 = args[0];
        WorldParam wp = new WorldParam(args, 2);
        if (!Groups.exist(id1)) Message.GROUP_ADDGROUP_NOTEXIST.print(sender, id1);
        if (!Groups.exist(wp.param)) Message.GROUP_ADDGROUP_NOTEXIST.print(sender, wp.param);
        Groups.addGroup(id1, wp);
        Message m = wp.world == null ? Message.GROUP_ADDGROUP_OK : Message.GROUP_ADDGROUPW_OK;
        return m.print(sender, id1, wp.param, wp.world);
    }
}
