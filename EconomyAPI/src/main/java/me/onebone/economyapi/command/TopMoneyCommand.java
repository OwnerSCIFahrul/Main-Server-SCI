package me.onebone.economyapi.command;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import me.onebone.economyapi.EconomyAPI;

public class TopMoneyCommand extends Command{
	private EconomyAPI plugin;
	
	public TopMoneyCommand(EconomyAPI plugin) {
		super("topcoin", "Menampilkan top coin dari server ini", "/topcoin [halaman]");
		
		this.plugin = plugin;
	}

	@Override
	public boolean execute(final CommandSender sender, String label, final String[] args){
		if(!this.plugin.isEnabled()) return false;
		if(!sender.hasPermission("economyapi.command.topcoin")){
			sender.sendMessage(TextFormat.RED + "Anda tidak memiliki izin untuk menggunakan perintah ini.");
			return false;
		}
		
		try{
			final LinkedHashMap<String, Double> money = plugin.getAllMoney();
			final Set<String> players = money.keySet();
			final int page = args.length > 0 ? Math.max(1, Math.min(Integer.parseInt(args[0]), players.size())) : 1;
			new Thread(){
				public void run(){
					List<String> list = new LinkedList<>();
					for(String player:money.keySet()) list.add(player);
					
					Collections.sort(list, new Comparator<String>(){
						@Override
						public int compare(String s1, String s2) {
							double one = money.get(s1);
							double two = money.get(s2);
							return one < two ? 1 : one > two ? -1 : 0;
						}
					});
					
					StringBuilder output = new StringBuilder();
					output.append(plugin.getMessage("topmoney-tag", new String[]{Integer.toString(page), Integer.toString(((players.size() + 6) / 5))}, sender) + "\n");
					
					int duplicate = 0;
					double prev = -1D;
					for(int n = 0; n < list.size(); n++){
						double m = money.get(list.get(n));
						if(m == prev) duplicate++;
						else duplicate = 0;
						prev = m;
						int current = (int)Math.ceil((double)(n + 1) / 5);
						if(page == current){
							output.append(plugin.getMessage("topmoney-format", new String[]{Integer.toString(n + 1 - duplicate), list.get(n), Double.toString(m)}, sender) + "\n");
						}else if(page < current){
							break;
						}
					}
					output.substring(0, output.length() - 1);
					
					if(sender != null){
						sender.sendMessage(output.toString());
					}
				}
			}.start();
		}catch(NumberFormatException e){
			sender.sendMessage(TextFormat.RED + "Harap berikan nomor.");
		}
		return true;
	}

}
