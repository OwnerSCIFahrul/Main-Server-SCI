package ru.nukkit.multiperms.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.WorldParam;

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "removegroup|rmvgrp|rgrp|rg", "\\S+"}, permission = "multiperms.admin.groupremovegroup", description = Message.CMD_GROUP_REMOVEGROUP)
public class GroupRemoveGroup extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id1 = args[0];
        WorldParam wp = new WorldParam(args, 2);
        if (!Groups.exist(id1)) Message.GROUP_REMOVEGROUP_NOTEXIST.print(sender, id1);
        if (!Groups.exist(wp.param)) Message.GROUP_REMOVEGROUP_NOTEXIST.print(sender, wp.param);
        Groups.removeGroup(id1, wp);
        Message m = wp.world == null ? Message.GROUP_REMOVEGROUP_OK : Message.GROUP_REMOVEGROUPW_OK;
        return m.print(sender, id1, wp.param, wp.world);
    }
}
