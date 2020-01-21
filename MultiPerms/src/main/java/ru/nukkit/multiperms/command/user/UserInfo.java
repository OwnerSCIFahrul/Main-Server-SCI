package ru.nukkit.multiperms.command.user;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.MultiPerms;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Users;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.Paginator;
import ru.nukkit.multiperms.util.Util;

import java.util.ArrayList;
import java.util.List;

@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = "\\S+", permission = "multiperms.admin.userinfo", description = Message.CMD_USER)
public class UserInfo extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        if (args.length != 1) return false;
        String userName = args[0];
        Users.isRegistered(userName).whenComplete((register, e) -> {
            if (e != null) {
                e.printStackTrace();
            } else {
                if (register) {
                    boolean online = Server.getInstance().getPlayerExact(userName) != null;
                    int page = args.length == 3 && args[2].matches("\\d+") ? Integer.parseInt(args[2]) : 1;
                    Users.getUser(userName).whenComplete((user, e2) -> {
                        if (e2 != null) {
                            e2.printStackTrace();
                        } else {

                            List<String> print = new ArrayList<>();
                            List<String> ln = MultiPerms.getPrefixes(userName);
                            if (!ln.isEmpty()) print.add(Message.PERM_USER_PREFIX.getText(Util.join(ln)));
                            ln = MultiPerms.getSuffixes(userName);
                            if (!ln.isEmpty()) print.add(Message.PERM_USER_SUFFIX.getText(Util.join(ln)));
                            ln = user.getGroupList();
                            if (!ln.isEmpty()) print.add(Message.PERM_USER_GROUPS.getText(Util.join(ln)));
                            List<String> pln = user.getPermissionList();
                            if (!pln.isEmpty()) {
                                print.add(Message.PERM_USER_PERMS.getText());
                                for (String s : pln) {
                                    print.add(Message.color2(s));
                                }
                            }
                            Paginator.printPage(sender, print, Message.PERM_USER_INFO.getText(userName), page);
                        }
                    });

                } else {
                    Message.PERM_USER_NOTREGISTER.print(sender, userName);
                }
            }
        });
        return true;
    }
}
