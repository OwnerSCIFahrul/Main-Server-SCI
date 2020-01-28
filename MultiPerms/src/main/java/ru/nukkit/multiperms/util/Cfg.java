package ru.nukkit.multiperms.util;

import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.SimpleConfig;
import ru.nukkit.multiperms.MultiPermsPlugin;

public class Cfg extends SimpleConfig {

    @Path("general.language")
    public String language = "default";

    @Path("general.language-save")
    public boolean saveLanguage = true;

    @Path("general.debug")
    public boolean debugMode = true;

    // Default group, will be added automatically for all new users
    @Path("permissions.group.default-group")
    public String defaultGroup = "default";

    @Path("permissions.group.default-priority")
    public int groupPriority = 10;

    @Path("permissions.group.group-as-permission")
    public boolean groupPermission = true;

    @Path("permissions.user.default-priority")
    public int userPriority = 100;

    // Global or per-world permissions
    @Path("permissions.multiworld.enable")
    public boolean enableWorldSupport = false;

    /*
      mirrors:
        world_mirrored_to:world_mirrored_from
     */
    @Path("permissions.multiworld.mirrors")
    public ConfigSection mirros = new ConfigSection();


    @Path("storage.type")
    public String dataSource = "YAML"; // yaml, database;

    @Path("storage.auto-update-interval")
    public String multiServerRecheck = "0";

    // Disabled: possible ORMLite bug
    //@Path("storage.database.table-prefix")
    @Skip
    public String tablePrefix = "mp_";

    @Path("storage.database.source")
    public String dblibSource = "DEFAULT"; //SQLITE, MYSQL

    @Path("storage.database.sqlite.file")
    public String customSQLite = "permissions.db";

    @Path("storage.database.mysql.host")
    public String mysqlHost = "localhost";

    @Path("storage.database.mysql.port")
    public int mysqlPort = 3306;

    @Path("storage.database.mysql.database")
    public String mysqlDb = "database";

    @Path("storage.database.mysql.username")
    public String mysqlName = "nukkit";

    @Path("storage.database.mysql.password")
    public String mysqlPassword = "tikkun";


    public Cfg() {
        super(MultiPermsPlugin.getPlugin());
    }
}
