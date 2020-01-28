package Fahrul8676;

import cn.nukkit.Player;
import cn.nukkit.command.CommandMap;
import cn.nukkit.plugin.PluginBase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Fahrul8676.commands.*;
import Fahrul8676.listeners.ChatListener;
import Fahrul8676.listeners.JoinListener;
import Fahrul8676.managers.BanManager;
import Fahrul8676.managers.MuteManager;
import Fahrul8676.utils.MutedPlayer;
import org.bson.Document;

import java.util.HashMap;

public class TempBanMute extends PluginBase {

    private static TempBanMute instance;
    public HashMap<Player, MutedPlayer> mutedCache = new HashMap<Player, MutedPlayer>();

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> banCollection, muteCollection;
    public BanManager banManager;
    public MuteManager muteManager;

    @Override
    public void onEnable() {
        getLogger().info("Mulai BanMuteSystem...");
        instance = this;
        getLogger().info("Memuat semua komponen...");
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        this.saveDefaultConfig();
        registerCommands();
        this.banManager = new BanManager(this);
        this.muteManager = new MuteManager(this);
        getLogger().info("Komponen berhasil dimuat!");
        if (getConfig().getBoolean("MongoDB")) {
            getLogger().info("Menghubungkan ke database...");
            try {
                MongoClientURI uri = new MongoClientURI(getConfig().getString("MongoDBUri"));
                this.mongoClient = new MongoClient(uri);
                this.mongoDatabase = mongoClient.getDatabase(getConfig().getString("Database"));
                this.banCollection = mongoDatabase.getCollection(getConfig().getString("BanCollection"));
                this.muteCollection = mongoDatabase.getCollection(getConfig().getString("MuteCollection"));
                getLogger().info("§aBerhasil terhubung ke basis data!");
            } catch (Exception e) {
                getLogger().error("§4Gagal terhubung ke basis data.");
                getLogger().error("§4Silakan periksa detail Anda di config.yml atau periksa basis data mongodb Anda \"" + getConfig().getString("Database") + "\"");
                onDisable();
            }
        } else {
            getLogger().info("Menggunakan konfigurasi...");
            saveResource("bans.yml");
            saveResource("mutes.yml");
            getLogger().info("§aPlugin berhasil dimulai.");
        }
    }

    private void registerCommands() {
        CommandMap map = getServer().getCommandMap();
        map.register("ban", new BanCommand(this));
        map.register("unban", new UnbanCommand(this));
        map.register("check", new CheckCommand(this));
        map.register("mute", new MuteCommand(this));
        map.register("unmute", new UnmuteCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("Menonaktifkan BanMuteSystem...");
        if (getConfig().getBoolean("MongoDB")) {
            mongoClient.close();
        }
    }

    public MongoCollection<Document> getBanCollection() {
        return banCollection;
    }

    public MongoCollection<Document> getMuteCollection() {
        return muteCollection;
    }

    public static TempBanMute getInstance() {
        return instance;
    }
}
