package Fahrul8676.servermusic.command;

import Fahrul8676.servermusic.IServer;
import Fahrul8676.servermusic.ServerMusicPlugin;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginIdentifiableCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.plugin.Plugin;

public class ServerMusicCommand extends Command implements PluginIdentifiableCommand {

    private final ServerMusicPlugin plugin;

    public ServerMusicCommand(ServerMusicPlugin plugin) {
        super("servermusic", "Mengontrol Lagu Musik", "/servermusic <play|stop>", new String[]{"sm"});
        this.setPermission("servermusic.command.servermusic");
        this.getCommandParameters().clear();
        this.addCommandParameters("default", new CommandParameter[]{
                new CommandParameter("operate", false, new String[]{"play", "stop"})
        });
        this.plugin = plugin;
    }

    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.plugin.isEnabled() || !this.testPermission(sender)) {
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(new TranslationContainer("commands.generic.usage", this.usageMessage));
            return false;
        }

        IServer global = this.plugin.getGlobal();

        if (global.getPlaylist().isEmpty()) {
            sender.sendMessage("Daftar putar kosong");
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch (args[0].toLowerCase()) {
                case "play":
                    global.addListener(player);
                    sender.sendMessage("Telah Diputarkan Lagu Musik");
                    break;
                case "stop":
                    global.removeListener(player);
                    sender.sendMessage("Telah Dimatikan Lagu Musik");
                    break;
                default:
                    sender.sendMessage(new TranslationContainer("commands.generic.usage", this.usageMessage));
                    return false;
            }
        } else {
            sender.sendMessage(new TranslationContainer("%commands.generic.ingame"));
        }

        return true;
    }
}
