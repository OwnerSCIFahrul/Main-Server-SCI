package ru.nukkit.multichat.util;

import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.SimpleConfig;
import ru.nukkit.multichat.MultiChat;

public class Cfg extends SimpleConfig {

    @Path("general.language")
    public String language = "default";

    @Path("general.language-save")
    public boolean saveLanguage = false;

    @Path("general.debug")
    public boolean debugMode = false;

    @Path("chat.format")
    public String chatFormat = "&f[&c%time%&f] &f[&a%prefix%&f] &f[%aMember&f] &f[&2%faction%&f] &f[&2%suffix%&f] &6%player%&f: &e%message%";

    @Path("name-tag.enable")
    public boolean nametagEnabled = true;

    @Path("name-tag.format")
    public String nametagFormat = "&f[&a%prefix%&f] &f[%aMember&f] &f[&2%faction%&f] &f[&2%suffix%&f] &6%player%";

    @Path("name-tag.strip-colors")
    public boolean nametagStripColor = false;

    @Path("display-name.enable")
    public boolean displayNameEnable = true;

    @Path("display-name.format")
    public String displayNameFormat = "&f[&a%prefix%&f] &f[&2%suffix%&f] &6%player%";

    @Path("display-name.strip-colors")
    public boolean isDisplayNameNoColors = false;

    @Path("group.scan-subgroups")
    public boolean useSubGroup = true;

    @Path("group.format")
    public ConfigSection customGroups = new ConfigSection();

    public Cfg() {
        super(MultiChat.getPlugin());
    }

    @Override
    public boolean load() {
        MultiChat.getPlugin().getDataFolder().mkdirs();
        MultiChat.getPlugin().saveDefaultConfig();
        return super.load();
    }
}
