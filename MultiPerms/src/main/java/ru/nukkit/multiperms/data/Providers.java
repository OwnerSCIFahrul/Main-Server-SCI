package ru.nukkit.multiperms.data;

import cn.nukkit.Server;
import org.sql2o.Sql2o;
import ru.nukkit.dblib.DbLib;
import ru.nukkit.multiperms.MultiPermsPlugin;
import ru.nukkit.multiperms.data.database.DatabaseSource;
import ru.nukkit.multiperms.data.yaml.YamlSource;
import ru.nukkit.multiperms.permissions.Group;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.permissions.User;
import ru.nukkit.multiperms.util.Cfg;
import ru.nukkit.multiperms.util.Message;
import ru.nukkit.multiperms.util.MultiTask;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public enum Providers {

    YAML(YamlSource.class),
    DATABASE(DatabaseSource.class);

    private Class<? extends DataProvider> clazz;
    private DataProvider source;

    Providers(Class<? extends DataProvider> clazz) {
        this.clazz = clazz;
    }

    public DataProvider getSource() {
        try {
            source = clazz.newInstance();
        } catch (Exception e) {
            source = null;
            Message.PROVIDER_FAILED.log(this.name());
        }
        return this.source;
    }

    private static DataProvider currentProvider;


    public static void init() {
        Providers dp = getByName(MultiPermsPlugin.getCfg().dataSource);
        if (dp == null) {
            Message.LOG_UNKNOWN_DATAPROVIDER.log(MultiPermsPlugin.getCfg().dataSource);
            dp = YAML;
        } else {
            Message.LOG_DATAPROVIDER.log(dp.name());
        }
        currentProvider = dp.getSource();
        if (currentProvider == null || !currentProvider.isEnabled()) {
            Message.LOG_DATAPROVIDER_FAIL.log(MultiPermsPlugin.getCfg().dataSource);
        }
    }

    public static CompletableFuture<User> loadUser(String playerName) {
        CompletableFuture<User> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                result.complete(currentProvider.loadUser(playerName));
            }
        }.start();
        return result;
    }

    public static CompletableFuture<Boolean> isRegistered(String userName) {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                result.complete(currentProvider.isStored(userName));
            }
        }.start();
        return result;
    }

    public static CompletableFuture<Boolean> saveUser(User user) {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                currentProvider.saveUser(user);
                result.complete(true);
            }
        }.start();
        return result;
    }


    public static CompletableFuture<Boolean> saveGroup(Group group) {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                currentProvider.saveGroup(group);
                result.complete(true);
            }
        }.start();
        return result;
    }

    public static CompletableFuture<Boolean> saveUsers(Collection<User> users) {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                currentProvider.saveUsers(users);
                result.complete(true);
            }
        }.start();
        return result;
    }

    public static CompletableFuture<Boolean> saveGroups() {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                currentProvider.saveGroups(Groups.getAll());
                result.complete(true);
            }
        }.start();
        return result;
    }

    public static CompletableFuture<Map<String, Group>> loadGroups() {
        CompletableFuture<Map<String, Group>> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                Map<String, Group> groups = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                currentProvider.loadGroups().forEach(group -> groups.put(group.getName(), group));
                result.complete(groups);
            }
        }.start();
        return result;
    }

    public static Providers getByName(String name) {
        for (Providers dp : values()) {
            if (name.equalsIgnoreCase(dp.name())) return dp;
        }
        return null;
    }

    public static CompletableFuture<Boolean> clearUsers() {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                currentProvider.clearUsers();
                result.complete(true);
            }
        }.start();
        return result;
    }

    public static CompletableFuture<Boolean> clearGroups() {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                currentProvider.clearGroups();
                result.complete(true);
            }
        }.start();
        return result;
    }

    public static CompletableFuture<Collection<User>> getAllUsers() {
        CompletableFuture<Collection<User>> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                result.complete(currentProvider.getAllUsers());
            }
        }.start();
        return result;
    }


    public static Sql2o getSql2o() {
        if (Server.getInstance().getPluginManager().getPlugin("DbLib") == null) {
            Message.DB_DBLIB_NOTFOUND.log();
            return null;
        }
        Cfg cfg = MultiPermsPlugin.getCfg();
        switch (cfg.dblibSource.toLowerCase()) {
            case "dblib":
            case "default":
                return DbLib.getSql2o();
            case "sqlite":
                File dbFile = new File(MultiPermsPlugin.getPlugin().getDataFolder() + File.separator + cfg.customSQLite);
                return DbLib.getSql2o(DbLib.getSqliteUrl(dbFile), "", "");
            case "mysql":
                return DbLib.getSql2oMySql(cfg.mysqlHost, cfg.mysqlPort, cfg.mysqlDb, cfg.mysqlName, cfg.mysqlPassword);
        }
        return null;
    }

    public static DataProvider getCurrentProvider() {
        return currentProvider;
    }

    public static CompletableFuture<Boolean> removeGroup(String groupId) {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                getCurrentProvider().removeGroup(groupId);
                result.complete(true);
            }
        }.start();
        return result;
    }


    public static CompletableFuture<Boolean> removeUser(String userName) {
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        new MultiTask() {
            @Override
            public void onRun() {
                getCurrentProvider().removeUser(userName);
                result.complete(true);
            }
        }.start();
        return result;
    }
}
