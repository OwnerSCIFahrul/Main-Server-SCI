package Fahrul8676.noteblockmusic.command;

import Fahrul8676.NoteBlockAPI.NoteBlockAPI;
import Fahrul8676.noteblockmusic.NoteBlockMusicPlugin;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginIdentifiableCommand;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.plugin.Plugin;

public class StopAllCommand extends Command implements PluginIdentifiableCommand {

    private final NoteBlockMusicPlugin plugin;

    public StopAllCommand(NoteBlockMusicPlugin plugin) {
        super("noteblockmusicstopall", "Mematikan semua blok suara musik", "/noteblockmusicstopall", new String[]{"nbmsa"});
        this.setPermission("noteblockmusic.command.stopall");
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
        if (sender instanceof Player) {
            Player player = (Player) sender;
            NoteBlockAPI.getInstance().stopPlaying(player);
            sender.sendMessage("Telah Dimatikan Semua Lagu Yang Diputar");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(new TranslationContainer("%commands.generic.ingame"));
            return false;
        }
        return false;
    }
}