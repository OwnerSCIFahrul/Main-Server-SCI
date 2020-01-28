package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.yescallop.essentialsnk.command.CommandBase;

public class KickAllCommand extends CommandBase {

    public KickAllCommand(EssentialsAPI api) {
        super("kickall", api);

        // command parameters
        commandParameters.clear();
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (args.length != 0) {
            this.sendUsage(sender);
            return false;
        }
        int count = api.getServer().getOnlinePlayers().size();
        if (count == 0 || (sender instanceof Player && count == 1)) {
            sender.sendMessage(TextFormat.RED + Language.translate("commands.kickall.noplayer"));
            return false;
        }
        String reason = String.join(" ", args);
        for (Player player : api.getServer().getOnlinePlayers().values()) {
            if (player != sender) {
                player.kick(reason);
            }
        }
        sender.sendMessage(Language.translate("commands.kickall.success"));
        return true;
    }
}
