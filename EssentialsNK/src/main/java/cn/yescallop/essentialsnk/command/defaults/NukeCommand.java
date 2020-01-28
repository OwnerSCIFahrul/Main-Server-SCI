package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.yescallop.essentialsnk.command.CommandBase;

public class NukeCommand extends CommandBase {

    public NukeCommand(EssentialsAPI api) {
        super("nuke", api);

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
        if (!sender.hasPermission("essentialsnk.nuke.others")) {
            this.sendPermissionMessage(sender);
            return false;
        } else {
            Player player;
            if (args.length != 0) {
                player = this.api.getServer().getPlayer(args[0]);
                if (player.hasPermission(this.getPermission())) {
                }
            } else {
                player = this.api.getServer().getPlayer(sender.getName());
                if (player == null) {
                }
            }
            this.api.nuke(player);
            sender.sendMessage(Language.translate("commands.nuke.success"));
        }
            return true;
        }
    }
