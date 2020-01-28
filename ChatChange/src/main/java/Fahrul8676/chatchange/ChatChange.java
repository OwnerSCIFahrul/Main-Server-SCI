package Fahrul8676.chatchange;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import lombok.NonNull;

public final class ChatChange extends PluginBase {
    private boolean colorize;
    private boolean caseSensitive;
    private boolean changeChat;
    private BiFunction<String, String, Boolean> compareStrings;
    private final Map<StringWrapper, Function<Player, String>> mappings = new LinkedHashMap<StringWrapper, Function<Player, String>>();

    public void onEnable() {
        File file = new File(this.getDataFolder(), "config.yml");
        if (file.exists()) {
            try {
                String content = Utils.readFile((File)file);
                if (content.trim().isEmpty() && !file.delete()) {
                    throw new IllegalStateException(String.format("Tidak dapat menghapus file konfigurasi kosong %s", file.getAbsolutePath()));
                }
            }
            catch (IOException e) {
                Server.getInstance().getLogger().logException((Throwable)e);
            }
        }
        this.saveResource("messages.json");
        this.saveResource("config.yml");
        this.reloadConfig();
        this.getServer().getPluginManager().registerEvents(new Listener(){

            @EventHandler
            public void onChat(PlayerChatEvent event) {
                Function function = (Function) ChatChange.this.mappings.get(new StringWrapper(event.getMessage()));
                if (function != null) {
                    String msg = (String)function.apply(event.getPlayer());
                    if (ChatChange.this.colorize) {
                        msg = TextFormat.colorize((String)msg);
                    }
                    event.getPlayer().chat(msg);
                    if (ChatChange.this.changeChat) {
                        event.setCancelled(true);
                    }
                }
            }
        }, (Plugin)this);
    }

    public void onDisable() {
        this.mappings.clear();
    }

    public void reloadConfig() {
        File dataFolder = this.getDataFolder();
        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
            throw new IllegalStateException(String.format("Tidak dapat membuat direktori %s!", dataFolder.getAbsolutePath()));
        }
        Config config = new Config(new File(dataFolder, "config.yml"));
        if (!config.isCorrect()) {
            throw new IllegalStateException();
        }
        this.colorize = config.getBoolean("colorize", true);
        this.caseSensitive = config.getBoolean("caseSensitive", false);
        this.compareStrings = this.caseSensitive ? String::equals : String::equalsIgnoreCase;
        this.changeChat = config.getBoolean("changeChat", true);
        config.save();
        this.mappings.clear();
        config = new Config(new File(dataFolder, "messages.json"));
        if (!config.isCorrect()) {
            throw new IllegalStateException();
        }
        config.getAll().entrySet().stream().map(entry -> new Tuple(entry.getKey(), (String)entry.getValue())).forEach(tuple -> this.mappings.put(new StringWrapper((String)tuple.key), player -> {
            String response = (String)tuple.value;
            response = response.replaceAll("\\$name", player.getName());
            response = response.replaceAll("\\$displayName", player.getDisplayName());
            response = response.replaceAll("\\$x", String.valueOf(player.getFloorX()));
            response = response.replaceAll("\\$y", String.valueOf(player.getFloorY()));
            response = response.replaceAll("\\$z", String.valueOf(player.getFloorZ()));
            return response;
        }));
        this.getLogger().info(String.format("Memuat %d pesan dan respon!", this.mappings.size()));
    }

    public boolean isColorize() {
        return this.colorize;
    }

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    public boolean isChangeChat() {
        return this.changeChat;
    }

    public BiFunction<String, String, Boolean> getCompareStrings() {
        return this.compareStrings;
    }

    public Map<StringWrapper, Function<Player, String>> getMappings() {
        return this.mappings;
    }

    protected final class StringWrapper {
        @NonNull
        protected final String delegate;

        public int hashCode() {
            return ChatChange.this.caseSensitive ? this.delegate.hashCode() : this.delegate.toLowerCase().hashCode();
        }

        public boolean equals(Object o) {
            if (o instanceof StringWrapper) {
                o = ((StringWrapper)o).delegate;
            }
            if (o instanceof String) {
                return (Boolean) ChatChange.this.compareStrings.apply(this.delegate, (String)o);
            }
            return false;
        }

        public StringWrapper(String delegate) {
            if (delegate == null) {
                throw new NullPointerException("delegate");
            }
            this.delegate = delegate;
        }
    }

    protected static final class Tuple<K, V> {
        @NonNull
        protected final K key;
        @NonNull
        protected final V value;

        public Tuple(@NonNull K key, @NonNull V value) {
            if (key == null) {
                throw new NullPointerException("key");
            }
            if (value == null) {
                throw new NullPointerException("value");
            }
            this.key = key;
            this.value = value;
        }
    }

}

