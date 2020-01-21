package me.onebone.economyapi.command;

import me.onebone.economyapi.EconomyAPI;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class SetMoneyCommand extends Command{
	private EconomyAPI plugin;
	
	public SetMoneyCommand(EconomyAPI plugin) {
		super("setcoin", "Tetapkan coin pemain", "/setcoin <pemain> <jumlah>");
		
		this.plugin = plugin;
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!this.plugin.isEnabled()) return false;
		if(!sender.hasPermission("economyapi.command.setcoin")){
			sender.sendMessage(TextFormat.RED + "Anda tidak memiliki izin untuk menggunakan perintah ini.");
			return false;
		}
		
		if(args.length < 2){
			sender.sendMessage(TextFormat.RED + "Pemakaian: " + this.getUsage());
			return true;
		}
		String player = args[0];
		
		Player p = this.plugin.getServer().getPlayer(player);
		if(p != null){
			player = p.getName();
		}
		try{
			double amount = Double.parseDouble(args[1]);
			if(amount < 0){
				sender.sendMessage(this.plugin.getMessage("setmoney-invalid-number", sender));
				return true;
			}
			
			int result = this.plugin.setMoney(player, amount);
			switch(result){
			case EconomyAPI.RET_NO_ACCOUNT:
				sender.sendMessage(this.plugin.getMessage("player-never-connected", new String[]{player}, sender));
				return true;
			case EconomyAPI.RET_CANCELLED:
				sender.sendMessage(this.plugin.getMessage("setmoney-failed", new String[]{player}, sender));
				return true;
			case EconomyAPI.RET_INVALID:
				sender.sendMessage(this.plugin.getMessage("reached-max", new String[]{Double.toString(amount)}, sender));
				return true;
			case EconomyAPI.RET_SUCCESS:
				sender.sendMessage(this.plugin.getMessage("setmoney-setmoney", new String[]{player, Double.toString(amount)}, sender));
				if(p instanceof Player){
					p.sendMessage(this.plugin.getMessage("setmoney-set", new String[]{Double.toString(amount)}, sender));
				}
				return true;
			}
		}catch(NumberFormatException e){
			sender.sendMessage(this.plugin.getMessage("setmoney-invalid-number", sender));
		}
		return true;
	}

}
