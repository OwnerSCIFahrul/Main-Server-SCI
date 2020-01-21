package ru.nukkit.multiperms.command.user;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.Util;

@CmdDefine(
        command = "user",
        alias = "userperm",
        allowConsole = true,
        subCommands = {"[A-Za-z0-9 ]{1,15}", "setprefix|prefix|px", "\\S+"},
        permission = {"multiperms.admin"},
        description = Message.CMD_USER_SETPREFIX
)
public class UserSetPrefix extends Cmd {
    public UserSetPrefix() {
    }

    public boolean execute(CommandSender sender, Player player, String[] args) {
        String userName = args[0];
        String prefix = Util.join(args, 2);
        Users.setPrefix(userName, prefix);
        Util.informMessage(userName, Message.USER_PREFIX_OK_INFORM, new Object[]{prefix});
        return Message.USER_PREFIX_OK.print(sender, new Object[]{userName, prefix});
    }
}