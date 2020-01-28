package Fahrul8676.showcommand;

import cn.nukkit.utils.TextFormat;
import cn.nukkit.scheduler.NukkitRunnable;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import org.jline.utils.InputStreamReader;
import java.net.URL;
import cn.nukkit.command.Command;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase
{
    public void onLoad() {
        this.getServer().getConsoleSender().sendMessage(Util.load);
        super.onLoad();
    }

    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(Util.enable);
        this.getServer().getPluginManager().registerEvents((Listener)new Listener(this), (Plugin)this);
        this.getServer().getCommandMap().register("sm", (Command)new ShowCommand("sm", this));
        this.saveDefaultConfig();
        this.updateck();
        super.onEnable();
    }

    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(Util.disable);
        super.onDisable();
    }

    public String getLatestVersion() {
        String ver = null;
        try {
            final URL url = new URL("https://rplay123.github.io/plugins/nukkit/HMShowCommand/version.txt");
            final InputStream i = url.openStream();
            final BufferedReader b = new BufferedReader((Reader)new InputStreamReader(i, "UTF-8"));
            ver = b.readLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final String latestver = ver;
        return ver;
    }

    public boolean islatestversion() {
        boolean islatest = false;
        final String latest = this.getLatestVersion();
        final String c = this.getDescription().getVersion();
        if (latest.equalsIgnoreCase(c)) {
            islatest = true;
            islatest = true;
        }
        return islatest;
    }

    public void updateck() {
        new NukkitRunnable() {
            public void run() {
                if (Main.this.islatestversion()) {
                    final String prefix = Main.this.getConfig().getString("Prefix").replace("&", "§");
                    Main.this.getServer().getConsoleSender().sendMessage(String.valueOf(prefix) + TextFormat.GREEN + " versi saat ini adalah versi terbaru, terima kasih telah menggunakan!");
                }
                else {
                    final String prefix = Main.this.getConfig().getString("Prefix").replace("&", "§");
                    Main.this.getServer().getConsoleSender().sendMessage(String.valueOf(prefix) + TextFormat.RED + " memiliki versi baru! ");
                }
            }
        }.runTaskAsynchronously((Plugin)this);
    }
}

