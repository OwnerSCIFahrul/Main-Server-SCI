package me.onebone.economyapi.provider;

import cn.nukkit.Player;

import java.io.File;
import java.util.LinkedHashMap;

public interface Provider {
	public void init(File path);
	
	public void open();
	public void save();
	public void close();
	
	public boolean accountExists(String player);
	public boolean accountExists(Player player);
	
	public boolean createAccount(Player player, double defaultMoney);
	public boolean createAccount(String player, double defaultMoney);
	
	public boolean setMoney(String player, double amount);
	public boolean setMoney(Player player, double amount);
	
	public boolean addMoney(String player, double amount);
	public boolean addMoney(Player player, double amount);
	
	public boolean reduceMoney(String player, double amount);
	public boolean reduceMoney(Player player, double amount);
	
	public double getMoney(String player);
	public double getMoney(Player player);
	
	public LinkedHashMap<String, Double> getAll();
	
	public String getName();
}
