package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.Language;
import cn.yescallop.essentialsnk.command.CommandBase;

public class GodCommand extends CommandBase {
    private EssentialsAPI api;

    public GodCommand(EssentialsAPI api) {
        super("god", api);
        this.setPermission("essentialsnk.god");
        this.api = api;

        // command parameters
        commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[] {
                new CommandParameter("player", CommandParamType.TARGET, true)
        });
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (!this.testIngame(sender)) {
            return false;

        } else if (args.length != 0) {
            if (!sender.hasPermission("essentialsnk.god.others")) {
                this.sendPermissionMessage(sender);
            }

            final Player p = this.api.getServer().getPlayer(args[0]);
            if (p != null) {
                if (p.getGamemode() != 1 && p.getGamemode() != 3) {
                    if (this.api.getPlayerGod(p)) {
                        this.api.removePlayerGod(p);
                        sender.sendMessage(Language.translate("commands.god.success.player.disable", p.getName()));
                        sender.sendMessage(Language.translate("commands.god.success.player.message.disable", p));
                        this.api.getLogger().info(Language.translate("commands.god.success.others.player.disable", sender.getName(), p.getName()));
                    } else {
                        this.api.setPlayerGod(p);
                        p.setHealth(p.getMaxHealth());
                        p.getFoodData().setLevel(p.getFoodData().getMaxLevel());
                        sender.sendMessage(Language.translate("commands.god.success.player.enable", p.getName()));
                        sender.sendMessage(Language.translate("commands.god.success.player.message.enable", p));
                        this.api.getLogger().info(Language.translate("commands.god.success.others.player.enable", sender.getName(), p.getName()));
                    }
                } else {
                    sender.sendMessage(Language.translate("commands.god.empty", p.getName()));
                }
            } else {
                sender.sendMessage(Language.translate("commands.generic.player.notfound", sender.getName()));
            }
        } else {
            if (!(sender instanceof Player)) {
            }
            if (((Player)sender).getGamemode() != 1 && ((Player)sender).getGamemode() != 3) {
                if (this.api.getPlayerGod((Player)sender)) {
                    this.api.removePlayerGod((Player)sender);
                    sender.sendMessage(Language.translate("commands.god.success.sender.disable"));
                    this.api.getLogger().info(Language.translate("commands.god.success.others.sender.disable",sender.getName()));
                } else {
                    this.api.setPlayerGod((Player)sender);
                    ((Player) sender).getPlayer().setHealth(((Player) sender).getPlayer().getMaxHealth());
                    ((Player) sender).getPlayer().getFoodData().setLevel(((Player) sender).getPlayer().getFoodData().getMaxLevel());
                    sender.sendMessage(Language.translate("commands.god.success.sender.enable"));
                    this.api.getLogger().info(Language.translate("commands.god.success.others.sender.enable", sender.getName()));
                }
            } else {
                sender.sendMessage(Language.translate("commands.god.empty"));
            }

        }

        return true;
    }
}
