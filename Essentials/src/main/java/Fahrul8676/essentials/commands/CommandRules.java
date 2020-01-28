package Fahrul8676.essentials.commands;

import cn.nukkit.command.CommandSender;

import Fahrul8676.essentials.Main;
import cn.nukkit.utils.Utils;

import java.io.File;
import java.io.IOException;

public class CommandRules extends EssentialsCommand {

    public CommandRules(Main plugin) {
        super(plugin, "rules", "Melihat Peraturan Server.", null);
        setPermission("essentials.rules");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!testPermission(sender)) {
            return true;
        } else {
            try {
                sender.sendMessage(Utils.readFile(new File(this.getPlugin().getDataFolder(), "rules.txt")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }
}