package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.yescallop.essentialsnk.command.CommandBase;

public class AntiochCommand extends CommandBase {

    public AntiochCommand(EssentialsAPI api) {
        super("antioch", api);

        // command parameters
        commandParameters.clear();
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (!this.testIngame(sender)) {
            return false;
        }
        if (args.length != 0) {
            this.sendUsage(sender);
            return false;
            }
        if (!sender.hasPermission("essentialsnk.antioch.others")) {
            this.sendPermissionMessage(sender);
            return false;
        } else if (sender instanceof Player) {
            if (this.api.antioch((Player) sender)) {
                sender.sendMessage(Language.translate("commands.antioch.success"));
            } else {
                sender.sendMessage(Language.translate("commands.antioch.notexists"));
            }
        }
        return true;
        }
    }