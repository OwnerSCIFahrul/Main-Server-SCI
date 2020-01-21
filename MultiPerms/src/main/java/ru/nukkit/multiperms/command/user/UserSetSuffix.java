package ru.nukkit.multiperms.command.user;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.Util;

@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = {"\\S+", "setsuffix|suffix|sx", "\\S+"}, permission = "multiperms.admin.usersetsuffix", description = Message.CMD_USER_SETSUFFIX)
public class UserSetSuffix extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String userName = args[0];
        String suffix = Util.join(args, 2);
        Users.setPrefix(userName, suffix);
        Util.informMessage(userName, Message.USER_SUFFIX_OK_INFORM, suffix);
        return Message.USER_SUFFIX_OK.print(sender, userName, suffix);
    }
}
