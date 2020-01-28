package ru.nukkit.multiperms.command.user;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Message;

@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = {"remove|rmv|delete|del", "\\S+"}, permission = "multiperms.admin.userremove", description = Message.CMD_USER_REMOVE)
public class UserRemove extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[1];

        Users.isRegistered(id).whenComplete((exist, e) -> {
            if (e != null) {
                e.printStackTrace();
            } else {
                if (exist) {
                    Users.remove(id);
                    Message.USER_REMOVE_OK.print(sender, id);
                } else {
                    Message.USER_NOTEXIST.print(sender, id);
                }
            }
        });
        return true;
    }
}
