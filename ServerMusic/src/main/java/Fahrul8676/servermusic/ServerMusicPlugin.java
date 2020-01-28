package Fahrul8676.servermusic;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.resourcepacks.ResourcePack;
import cn.nukkit.resourcepacks.ResourcePackManager;
import cn.nukkit.utils.Config;
import Fahrul8676.servermusic.command.ServerMusicAdminCommand;
import Fahrul8676.servermusic.command.ServerMusicCommand;
import Fahrul8676.servermusic.resourcepack.MusicResourcePack;
import Fahrul8676.servermusic.util.MetricsLite;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.jaudiotagger.audio.ogg.util.OggInfoReader;

public class ServerMusicPlugin extends PluginBase implements Listener {

    private static final ThreadLocalRandom RNG = ThreadLocalRandom.current();

    private boolean autoplay = true;

    private final IServer global = new Server();

    @Override
    public void onEnable() {
        try {
            new MetricsLite(this);
        } catch (Throwable ignore) {

        }

        this.saveDefaultConfig();
        Config config = this.getConfig();
        String node = "autoplay";
        try {
            this.autoplay = config.getBoolean(node, this.autoplay);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }
        node = "play-mode";
        try {
            if (config.getString(node).trim().equalsIgnoreCase("random")) {
                this.global.setMode(IServer.MODE_RANDOM);
            }
        } catch (Exception e) {
            this.logConfigException(node, e);
        }

        Path musicPath = this.getDataFolder().toPath().resolve("songs");
        try {
            if (!Files.isDirectory(musicPath, LinkOption.NOFOLLOW_LINKS)) {
                Files.deleteIfExists(musicPath);
                Files.createDirectory(musicPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HashFunction hasher = Hashing.md5();
        List<ResourcePack> packs = new ObjectArrayList<>();
        try {
            Files.walk(musicPath, 1).filter(path -> Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS) && path.toString().endsWith(".ogg")).forEach(path -> {
                try (InputStream fis = Files.newInputStream(path, StandardOpenOption.READ)) {
                    byte[] bytes = new byte[fis.available()];
                    fis.read(bytes);

                    String md5 = hasher.hashBytes(bytes).toString();
                    double seconds = new OggInfoReader().read(new RandomAccessFile(path.toFile(), "r")).getPreciseTrackLength();
                    String name = path.getFileName().toString();
                    IMusic music = new Music(md5, (long) Math.ceil(seconds * 1000), name.substring(0, name.length() - 4));

                    packs.add(new MusicResourcePack(md5, bytes));
                    this.global.addMusic(music);
                } catch (Exception ignore) {

                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!packs.isEmpty()) {
            List<IMusic> playlist = this.global.getPlaylist();
            this.getLogger().info("Memuat " + playlist.size() + " Lagu");

            ResourcePackManager manager = this.getServer().getResourcePackManager();
            synchronized (manager) {
                try {
                    Field f1 = ResourcePackManager.class.getDeclaredField("resourcePacksById");
                    f1.setAccessible(true);
                    Map<UUID, ResourcePack> byId = (Map<UUID, ResourcePack>) f1.get(manager);
                    packs.forEach(pack -> byId.put(pack.getPackId(), pack));

                    Field f2 = ResourcePackManager.class.getDeclaredField("resourcePacks");
                    f2.setAccessible(true);
                    packs.addAll(Arrays.asList((ResourcePack[]) f2.get(manager)));
                    f2.set(manager, packs.toArray(new ResourcePack[0]));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            this.getServer().getPluginManager().registerEvents(this, this);
        }

        this.getServer().getCommandMap().register("servermusic", new ServerMusicCommand(this));
        this.getServer().getCommandMap().register("servermusic", new ServerMusicAdminCommand(this));
    }

    @EventHandler
    public void onDataPacketReceive(DataPacketReceiveEvent event) {
        if (event.getPacket() instanceof SetLocalPlayerAsInitializedPacket && this.autoplay) {
            this.global.addListener(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.global.removeListener(event.getPlayer());
    }

    public IServer getGlobal() {
        return this.global;
    }

    private void logConfigException(String node, Throwable t) {
        this.getLogger().alert("Terjadi kesalahan saat membaca konfigurasi '" + node + "'. Gunakan nilai standar.", t);
    }

    public static ThreadLocalRandom getRNG() {
        return RNG;
    }
}
