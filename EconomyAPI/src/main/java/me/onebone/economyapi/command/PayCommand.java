package me.onebone.economyapi.command;

import me.onebone.economyapi.EconomyAPI;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class PayCommand extends Command{
	private EconomyAPI plugin;
	
	public PayCommand(EconomyAPI plugin) {
		super("paycoin", "Membayar coin ke pemain lain", "/paycoin <pemain> <jumlah>");

		this.plugin = plugin;
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args){
		if(!this.plugin.isEnabled()) return false;
		if(!sender.hasPermission("economyapi.command.paycoin")){
			sender.sendMessage(TextFormat.RED + "Anda tidak memiliki izin untuk menggunakan perintah ini.");
			return false;
		}
		
		if(!(sender instanceof Player)){
			sender.sendMessage(TextFormat.RED + "Silakan gunakan perintah ini dalam permainan.");
			return true;
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
			
			int result = this.plugin.reduceMoney((Player) sender, amount);
			switch(result){
			case EconomyAPI.RET_INVALID:
			case EconomyAPI.RET_CANCELLED:
				sender.sendMessage(this.plugin.getMessage("pay-failed", sender));
				return true;
			case EconomyAPI.RET_NO_ACCOUNT:
				sender.sendMessage(this.plugin.getMessage("player-never-connected", new String[]{player}, sender));
				return true;
			case EconomyAPI.RET_SUCCESS:
				this.plugin.addMoney(player, amount, true);
				
				sender.sendMessage(this.plugin.getMessage("pay-success", new String[]{Double.toString(amount), player}, sender));
				if(p instanceof Player){
					p.sendMessage(this.plugin.getMessage("money-paid", new String[]{sender.getName(), Double.toString(amount)}, sender));
				}
				return true;
			}
		}catch(NumberFormatException e){
			sender.sendMessage(this.plugin.getMessage("takemoney-must-be-number", sender));
		}
		return true;
	}

}
