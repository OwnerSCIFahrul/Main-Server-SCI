package ru.nukkit.multiperms.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.permissions.Group;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {}, permission = "multiperms.admin.groupinfo", description = Message.CMD_GROUP_REMOVE)
public class GroupInfo extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        if (args.length > 1) return false;
        if (args.length == 0) {
            Collection<Group> groups = Groups.getAll();
            if (groups == null || groups.isEmpty()) return Message.GROUP_LISTNOTFOUND.print(sender);
            else {
                List<String> groupList = new ArrayList<>();
                groups.forEach(g -> groupList.add(g.getName()));
                return Message.GROUP_LIST.print(sender, Util.join(groupList));
            }
        } else {
            Group group = Groups.getGroup(args[0]);
            if (group == null) return Message.GROUP_INFOUNKNOWN.print(sender, args[0]);
            Message.GROUP_INFO_TITLE.print(sender, group.getName());
            List<String> ln = group.getGroupList();
            if (!ln.isEmpty()) Message.GROUP_INFO_GROUPS.print(sender, Util.join(ln));
            List<String> pln = group.getPermissionList();
            if (!pln.isEmpty()) {
                Message.GROUP_INFO_PERMTITLE.print(sender);
                pln.forEach(s -> sender.sendMessage(Message.color1(s)));
            }
        }
        return true;
    }
}
