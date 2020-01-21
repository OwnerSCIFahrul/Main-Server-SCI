package ru.nukkit.multiperms.command.perm;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Message;


@CmdDefine(command = "perm", alias = "permission", allowConsole = true, subCommands = "refresh|update|recalculate", permission = "multiperms.admin.permrefresh", description = Message.CMD_PERM_UPDATE)
public class PermRefresh extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        Users.recalculatePermissions();
        return Message.PERM_UPDATED.print(sender);
    }
}
