package ru.nukkit.multiperms.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.util.Message;

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"create|new", "\\S+"}, permission = "multiperms.admin.groupcreate", description = Message.CMD_GROUP_CREATE)
public class GroupCreate extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[1];
        if (Groups.exist(id)) return Message.GROUP_EXIST.print(sender, id);
        Groups.create(id);
        return Message.GROUP_CREATE_OK.print(sender, id);
    }
}
