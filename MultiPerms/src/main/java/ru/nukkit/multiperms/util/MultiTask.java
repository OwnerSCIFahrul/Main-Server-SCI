package ru.nukkit.multiperms.util;

import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;

public abstract class MultiTask extends AsyncTask {


    public void start() {
        Server.getInstance().getScheduler().scheduleAsyncTask(this);
    }

}
