package Fahrul8676.showcommand;

import cn.nukkit.utils.TextFormat;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.Command;

public class ShowCommand extends Command
{
    private Main main;

    public ShowCommand(final String name, final Main main) {
        super(name);
        this.main = main;
    }

    public boolean execute(final CommandSender sender, final String label, final String[] args) {
        if (!args[0].equalsIgnoreCase("reload")) {
            return false;
        }
        if (sender.hasPermission("showcommand.reload")) {
            this.main.reloadConfig();
            this.main.getServer().getConsoleSender().sendMessage(TextFormat.AQUA + "Muat ulang konfigurasi...");
            return true;
        }
        return true;
    }
}