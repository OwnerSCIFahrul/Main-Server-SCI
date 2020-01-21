package me.onebone.economyapi.command;

import cn.nukkit.Player;
import me.onebone.economyapi.EconomyAPI;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class SetLangCommand extends Command{
	private EconomyAPI plugin;
	
	public SetLangCommand(EconomyAPI plugin) {
		super("setlang", "Tetapkan bahasa pilihan Anda", "/setlang <ccTLD>");
		
		this.plugin = plugin;
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!this.plugin.isEnabled()) return false;
		if(!sender.hasPermission("economyapi.command.setlang")){
			sender.sendMessage(TextFormat.RED + "Anda tidak memiliki izin untuk menggunakan perintah ini.");
			return false;
		}
		
		if(args.length < 1){
			sender.sendMessage(TextFormat.RED + "Pemakaian: " + this.getUsage());
			return true;
		}
		String lang = args[0];
		
		// TODO
		return true;
	}
}
