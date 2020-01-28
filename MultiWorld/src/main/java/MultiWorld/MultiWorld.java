package MultiWorld;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.utils.MainLogger;
import cn.nukkit.utils.TextFormat;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MultiWorld extends PluginBase {

    @Override
    public void onEnable() {
        this.getLogger().info("Memuat MultiWorld");
        LoadAllLevels();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("mw.command")) {
            sender.sendMessage(cmd.getPermissionMessage());
            return true;
        }

        if (args.length <= 0 || args[0].toLowerCase().equals("help")) {
            if(!sender.hasPermission("mw.command.help")){
                sender.sendMessage(cmd.getPermissionMessage());
                return true;
            }

            sender.sendMessage(getHelp(sender));
            return true;
        }

        Level level = null;
        Player p = null;

        if (sender instanceof Player) {
            p = (Player) sender;
            level = p.getLevel();
        }

        switch (args[0].toLowerCase()) {
            case "load":
            case "l":
                if(!sender.hasPermission("mw.command.unload")){
                    sender.sendMessage(cmd.getPermissionMessage());
                    break;
                }

                if (args.length != 2) {
                    sender.sendMessage(TextFormat.GRAY + "gunakan: " + TextFormat.YELLOW + "/mw load <nama dunia>");
                    return true;
                }

                if (!getServer().loadLevel(args[1])) {
                    sender.sendMessage(TextFormat.RED + "dunia " + args[1] + " tidak ada");
                    return true;
                }

                sender.sendMessage(TextFormat.GREEN + "Dunia telah berhasil dimuat");
                break;
            case "unload":
            case "ul":
                if(!sender.hasPermission("mw.command.unload")){
                    sender.sendMessage(cmd.getPermissionMessage());
                    break;
                }

                if (args.length != 2) {
                    sender.sendMessage(TextFormat.GRAY + "gunakan: " + TextFormat.YELLOW + "/mw unload <nama dunia>");
                    return true;
                }

                Level lvl = getServer().getLevelByName(args[1]);

                if (lvl == null) {
                    sender.sendMessage(TextFormat.RED + "dunia " + args[1] + " tidak ada");
                    return true;
                }

                getServer().unloadLevel(lvl);
                sender.sendMessage(TextFormat.GREEN + "dunia telah berhasil dibongkar");
                break;
            case "create":
            case "c":
                if(!sender.hasPermission("mw.command.create")){
                    sender.sendMessage(cmd.getPermissionMessage());
                    break;
                }

                if (args.length < 2 || args.length > 4) {
                    sender.sendMessage(TextFormat.GRAY + "gunakan: " + TextFormat.YELLOW + "/mw create <nama dunia> [generator] [benih]");
                    return true;
                }

                long seed = args.length >= 4 ? Long.valueOf(args[3]) : new Random().nextLong();
                Class<? extends Generator> generator = args.length >= 3 ? Generator.getGenerator(args[2]) : null;

                if (!getServer().generateLevel(args[1], seed, generator)) {
                    sender.sendMessage(TextFormat.RED + "dunia " + args[1] + " sudah ada");
                    return true;
                }

                sender.sendMessage(TextFormat.GREEN + "Dunia telah berhasil dibuat");
                break;
            case "delete":
            case "d":
                if(!sender.hasPermission("mw.command.delete")){
                    sender.sendMessage(cmd.getPermissionMessage());
                    break;
                }

                if (args.length != 2) {
                    sender.sendMessage(TextFormat.GRAY + "gunakan: " + TextFormat.YELLOW + "/mw delete <nama dunia>");
                    return true;
                }

                Level level1 = getServer().getLevelByName(args[0]);
                File path = new File(getServer().getDataPath() + "worlds/" + args[0]);

                if (level1 != null) {
                    getServer().unloadLevel(level1);
                }

                getServer().getScheduler().scheduleAsyncTask(new AsyncTask() {
                    boolean error = false;

                    @Override
                    public void onRun() {
                        try {
                            FileUtils.deleteDirectory(path);
                        } catch (IOException e) {
                            MainLogger.getLogger().logException(e);
                            error = true;
                        }
                    }

                    @Override
                    public void onCompletion(Server server){
                        if(error){
                            sender.sendMessage(TextFormat.RED + "Kesalahan terjadi selama penghapusan dunia");
                        } else {
                            sender.sendMessage(TextFormat.GREEN + "Dunia telah berhasil dihapus");
                        }
                    }
                });
                break;
            case "teleport":
            case "tp":
            case "goto":
                if(!sender.hasPermission("mw.command.teleport")){
                    sender.sendMessage(cmd.getPermissionMessage());
                    break;
                }

                if ((p == null && args.length != 3) || args.length < 2 || args.length > 3) {
                    sender.sendMessage(TextFormat.GRAY + "gunakan: " + TextFormat.YELLOW + "/mw teleport <nama dunia> [pemain]");
                    return true;
                }

                Level level2 = getServer().getLevelByName(args[1]);

                if (level2 == null) {
                    if (!getServer().loadLevel(args[1])) {
                        sender.sendMessage(TextFormat.RED + "dunia " + args[1] + " tidak ada");
                        return true;
                    }

                    level2 = getServer().getLevelByName(args[1]);

                    if (level2 == null) {
                        sender.sendMessage(TextFormat.RED + "dunia " + args[1] + " tidak ada");
                        return true;
                    }
                }

                Player target = p;
                String msg = TextFormat.GRAY + "Teleportasi ke dunia " + level2.getFolderName()+"...";

                if (args.length == 3) {
                    target = getServer().getPlayer(args[2]);

                    if (target == null) {
                        sender.sendMessage(TextFormat.RED + "Pemain " + args[2] + " tidak ada");
                        return true;
                    }

                    msg = TextFormat.GRAY + "Teleportasi " + TextFormat.YELLOW + target.getName() + TextFormat.GRAY + " ke dunia " + TextFormat.GREEN + level2.getFolderName() + "...";
                }

                target.teleport(level2.getSafeSpawn());
                sender.sendMessage(msg);
                break;
            case "list":
                if(!sender.hasPermission("mw.command.list")){
                    sender.sendMessage(cmd.getPermissionMessage());
                    break;
                }

                String message = TextFormat.GRAY+"Semua dunia dimuat:\n";

                for(Level level3 : getServer().getLevels().values()){
                    message += TextFormat.GREEN+"- "+TextFormat.GRAY+level3.getFolderName()+"\n";
                }

                sender.sendMessage(message);
                break;
            case "spawn":
                if(!sender.hasPermission("mw.command.spawn")){
                    sender.sendMessage(cmd.getPermissionMessage());
                    break;
                }

                if(p == null){
                    sender.sendMessage(TextFormat.RED+"Anda dapat menggunakan perintah ini hanya dalam permainan");
                    break;
                }

                p.sendMessage(TextFormat.GRAY + "Teleportasi untuk tempat muncul dunia...");
                p.teleport(level.getSafeSpawn());
                break;
            case "setspawn":
                if(!sender.hasPermission("mw.command.setspawn")){
                    sender.sendMessage(cmd.getPermissionMessage());
                    break;
                }

                if(p == null){
                    sender.sendMessage(TextFormat.RED+"Anda dapat menggunakan perintah ini hanya dalam permainan");
                    break;
                }

                p.sendMessage(TextFormat.GRAY+"Tetapkan tempat muncul dunia");
                level.setSpawnLocation(p.clone());
                break;
        }

        return true;
    }
    public void LoadAllLevels() {
        File path = new File(getServer().getDataPath() + "/worlds/");
        File[] file = path.listFiles();
        for (File f:file){
            boolean rs = getServer().loadLevel(f.getName());
        }
    }

    private String getHelp(CommandSender sender) {
        String msg = TextFormat.BLUE+"Mencetak semua perintah MultiWorld: \n";

        if(sender.hasPermission("mw.command.load")) msg += TextFormat.YELLOW+"/mw load "+TextFormat.GREEN+"- Memuat dunia yang ada\n";
        if(sender.hasPermission("mw.command.unload")) msg += TextFormat.YELLOW+"/mw unload "+TextFormat.GREEN+"- Membongkar dunia yang ada\n";
        if(sender.hasPermission("mw.command.create")) msg += TextFormat.YELLOW+"/mw create "+TextFormat.GREEN+"- Ciptakan dunia baru\n";
        if(sender.hasPermission("mw.command.delete")) msg += TextFormat.YELLOW+"/mw delete "+TextFormat.GREEN+"- Hapus dunia yang ada\n";
        if(sender.hasPermission("mw.command.list")) msg += TextFormat.YELLOW+"/mw list "+TextFormat.GREEN+"- Menunjukkan semua dunia yang dimuat\n";
        if(sender.hasPermission("mw.command.teleport")) msg += TextFormat.YELLOW+"/mw teleport "+TextFormat.GREEN+"- Teleport ke dunia\n";
        if(sender.hasPermission("mw.command.spawn")) msg += TextFormat.YELLOW+"/mw spawn "+TextFormat.GREEN+"- Teleport ke bibit dunia\n";
        if(sender.hasPermission("mw.command.setspawn")) msg += TextFormat.YELLOW+"/mw setspawn "+TextFormat.GREEN+"- Mengatur bibit dunia\n";


        return msg;
    }

    public static String getPrefix(){
        return "";
    }
}