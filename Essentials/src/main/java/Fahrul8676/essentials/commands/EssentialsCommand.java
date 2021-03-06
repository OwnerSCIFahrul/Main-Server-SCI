package Fahrul8676.essentials.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandMap;
import cn.nukkit.command.PluginIdentifiableCommand;

import Fahrul8676.essentials.Main;

public abstract class EssentialsCommand extends Command implements PluginIdentifiableCommand {

    private Main plugin;
    
    public EssentialsCommand(Main plugin, String name, String desc, String usage) {
        super(name, desc, usage);

        this.plugin = plugin;
    }
    
    public EssentialsCommand(Main plugin, String name, String desc, String usage, String[] aliases) {
        super(name, desc, usage, aliases);

        this.plugin = plugin;
    }

    // Only for commands that override default ones
    public EssentialsCommand(Main plugin, Boolean override, String name, String desc, String usage, String[] aliases) {
        super(name, desc, usage, aliases);

        this.plugin = plugin;
        
        CommandMap map = plugin.getServer().getCommandMap();
        Command command = map.getCommand(name);
        command.setLabel(name + "_disabled");
        command.unregister(map);
    }

    /*
     * The main class of the plugin
     * @return plugin
     */
    public Main getPlugin() {
        return plugin;
    }

    /*
     * Replace colour tags in messages
     * @return string
     */
    public String f(String string) {
        return string
                .replace("<black>", "\u00A70")
                .replace("<darkblue>", "\u00A71")
                .replace("<green>", "\u00A72")
				.replace("<red>", "\u00A7c")
                .replace("<darkred>", "\u00A74")
                .replace("<purple>", "\u00A75")
                .replace("<gold>", "\u00A76")
                .replace("<darkgray>", "\u00A77")
                .replace("<gray>", "\u00A78")
                .replace("<blue>", "\u00A79")
                .replace("<lime>", "\u00A7a")
                .replace("<aqua>", "\u00A7b")
                .replace("<rose>", "\u00A7c")
                .replace("<pink>", "\u00A7d")
                .replace("<yellow>", "\u00A7e")
                .replace("<white>", "\u00A7f")
                .replace("<lightpurple>","\u00A7d")
                .replace("<reset>","\u00A7r");

    }
}
