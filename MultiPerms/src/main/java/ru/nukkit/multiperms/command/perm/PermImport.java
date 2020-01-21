package ru.nukkit.multiperms.command.perm;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multiperms.command.Cmd;
import ru.nukkit.multiperms.command.CmdDefine;
import ru.nukkit.multiperms.util.Exporter;
import ru.nukkit.multiperms.util.Message;

@CmdDefine(command = "perm", alias = "permission", allowConsole = true, subCommands = "import", permission = "multiperms.admin.permimport", description = Message.CMD_PERM_IMPORT)
public class PermImport extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        boolean overwrite = false;
        String fileName = null;
        if (args.length == 1) {
            fileName = "import";
        } else if (args.length == 2) {
            if (args[1].matches("(?i)overwrite|over")) {
                overwrite = true;
                fileName = "import";
            } else fileName = args[1];
        } else if (args.length == 3) {
            if (args[1].matches("(?i)overwrite|over")) {
                overwrite = true;
            } else {
                fileName = args[2];
            }
        }
        if (fileName == null) return Message.CMD_PERM_IMPORT.print(sender);
        Exporter exporter = new Exporter(fileName);
        if (exporter.importPermissions(overwrite)) Message.PERM_IMPORT_OK.print(sender, fileName);
        else Message.PERM_IMPORT_FAILED.print(sender, fileName);
        return true;
    }
}
