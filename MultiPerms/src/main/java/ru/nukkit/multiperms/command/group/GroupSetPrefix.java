package ru.nukkit.multiperms.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.Util;

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "setprefix|prefix|px", "\\S+"}, permission = "multiperms.admin.groupsetprefix", description = Message.CMD_GROUP_SETPREFIX)
public class GroupSetPrefix extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[0];
        if (!Groups.exist(id)) return Message.GROUP_SETPREFIX_NOTEXIST.print(sender, id);
        String prefix = Util.join(args, 2);
        Groups.setPrefix(id, prefix);
        return Message.GROUP_SETPREFIX_OK.print(sender, id, prefix);
    }
}
