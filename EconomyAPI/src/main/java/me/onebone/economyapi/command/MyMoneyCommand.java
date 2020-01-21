package me.onebone.economyapi.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

import me.onebone.economyapi.EconomyAPI;

public class MyMoneyCommand extends Command{
	private EconomyAPI plugin;
	
	public MyMoneyCommand(EconomyAPI plugin) {
		super("mycoin", "Menunjukkan coin Anda", "/mycoin");
		
		this.plugin = plugin;
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!this.plugin.isEnabled()) return false;
		if(!sender.hasPermission("economyapi.command.mycoin")){
			sender.sendMessage(TextFormat.RED + "Anda tidak memiliki izin untuk menggunakan perintah ini.");
			return false;
		}
		
		if(!(sender instanceof Player)){
			sender.sendMessage(TextFormat.RED+"Silakan gunakan perintah ini dalam permainan.");
			return true;
		}
		Player player = (Player) sender;
		sender.sendMessage(this.plugin.getMessage("mymoney-mymoney", new String[]{Double.toString(this.plugin.myMoney(player))}, player));
		return true;
	}
}
