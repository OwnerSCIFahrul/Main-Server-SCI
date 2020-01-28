package Fahrul8676.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import Fahrul8676.TempBanMute;
import Fahrul8676.managers.MuteManager;
import Fahrul8676.utils.MessageUtil;
import Fahrul8676.utils.MutedPlayer;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        if (TempBanMute.getInstance().mutedCache.containsKey(player)) {
            event.setCancelled(true);
            MutedPlayer mutedPlayer = TempBanMute.getInstance().mutedCache.get(player);
            player.sendMessage(MessageUtil.muteScreen(mutedPlayer.getReason(), mutedPlayer.getId(), MuteManager.getRemainingTime(mutedPlayer.getEnd()), mutedPlayer.getBanner()));
            long current = System.currentTimeMillis();
            long end = mutedPlayer.getEnd();
            if (end < current) {
                TempBanMute.getInstance().mutedCache.remove(player);
                MuteManager.unMute(player.getName());
            }
        }
    }
}
