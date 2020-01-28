package Fahrul8676.motd.manager.managers.utils;

import java.io.File;

import Fahrul8676.motd.MotdSpawn;
import Fahrul8676.motd.manager.managers.Manager;

public class FileManager implements Manager
{
    private File baseDir;
    private File motdFile;

    @Override
    public void onEnable(final MotdSpawn instance) {
        this.baseDir = instance.getDataFolder();
        instance.saveResource("motd.txt");
        this.motdFile = new File(this.baseDir, "motd.txt");
    }

    @Override
    public void onDisable(final MotdSpawn instance) {
    }
    
    public File getBaseDir() {
        return this.baseDir;
    }
    
    public File getMotdFile() {
        return this.motdFile;
    }
}
