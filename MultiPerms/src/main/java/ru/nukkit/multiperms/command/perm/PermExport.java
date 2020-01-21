package ru.nukkit.multiperms.command.perm;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.util.Exporter;
import ru.nukkit.multiperms.util.Message;

@CmdDefine(command = "perm", alias = "permission", allowConsole = true, subCommands = "export", permission = "multiperms.admin.permexport", description = Message.CMD_PERM_EXPORT)
public class PermExport extends Cmd {

    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String fileName = ((args.length > 1) ? args[1] : "import").toLowerCase();
        Exporter exporter = new Exporter(fileName);
        if (exporter.exportPermissions()) Message.PERM_EXPORT_OK.print(sender, fileName + ".yml");
        else Message.PERM_EXPORT_FAILED.print(sender);
        return true;
    }
}
