package Fahrul8676.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandMap;
import cn.nukkit.command.PluginIdentifiableCommand;
import Fahrul8676.TempBanMute;

public abstract class CommandManager extends Command implements PluginIdentifiableCommand {
    private TempBanMute plugin;

    public CommandManager(TempBanMute plugin, String name, String desc, String usage) {
        super(name, desc, usage);

        this.plugin = plugin;
    }

    public CommandManager(TempBanMute plugin, String name, String desc, String usage, String[] aliases) {
        super(name, desc, usage, aliases);

        this.plugin = plugin;
    }

    public CommandManager(TempBanMute plugin, Boolean override, String name, String desc, String usage, String[] aliases) {
        super(name, desc, usage, aliases);

        this.plugin = plugin;

        CommandMap map = plugin.getServer().getCommandMap();
        Command command = map.getCommand(name);
        command.setLabel(name + "_disabled");
        command.unregister(map);
    }

    public TempBanMute getPlugin() {
        return plugin;
    }
}