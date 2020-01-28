package Fahrul8676.servermusic;

import cn.nukkit.Player;
import java.util.List;

public interface IServer {

    byte MODE_ORDER = 0;
    byte MODE_RANDOM = 1;

    byte getMode();

    void setMode(byte mode);

    List<IMusic> getPlaylist();

    void addMusic(IMusic music);

    void next();

    void addListener(Player player);

    void removeListener(Player player);
}
