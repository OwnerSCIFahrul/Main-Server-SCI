package ru.nukkit.multiperms.command.perm;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.util.Message;

@CmdDefine(command = "perm", alias = "permission", allowConsole = true, subCommands = {"check|chk|test|tst", "\\S+", "\\S+"}, permission = "multiperms.admin.permcheck", description = Message.CMD_PERM_CHECK)
public class PermCheck extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        Player p = Server.getInstance().getPlayerExact(args[1]);
        if (p == null) return Message.PERM_CHECK_NULLPLAYER.print(sender, args[1]);
        Message m = p.hasPermission(args[2]) ? Message.PERM_CHECK_HAS : Message.PERM_CHECK_HASNT;
        return m.print(sender, p.getName(), args[2]);
    }
}
