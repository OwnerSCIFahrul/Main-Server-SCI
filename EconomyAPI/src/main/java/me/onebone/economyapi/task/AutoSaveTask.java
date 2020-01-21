package me.onebone.economyapi.task;

import me.onebone.economyapi.EconomyAPI;

import cn.nukkit.scheduler.PluginTask;

public class AutoSaveTask extends PluginTask<EconomyAPI>{
	public AutoSaveTask(EconomyAPI owner){
		super(owner);
	}
	
	public void onRun(int currentTick){
		((EconomyAPI)this.getOwner()).saveAll();
	}
}
