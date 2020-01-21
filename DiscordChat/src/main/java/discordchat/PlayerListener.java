package discordchat;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.lang.TextContainer;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;
import ru.nukkit.multiperms.MultiPerms;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String u00BB = "[";
        String u00BB1 = "] ";
        String u00BB2 = ":";
        if (Main.jda != null && Main.config.getBoolean("joinMessages")) Main.channel.sendMessage(u00BB + java.time.LocalTime.now().getHour() + u00BB2 + java.time.LocalTime.now().getMinute() + u00BB2 + java.time.LocalTime.now().getSecond() + u00BB1 + "**\uD83D\uDD14 Pemain " + u00BB + MultiPerms.getGroup(e.getPlayer()) + u00BB1 + e.getPlayer().getName() + " Telah Masuk Ke Server \uD83D\uDD14**").queue();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        String u00BB = "[";
        String u00BB1 = "] ";
        String u00BB2 = ":";
        if (Main.jda != null && Main.config.getBoolean("quitMessages")) Main.channel.sendMessage(u00BB + java.time.LocalTime.now().getHour() + u00BB2 + java.time.LocalTime.now().getMinute() + u00BB2 + java.time.LocalTime.now().getSecond() + u00BB1 + "**\uD83D\uDD14 Pemain " + u00BB + MultiPerms.getGroup(e.getPlayer()) + u00BB1 + e.getPlayer().getName() + " Telah Keluar Dari Server \uD83D\uDD14**").queue();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        String msg = TextFormat.clean(textFromContainer(e.getDeathMessage()));
        String u00BB = "[";
        String u00BB2 = "] ";
        if (Main.jda != null && Main.config.getBoolean("deathMessages")) Main.channel.sendMessage(u00BB + java.time.LocalTime.now().getHour() + u00BB2 + java.time.LocalTime.now().getMinute() + u00BB2 + java.time.LocalTime.now().getSecond() + u00BB2 + "**\uD83D\uDC80 Pemain " + msg + " \uD83D\uDC80**").queue();
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        if (!Main.config.getBoolean("enableMinecraftToDiscord")) return;
        String u00BB = "  ";
        String u00BB1 = " <> ";
        String u00BB2 = " ✉ ";
        String u00BB3 = "【";
        String u00BB4 = "】 ";
        String u00BB5 = " ☆ ";
        String u00BB6 = ":";
        if (Main.config.getBoolean("windowsHost")) u00BB1 = " \u00BB1 ";
        if (!e.isCancelled() && Main.channel != null) Main.channel.sendMessage(u00BB5 + u00BB3 + java.time.LocalTime.now().getHour() + u00BB6 + java.time.LocalTime.now().getMinute() + u00BB6 + java.time.LocalTime.now().getSecond() + u00BB4 + u00BB3 + e.getPlayer().getLevel().getName() + u00BB4 + u00BB + u00BB3 + MultiPerms.getGroup(e.getPlayer()) + u00BB4 + e.getPlayer().getName() + u00BB + u00BB5 + u00BB2 + e.getMessage()).queue();

}

    private String textFromContainer(TextContainer container) {
        if (container instanceof TranslationContainer) {
            return Server.getInstance().getLanguage().translateString(container.getText(), ((TranslationContainer) container).getParameters());
        } else {
            return container.getText();
        }
    }
}