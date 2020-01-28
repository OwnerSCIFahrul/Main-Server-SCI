package Fahrul8676.motd;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class ReloadCommand extends Command {

    public ReloadCommand(String name) {
        super(name);
        setPermission("motdserver.reload.command");
        setDescription("Muat ulang konfigurasi plugin");
        setAliases(new String [] {"mser"});
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if(sender.isOp()) {

            try {
                MotdServer.config.reload();
                sender.sendMessage("§aKonfigurasi berhasil dimuat ulang");
            } catch (Exception ex) {
                sender.sendMessage("§cKesalahan konfigurasi silakan lihat informasi tambahan di konsol.");
            }

        }

        return false;
    }



}