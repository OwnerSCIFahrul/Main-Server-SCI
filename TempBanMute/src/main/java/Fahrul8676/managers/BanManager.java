package Fahrul8676.managers;

import cn.nukkit.utils.Config;
import com.mongodb.client.MongoCollection;
import java.text.SimpleDateFormat;
import java.util.Map;
import Fahrul8676.TempBanMute;
import Fahrul8676.utils.BanUtil;
import org.bson.Document;

public class BanManager {
    private TempBanMute plugin;

    public BanManager(TempBanMute plugin) {
        this.plugin = plugin;
    }

    public static void setBanned(String player, String reason, String id, String banner, String date, int seconds) {
        long current;
        long end;
        if (TempBanMute.getInstance().getConfig().getBoolean("MongoDB")) {
            current = System.currentTimeMillis();
            end = current + (long)seconds * 1000L;
            if (seconds == -1) {
                end = -1L;
            }

            Document document = new Document("name", player);
            Document found = (Document)TempBanMute.getInstance().getBanCollection().find(document).first();
            if (found == null) {
                document.append("reason", reason);
                document.append("id", id);
                document.append("banner", banner);
                document.append("date", date);
                document.append("end", end);
            }

            TempBanMute.getInstance().getBanCollection().insertOne(document);
        } else {
            current = System.currentTimeMillis();
            end = current + (long)seconds * 1000L;
            if (seconds == -1) {
                end = -1L;
            }

            Config bans = new Config(TempBanMute.getInstance().getDataFolder() + "/bans.yml", 2);
            bans.set("Player." + player, player);
            bans.set("Player." + player + ".Reason", reason);
            bans.set("Player." + player + ".ID", id);
            bans.set("Player." + player + ".Banner", banner);
            bans.set("Player." + player + ".Date", date);
            bans.set("Player." + player + ".End", end);
            bans.save();
            bans.reload();
        }

    }

    public static void unBan(String player) {
        if (TempBanMute.getInstance().getConfig().getBoolean("MongoDB")) {
            MongoCollection<Document> collection = TempBanMute.getInstance().getBanCollection();
            collection.deleteOne(new Document("name", player));
        } else {
            Config bans = new Config(TempBanMute.getInstance().getDataFolder() + "/bans.yml", 2);
            Map<String, Object> map = bans.getSection("Player").getAllMap();
            map.remove(player);
            bans.set("Player", map);
            bans.save();
            bans.reload();
        }

    }

    public BanUtil getPlayer(String player) {
        String name = "";
        String reason = "";
        String id = "";
        String banner = "";
        String date = "";
        long end = 0L;
        if (TempBanMute.getInstance().getConfig().getBoolean("MongoDB")) {
            Document found = (Document)TempBanMute.getInstance().getBanCollection().find(new Document("name", player)).first();
            if (found != null) {
                name = found.getString("name");
                reason = found.getString("reason");
                id = found.getString("id");
                banner = found.getString("banner");
                date = found.getString("date");
                end = found.getLong("end");
            }
        } else {
            Config bans = new Config(TempBanMute.getInstance().getDataFolder() + "/bans.yml", 2);
            name = player;
            reason = bans.getString("Player." + player + ".Reason");
            id = bans.getString("Player." + player + ".ID");
            banner = bans.getString("Player." + player + ".Banner");
            date = bans.getString("Player." + player + ".Date");
            end = bans.getLong("Player." + player + ".End");
        }

        return new BanUtil(name, reason, id, banner, date, end);
    }

    public static String getRemainingTime(Long duration) {
        if (duration == -1L) {
            return TempBanMute.getInstance().getConfig().getString("Permanent");
        } else {
            SimpleDateFormat today = new SimpleDateFormat("dd.MM.yyyy");
            today.format(System.currentTimeMillis());
            SimpleDateFormat future = new SimpleDateFormat("dd.MM.yyyy");
            future.format(duration);
            long time = future.getCalendar().getTimeInMillis() - today.getCalendar().getTimeInMillis();
            int days = (int)(time / 86400000L);
            int hours = (int)(time / 3600000L % 24L);
            int minutes = (int)(time / 60000L % 60L);
            String day = TempBanMute.getInstance().getConfig().getString("Days");
            if (days == 1) {
                day = TempBanMute.getInstance().getConfig().getString("Day");
            }

            String hour = TempBanMute.getInstance().getConfig().getString("Hours");
            if (hours == 1) {
                hour = TempBanMute.getInstance().getConfig().getString("Hour");
            }

            String minute = TempBanMute.getInstance().getConfig().getString("Minutes");
            if (minutes == 2) {
                minute = TempBanMute.getInstance().getConfig().getString("Minute");
            }

            if (minutes < 1 && days == 0 && hours == 0) {
                return TempBanMute.getInstance().getConfig().getString("Seconds");
            } else if (hours == 0 && days == 0) {
                return minutes + " " + minute;
            } else {
                return days == 0 ? hours + " " + hour + " " + minutes + " " + minute : days + " " + day + " " + hours + " " + hour + " " + minutes + " " + minute;
            }
        }
    }
}
