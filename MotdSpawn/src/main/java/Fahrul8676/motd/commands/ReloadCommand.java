package Fahrul8676.motd.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import Fahrul8676.motd.MotdSpawn;

public class ReloadCommand extends Command {

	public ReloadCommand(String name) {
		super(name);
		setPermission("motdspawnreload.command");
		setDescription("Muat ulang konfigurasi plugin");
		setAliases(new String [] {"mspr"});
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		
		if(sender.isOp()) {
			
			try {
				MotdSpawn.config.reload();
				sender.sendMessage("§aKonfigurasi berhasil dimuat ulang");
			} catch (Exception ex) {
				sender.sendMessage("§cKesalahan konfigurasi silakan lihat informasi tambahan di konsol.");
			}
			
		}
		
		return false;
	}
	
	

}
