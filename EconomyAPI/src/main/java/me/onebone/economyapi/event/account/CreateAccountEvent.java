package me.onebone.economyapi.event.account;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;

public class CreateAccountEvent extends Event implements Cancellable{
	public static HandlerList handlerList = new HandlerList();
	
	private String player;
	private double defaultMoney;
	
	public CreateAccountEvent(String player, double defaultMoney){
		this.player = player;
		this.defaultMoney = defaultMoney;
	}
	
	public String getPlayer(){
		return this.player;
	}
	
	public double getDefaultMoney(){
		return this.defaultMoney;
	}
	
	public void setDefaultMoney(double amount){
		this.defaultMoney = amount;
	}
	
	public static HandlerList getHandlers(){
		return handlerList;
	}
}
