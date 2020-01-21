package discordbot;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.lang.TextContainer;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;

import java.time.LocalTime;

public class PlayerListener implements Listener {
    private String lastMessage;
    private String lastDisplayName;
    private String lastTime;
    private String lastWorld;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String displayname = e.getPlayer().getDisplayName();
        if (Main.config.getBoolean("joinMessages")) {
        if (displayname.equals(this.lastDisplayName)) {
            return;
        }

        this.lastDisplayName = displayname;
        API.sendMessage(Main.config.getString("info_player_joined").replace("%time%", LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()).replace("%player_displayname%", TextFormat.clean(displayname)));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        String displayname = e.getPlayer().getDisplayName();
        if (Main.config.getBoolean("quitMessages")) {
        if (displayname.equals(this.lastDisplayName)) {
            return;
        }

        this.lastDisplayName = displayname;
            API.sendMessage(Main.config.getString("info_player_left").replace("%time%", LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()).replace("%player_displayname%", TextFormat.clean(displayname)));
        }
    }

    @EventHandler(ignoreCancelled=true)
    public void onDeath(PlayerDeathEvent e) {
        if (Main.config.getBoolean("deathMessages")) {
            API.sendMessage(Main.config.getString("info_player_death").replace("%death_message%", TextFormat.clean((String)PlayerListener.textFromContainer(e.getDeathMessage()))).replace("%time%", LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()));
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onChat(PlayerChatEvent e) {
        if (!e.isCancelled()) {
            if (!Main.config.getBoolean("enableMinecraftToDiscord")) {
                return;
            }
            String msg = e.getMessage();
            String displayname = e.getPlayer().getDisplayName();
            String time = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            String world = e.getPlayer().getLevel().getName();
            if (Main.config.getBoolean("spamFilter")) {
                if (msg.startsWith("Horion - mod utilitas batuan dasar minecraft - horionclient.eu | ")) {
                    return;
                }
                if (msg.equals(this.lastMessage) && displayname.equals(this.lastDisplayName) && time.equals(this.lastTime) && world.equals(this.lastWorld)) {
                    return;
                }

                this.lastMessage = msg;
                this.lastDisplayName = displayname;
                this.lastTime = time;
                this.lastWorld = world;
            }

            API.sendMessage(Main.config.getString("discord_player_messages").replace("%time%", TextFormat.clean(time)).replace("%world%", TextFormat.clean(world)).replace("%player_displayname%", TextFormat.clean(displayname)).replace("%player_message%", TextFormat.clean(msg)));
        }

    }

    private static String textFromContainer(TextContainer container) {
        if (container instanceof TranslationContainer) {
            return Server.getInstance().getLanguage().translateString(container.getText(), ((TranslationContainer)container).getParameters());
        }
        return container.getText();
    }
}