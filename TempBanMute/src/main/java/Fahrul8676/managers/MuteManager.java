package Fahrul8676.managers;

import cn.nukkit.utils.Config;
import com.mongodb.client.MongoCollection;
import java.text.SimpleDateFormat;
import java.util.Map;
import Fahrul8676.TempBanMute;
import Fahrul8676.utils.MuteUtil;
import org.bson.Document;

public class MuteManager {
    private TempBanMute plugin;

    public MuteManager(TempBanMute plugin) {
        this.plugin = plugin;
    }

    public static void setMuted(String player, String reason, String id, String banner, String date, int seconds) {
        long current;
        long end;
        if (TempBanMute.getInstance().getConfig().getBoolean("MongoDB")) {
            current = System.currentTimeMillis();
            end = current + (long)seconds * 1000L;
            if (seconds == -1) {
                end = -1L;
            }

            Document document = new Document("name", player);
            Document found = (Document)TempBanMute.getInstance().getMuteCollection().find(document).first();
            if (found == null) {
                document.append("reason", reason);
                document.append("id", id);
                document.append("banner", banner);
                document.append("date", date);
                document.append("end", end);
            }

            TempBanMute.getInstance().getMuteCollection().insertOne(document);
        } else {
            current = System.currentTimeMillis();
            end = current + (long)seconds * 1000L;
            if (seconds == -1) {
                end = -1L;
            }

            Config mutes = new Config(TempBanMute.getInstance().getDataFolder() + "/mutes.yml", 2);
            mutes.set("Player." + player, player);
            mutes.set("Player." + player + ".Reason", reason);
            mutes.set("Player." + player + ".ID", id);
            mutes.set("Player." + player + ".Banner", banner);
            mutes.set("Player." + player + ".Date", date);
            mutes.set("Player." + player + ".End", end);
            mutes.save();
            mutes.reload();
        }

    }

    public static void unMute(String player) {
        if (TempBanMute.getInstance().getConfig().getBoolean("MongoDB")) {
            MongoCollection<Document> collection = TempBanMute.getInstance().getMuteCollection();
            collection.deleteOne(new Document("name", player));
        } else {
            Config mutes = new Config(TempBanMute.getInstance().getDataFolder() + "/mutes.yml", 2);
            Map<String, Object> map = mutes.getSection("Player").getAllMap();
            map.remove(player);
            mutes.set("Player", map);
            mutes.save();
            mutes.reload();
        }

    }

    public MuteUtil getPlayer(String player) {
        String name = "";
        String reason = "";
        String id = "";
        String banner = "";
        String date = "";
        long end = 0L;
        if (TempBanMute.getInstance().getConfig().getBoolean("MongoDB")) {
            Document found = (Document)TempBanMute.getInstance().getMuteCollection().find(new Document("name", player)).first();
            if (found != null) {
                name = found.getString("name");
                reason = found.getString("reason");
                id = found.getString("id");
                banner = found.getString("banner");
                date = found.getString("date");
                end = found.getLong("end");
            }
        } else {
            Config bans = new Config(TempBanMute.getInstance().getDataFolder() + "/mutes.yml", 2);
            name = player;
            reason = bans.getString("Player." + player + ".Reason");
            id = bans.getString("Player." + player + ".ID");
            banner = bans.getString("Player." + player + ".Banner");
            date = bans.getString("Player." + player + ".Date");
            end = bans.getLong("Player." + player + ".End");
        }

        return new MuteUtil(name, reason, id, banner, date, end);
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
