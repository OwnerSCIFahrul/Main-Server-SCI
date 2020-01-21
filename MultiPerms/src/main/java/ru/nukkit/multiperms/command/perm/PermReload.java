package ru.nukkit.multiperms.command.perm;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.MultiPermsPlugin;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Message;

@CmdDefine(command = "perm", alias = "permission", allowConsole = true, subCommands = "reload", permission = "multiperms.admin.permreload", description = Message.CMD_PERM_RELOAD)
public class PermReload extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        MultiPermsPlugin.getCfg().load();
        Groups.loadGroups();
        Users.reloadUsers();
        return Message.PERM_RELOADED.print(sender);
    }
}
