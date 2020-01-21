package ru.nukkit.multiperms.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.util.Message;

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"remove|rmv|delete|del", "\\S+"}, permission = "multiperms.admin.groupremove", description = Message.CMD_GROUP_REMOVE)
public class GroupRemove extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[1];
        if (!Groups.exist(id)) return Message.GROUP_NOTEXIST.print(sender, id);
        Groups.remove(id);
        return Message.GROUP_REMOVE_OK.print(sender, id);
    }
}
