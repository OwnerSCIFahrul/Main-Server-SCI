package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.command.CommandSender;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.yescallop.essentialsnk.command.CommandBase;

public class WhistleCommand extends CommandBase {

    public WhistleCommand(EssentialsAPI api) {
        super("whistle", api);

        // command parameters
        commandParameters.clear();
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        sender.getServer().broadcastMessage(Language.translate("commands.whistle.success"));
        return true;
    }
}