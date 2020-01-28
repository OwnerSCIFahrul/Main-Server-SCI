package cn.wode490390.nukkit.theend;

import cn.nukkit.level.generator.Generator;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.wode490390.nukkit.theend.generator.TheEndGenerator;
import cn.wode490390.nukkit.theend.listener.PortalListener;
import cn.wode490390.nukkit.theend.listener.TheEndListener;

public class TheEnd extends PluginBase {

    public static boolean activated;

    @Override
    public void onEnable() {
        try {
            new MetricsLite(this);
        } catch (Throwable ignore) {

        }
        this.saveDefaultConfig();
        Config config = this.getConfig();
        String node = "enable-end-portal";
        boolean portal = true;
        try {
            portal = config.getBoolean(node, portal);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }
        TheEndGenerator.setConfig(config);

        Generator.addGenerator(TheEndGenerator.class, "the_end", TheEndGenerator.TYPE_THE_END);
        this.getServer().getPluginManager().registerEvents(new TheEndListener(), this);
        if (portal) {
            this.getServer().getPluginManager().registerEvents(new PortalListener(), this);
        }
    }

    private void logConfigException(String node, Throwable t) {
        this.getLogger().alert("Terjadi kesalahan saat membaca konfigurasi '" + node + "'. Gunakan nilai standar.", t);
    }
}
