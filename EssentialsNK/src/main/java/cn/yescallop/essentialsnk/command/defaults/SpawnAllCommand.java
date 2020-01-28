package cn.yescallop.essentialsnk.command.defaults;

import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import cn.yescallop.essentialsnk.command.CommandBase;

public class SpawnAllCommand extends CommandBase {

    public SpawnAllCommand(EssentialsAPI api) {
        super("spawnall", api);

        commandParameters.clear();
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (args.length > 1) {
            this.sendUsage(sender);
            return false;
        }
        int count = this.api.getServer().getOnlinePlayers().size();
        if (!sender.hasPermission(Language.translate("essentialsnk.spawnall.others"))) {
            this.sendPermissionMessage(sender);
        } else {
            if ((count < 1) || (sender instanceof Player && count < 2)) {
                sender.sendMessage(Language.translate("commands.spawnall.noplayer"));
            } else {
                for (Player player : this.api.getServer().getOnlinePlayers().values()) {
                    if (player.equals(sender)) continue;
                    player.teleport(Location.fromObject(this.api.getServer().getDefaultLevel().getSpawnLocation(), this.api.getServer().getDefaultLevel()));
                    sender.sendMessage(Language.translate("commands.spawnall.success"));
                }
            }
        }
        return true;
    }
}
