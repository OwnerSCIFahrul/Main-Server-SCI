package Fahrul8676.servermusic.command;

import Fahrul8676.servermusic.ServerMusicPlugin;
import Fahrul8676.servermusic.resourcepack.MusicResourcePack;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginIdentifiableCommand;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.resourcepacks.ResourcePack;
import cn.nukkit.scheduler.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ServerMusicAdminCommand extends Command implements PluginIdentifiableCommand {

    private final ServerMusicPlugin plugin;

    public ServerMusicAdminCommand(ServerMusicPlugin plugin) {
        super("servermusicadmin", "Mengelola Sumber Lagu Musik", "/servermusicadmin <dump>");
        this.setPermission("servermusic.command.servermusicadmin");
        this.getCommandParameters().clear();
        this.addCommandParameters("default", new CommandParameter[]{
                new CommandParameter("operate", false, new String[]{"dump"})
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

        switch (args[0].toLowerCase()) {
            case "dump":
                sender.sendMessage("Membuang...");
                this.plugin.getServer().getScheduler().scheduleAsyncTask(this.plugin, new AsyncTask(){
                    @Override
                    public void onRun() {
                        Path path = plugin.getDataFolder().toPath().resolve("dump");
                        try {
                            if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                                Files.deleteIfExists(path);
                                Files.createDirectory(path);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        int num = 0;
                        for (ResourcePack pack : plugin.getServer().getResourcePackManager().getResourceStack()) {
                            if (pack instanceof MusicResourcePack) {
                                MusicResourcePack music = (MusicResourcePack) pack;
                                try (OutputStream outputStream = Files.newOutputStream(path.resolve(music.getPackId().toString() + ".mcpack"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
                                    outputStream.write(music.getData());
                                } catch (IOException e) {
                                    plugin.getLogger().alert("Tidak dapat membuang paket sumber daya", e);
                                    continue;
                                }
                                ++num;
                            }
                        }
                        sender.sendMessage("Dibuang " + num + " paket sumber musik");
                    }
                });
                break;
            default:
                sender.sendMessage(new TranslationContainer("commands.generic.usage", this.usageMessage));
                return false;
        }

        return true;
    }
}
