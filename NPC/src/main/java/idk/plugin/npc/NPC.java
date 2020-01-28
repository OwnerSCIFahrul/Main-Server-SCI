package idk.plugin.npc;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.plugin.PluginBase;
import idk.plugin.npc.entities.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class NPC extends PluginBase {

    public static final List<String> entities = Arrays.asList("Bat", "Blaze", "Cat", "CaveSpider", "Chicken", "Cow", "Creeper",
            "Dolphin", "Donkey", "ElderGuardian", "Enderman", "Endermite", "Evoker", "Ghast", "Guardian",
            "Horse", "Human", "Husk", "IronGolem", "Lama", "Mooshroom", "MagmaCube", "Mule", "Ocelot", "Panda", "Parrot", "Phantom",
            "Pig", "Pillager", "PolarBear", "Rabbit", "SkeletonHorse", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime",
            "Snowman", "Spider", "Squid", "Stray", "Turtle", "Vex", "Villager", "Vindicator", "WanderingTrader", "Witch", "Wither",
            "WitherSkeleton", "Wolf", "ZombieHorse", "Zombie", "ZombiePigman", "ZombieVillager");
    static List<String> id = new ArrayList<>();
    static List<String> kill = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        registerNPC();
    }

    private void registerNPC() {
        Entity.registerEntity(NPC_Bat.class.getSimpleName(), NPC_Bat.class);
        Entity.registerEntity(NPC_Chicken.class.getSimpleName(), NPC_Chicken.class);
        Entity.registerEntity(NPC_Cow.class.getSimpleName(), NPC_Cow.class);
        Entity.registerEntity(NPC_Donkey.class.getSimpleName(), NPC_Donkey.class);
        Entity.registerEntity(NPC_Horse.class.getSimpleName(), NPC_Horse.class);
        Entity.registerEntity(NPC_Mooshroom.class.getSimpleName(), NPC_Mooshroom.class);
        Entity.registerEntity(NPC_Mule.class.getSimpleName(), NPC_Mule.class);
        Entity.registerEntity(NPC_Ocelot.class.getSimpleName(), NPC_Ocelot.class);
        Entity.registerEntity(NPC_Pig.class.getSimpleName(), NPC_Pig.class);
        Entity.registerEntity(NPC_PolarBear.class.getSimpleName(), NPC_PolarBear.class);
        Entity.registerEntity(NPC_Rabbit.class.getSimpleName(), NPC_Rabbit.class);
        Entity.registerEntity(NPC_Sheep.class.getSimpleName(), NPC_Sheep.class);
        Entity.registerEntity(NPC_SkeletonHorse.class.getSimpleName(), NPC_SkeletonHorse.class);
        Entity.registerEntity(NPC_Villager.class.getSimpleName(), NPC_Villager.class);
        Entity.registerEntity(NPC_Wolf.class.getSimpleName(), NPC_Wolf.class);
        Entity.registerEntity(NPC_ZombieHorse.class.getSimpleName(), NPC_ZombieHorse.class);
        Entity.registerEntity(NPC_ElderGuardian.class.getSimpleName(), NPC_ElderGuardian.class);
        Entity.registerEntity(NPC_Guardian.class.getSimpleName(), NPC_Guardian.class);
        Entity.registerEntity(NPC_Snowman.class.getSimpleName(), NPC_Snowman.class);
        Entity.registerEntity(NPC_Lama.class.getSimpleName(), NPC_Lama.class);
        Entity.registerEntity(NPC_Squid.class.getSimpleName(), NPC_Squid.class);
        Entity.registerEntity(NPC_Vindicator.class.getSimpleName(), NPC_Vindicator.class);
        Entity.registerEntity(NPC_Vex.class.getSimpleName(), NPC_Vex.class);
        Entity.registerEntity(NPC_IronGolem.class.getSimpleName(), NPC_IronGolem.class);
        Entity.registerEntity(NPC_Blaze.class.getSimpleName(), NPC_Blaze.class);
        Entity.registerEntity(NPC_Wither.class.getSimpleName(), NPC_Wither.class);
        Entity.registerEntity(NPC_Ghast.class.getSimpleName(), NPC_Ghast.class);
        Entity.registerEntity(NPC_CaveSpider.class.getSimpleName(), NPC_CaveSpider.class);
        Entity.registerEntity(NPC_Creeper.class.getSimpleName(), NPC_Creeper.class);
        Entity.registerEntity(NPC_Enderman.class.getSimpleName(), NPC_Enderman.class);
        Entity.registerEntity(NPC_Endermite.class.getSimpleName(), NPC_Endermite.class);
        Entity.registerEntity(NPC_ZombiePigman.class.getSimpleName(), NPC_ZombiePigman.class);
        Entity.registerEntity(NPC_Silverfish.class.getSimpleName(), NPC_Silverfish.class);
        Entity.registerEntity(NPC_Skeleton.class.getSimpleName(), NPC_Skeleton.class);
        Entity.registerEntity(NPC_Spider.class.getSimpleName(), NPC_Spider.class);
        Entity.registerEntity(NPC_Stray.class.getSimpleName(), NPC_Stray.class);
        Entity.registerEntity(NPC_Witch.class.getSimpleName(), NPC_Witch.class);
        Entity.registerEntity(NPC_Husk.class.getSimpleName(), NPC_Husk.class);
        Entity.registerEntity(NPC_Zombie.class.getSimpleName(), NPC_Zombie.class);
        Entity.registerEntity(NPC_ZombieVillager.class.getSimpleName(), NPC_ZombieVillager.class);
        Entity.registerEntity(NPC_Evoker.class.getSimpleName(), NPC_Evoker.class);
        Entity.registerEntity(NPC_Shulker.class.getSimpleName(), NPC_Shulker.class);
        Entity.registerEntity(NPC_Slime.class.getSimpleName(), NPC_Slime.class);
        Entity.registerEntity(NPC_WitherSkeleton.class.getSimpleName(), NPC_WitherSkeleton.class);
        Entity.registerEntity(NPC_MagmaCube.class.getSimpleName(), NPC_MagmaCube.class);
        Entity.registerEntity(NPC_Human.class.getSimpleName(), NPC_Human.class);
        Entity.registerEntity(NPC_Parrot.class.getSimpleName(), NPC_Parrot.class);
        Entity.registerEntity(NPC_Dolphin.class.getSimpleName(), NPC_Dolphin.class);
        Entity.registerEntity(NPC_Turtle.class.getSimpleName(), NPC_Turtle.class);
        Entity.registerEntity(NPC_Phantom.class.getSimpleName(), NPC_Phantom.class);
        Entity.registerEntity(NPC_Drowned.class.getSimpleName(), NPC_Drowned.class);
        Entity.registerEntity(NPC_Cat.class.getSimpleName(), NPC_Cat.class);
        Entity.registerEntity(NPC_Panda.class.getSimpleName(), NPC_Panda.class);
        Entity.registerEntity(NPC_Pillager.class.getSimpleName(), NPC_Pillager.class);
        Entity.registerEntity(NPC_WanderingTrader.class.getSimpleName(), NPC_WanderingTrader.class);
    }

    public CompoundTag nbt(Player sender, String[] args, String name) {
        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<>("Pos")
                        .add(new DoubleTag("", sender.x))
                        .add(new DoubleTag("", sender.y))
                        .add(new DoubleTag("", sender.z)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", (float) sender.getYaw()))
                        .add(new FloatTag("", (float) sender.getPitch())))
                .putBoolean("Invulnerable", true)
                .putString("NameTag", name)
                .putList(new ListTag<StringTag>("Commands"))
                .putList(new ListTag<StringTag>("PlayerCommands"))
                .putBoolean("npc", true)
                .putFloat("scale", 1);
        if ("Human".equals(args[1])) {
            nbt.putCompound("Skin", new CompoundTag()
                    .putByteArray("Data", sender.getSkin().getSkinData().data)
                    .putString("ModelId", UUID.randomUUID().toString())
                    .putString("GeometryName", "geometry.humanoid.custom")
                    .putByteArray("GeometryData", sender.getSkin().getGeometryData().getBytes(StandardCharsets.UTF_8)));
            nbt.putBoolean("ishuman", true);
            nbt.putString("Item", sender.getInventory().getItemInHand().getName());
            nbt.putString("Helmet", sender.getInventory().getHelmet().getName());
            nbt.putString("Chestplate", sender.getInventory().getChestplate().getName());
            nbt.putString("Leggings", sender.getInventory().getLeggings().getName());
            nbt.putString("Boots", sender.getInventory().getBoots().getName());
        }
        return nbt;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("\u00A7cPerintah ini hanya berfungsi dalam permainan");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("npc")) {
            if (args.length < 1) {
                sender.sendMessage("\u00A7l\u00A7a--- BANTUAN NPC ---");
                sender.sendMessage("\u00A73Munculkan NPC: \u00A7e/npc spawn <perwujudan> <nama patung>");
                sender.sendMessage("\u00A73Tambahkan perintah: \u00A7e/npc addcmd <ID> <perintah>");
                sender.sendMessage("\u00A73Tambahkan perintah pemain: \u00A7e/npc addplayercmd <ID> <perintah>");
                sender.sendMessage("\u00A73Hapus perintah: \u00A7e/npc delcmd <ID> <perintah>");
                sender.sendMessage("\u00A73Hapus perintah pemain: \u00A7e/npc delplayercmd <ID> <perintah>");
                sender.sendMessage("\u00A73Hapus semua perintah: \u00A7e/npc delallcmd <ID>");
                sender.sendMessage("\u00A73Lihat semua perintah: \u00A7e/npc listcmd <ID>");
                sender.sendMessage("\u00A73Edit Patung: \u00A7e/npc edit <ID> <teleport|tp|gohere|handitem|hand|armor|size|scale|name|tphere>");
                sender.sendMessage("\u00A73Dapatkan id Patung: \u00A7e/npc getid");
                sender.sendMessage("\u00A73Dapatkan daftar semua perwujudan yang tersedia: \u00A7e/npc entities");
                sender.sendMessage("\u00A73Hapuskan Patung: \u00A7e/npc remove");
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "spawn":
                    if (args.length < 2) {
                        sender.sendMessage("\u00A7cPenggunaan: /npc spawn <perwujudan> <nama patung>");
                        return true;
                    }
                    if (!entities.contains(args[1])) {
                        sender.sendMessage("\u00A7cPerwujudan \u00A74" + args[1] + "\u00A7c tidak tersedis, silakan perintah \u00A7e/npc list\u00A7c untuk melihat semua perwujudan yang tersedia, pastikan nama perwujudan Huruf Besar.");
                        return true;
                    }
                    String name;
                    if (args.length < 2) {
                        name = "%k";
                    } else {
                        name = String.join(" ", args);
                        name = name.replaceFirst("spawn", "");
                        name = name.replaceFirst(args[1], "");
                        name = name.replaceFirst(" ", "");
                        name = name.replaceFirst(" ", "");
                    }
                    name = name.replaceAll("%n", "\n");
                    CompoundTag nbt = this.nbt(player, args, name);
                    Entity ent = Entity.createEntity("NPC_" + args[1], player.chunk, nbt);
                    ent.setNameTag(name);
                    if (!"%k".equals(name)) {
                        ent.setNameTagAlwaysVisible();
                    }
                    ent.spawnToAll();
                    sender.sendMessage("\u00A7aPatung dimunculkan dengan ID: " + ent.getId() + " dan dengan nama: " + ent.getName());
                    return true;
                case "getid":
                case "id":
                    id.add(player.getName());
                    player.sendMessage("\u00A7aMode ID - klik suatu perwujudan untuk mendapatkan ID itu");
                    return true;
                case "addcmd":
                    if (args.length < 3) {
                        sender.sendMessage("\u00A7cPenggunaan: /npc addcmd <ID> <perintah>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("\u00A7cPenggunaan: /npc addcmd <ID> <perintah>");
                        return true;
                    }
                    Entity enti = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (enti instanceof NPC_Human || enti instanceof NPC_Entity || enti.namedTag.getBoolean("npc")) {
                        String cmd;
                        cmd = String.join(" ", args);
                        cmd = cmd.replaceFirst("addcmd", "");
                        cmd = cmd.replaceFirst(args[1], "");
                        StringTag st = new StringTag(cmd, cmd);
                        if (enti.namedTag.getList("Commands", StringTag.class).getAll().contains(st)) {
                            player.sendMessage("\u00A7aPerintah sudah ditambahkan");
                            return true;
                        }
                        enti.namedTag.getList("Commands", StringTag.class).add(st);
                        player.sendMessage("\u00A7aPerintah ditambahkan");
                        return true;
                    } else {
                        player.sendMessage("\u00A7cTidak ada Patung yang ditemukan dengan ID itu");
                        return true;
                    }
                case "addplayercmd":
                    if (args.length < 3) {
                        sender.sendMessage("\u00A7cPenggunaan: /npc addplayercmd <ID> <perintah>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("\u00A7cPenggunaan: /npc addplayercmd <ID> <perintah>");
                        return true;
                    }
                    Entity enti2 = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (enti2 instanceof NPC_Human || enti2 instanceof NPC_Entity || enti2.namedTag.getBoolean("npc")) {
                        String cmd;
                        cmd = String.join(" ", args);
                        cmd = cmd.replaceFirst("addplayercmd", "");
                        cmd = cmd.replaceFirst(args[1], "");
                        StringTag st = new StringTag(cmd, cmd);
                        if (enti2.namedTag.getList("PlayerCommands", StringTag.class).getAll().contains(st)) {
                            player.sendMessage("\u00A7aPerintah Pemain sudah ditambahkan");
                            return true;
                        }
                        enti2.namedTag.getList("PlayerCommands", StringTag.class).add(st);
                        player.sendMessage("\u00A7aPerintah Pemain ditambahkan");
                        return true;
                    } else {
                        player.sendMessage("\u00A7cTidak ada Patung yang ditemukan dengan ID itu");
                        return true;
                    }
                case "listcmd":
                    if (args.length < 2) {
                        sender.sendMessage("\u00A7cPenggunaan: /npc listcmd <ID>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        sender.sendMessage("\u00A7cPenggunaan: /npc listcmdd <ID>");
                        return true;
                    }
                    Entity entity = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (entity instanceof NPC_Entity || entity instanceof NPC_Human || entity.namedTag.getBoolean("npc")) {
                        List<StringTag> cmddd = entity.namedTag.getList("Commands", StringTag.class).getAll();
                        player.sendMessage("\u00A7aPerintah dari \u00A7e" + entity.getName() + " (" + entity.getId() + ")\u00A7a:");
                        for (StringTag cmdd : cmddd) {
                            player.sendMessage(cmdd.data);
                        }
                        List<StringTag> cmdddd = entity.namedTag.getList("PlayerCommands", StringTag.class).getAll();
                        player.sendMessage("\u00A7aPerintah Pemain dari \u00A7e" + entity.getName() + " (" + entity.getId() + ")\u00A7a:");
                        for (StringTag cmdd : cmdddd) {
                            player.sendMessage(cmdd.data);
                        }
                        return true;
                    } else {
                        player.sendMessage("\u00A7cTidak ada Patung yang ditemukan dengan ID itu");
                        return true;
                    }
                case "delcmd":
                    if (args.length < 3) {
                        sender.sendMessage("\u00A7cPenggunaan: /npc delcmd <ID> <perintah>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("\u00A7cPenggunaan: /npc delcmd <ID> <perintah>");
                        return true;
                    }
                    Entity en = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (en instanceof NPC_Human || en instanceof NPC_Entity || en.namedTag.getBoolean("npc")) {
                        String cmd;
                        cmd = String.join(" ", args);
                        cmd = cmd.replaceFirst("delcmd", "");
                        cmd = cmd.replaceFirst(args[1], "");
                        StringTag st = new StringTag(cmd, cmd);
                        if (en.namedTag.getList("Commands", StringTag.class).getAll().contains(st)) {
                            en.namedTag.getList("Commands", StringTag.class).remove(st);
                            player.sendMessage("\u00A7aPerintah \u00A7e" + cmd + "\u00A7a dihapus");
                            return true;
                        } else {
                            player.sendMessage("\u00A7cPerintah \u00A7e" + cmd + "\u00A7c tidak ditemukan");
                            return true;
                        }
                    } else {
                        player.sendMessage("\u00A7cTidak ada Patung yang ditemukan dengan ID itu");
                        return true;
                    }
                case "delplayercmd":
                    if (args.length < 3) {
                        sender.sendMessage("\u00A7cPenggunaan: /npc delplayercmd <ID> <perintah>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("\u00A7cPenggunaan: /npc delplayercmd <ID> <perintah>");
                        return true;
                    }
                    Entity en2 = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (en2 instanceof NPC_Human || en2 instanceof NPC_Entity || en2.namedTag.getBoolean("npc")) {
                        String cmd;
                        cmd = String.join(" ", args);
                        cmd = cmd.replaceFirst("delplayercmd", "");
                        cmd = cmd.replaceFirst(args[1], "");
                        StringTag st = new StringTag(cmd, cmd);
                        if (en2.namedTag.getList("PlayerCommands", StringTag.class).getAll().contains(st)) {
                            en2.namedTag.getList("PlayerCommands", StringTag.class).remove(st);
                            player.sendMessage("\u00A7aPerintah Pemain \u00A7e" + cmd + "\u00A7a dihapus");
                            return true;
                        } else {
                            player.sendMessage("\u00A7cPerintah Pemain \u00A7e" + cmd + "\u00A7c tidak ditemukan");
                            return true;
                        }
                    } else {
                        player.sendMessage("\u00A7cTidak ada Patung yang ditemukan dengan ID itu");
                        return true;
                    }
                case "delallcmd":
                    if (args.length < 2) {
                        sender.sendMessage("\u00A7cPenggunaan: /npc delallcmd <ID>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("\u00A7cPenggunaan: /npc delallcmd <ID>");
                        return true;
                    }
                    Entity en3 = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (en3 instanceof NPC_Human || en3 instanceof NPC_Entity || en3.namedTag.getBoolean("npc")) {
                        en3.namedTag.putList(new ListTag<StringTag>("Commands")).putList(new ListTag<StringTag>("PlayerCommands"));
                        sender.sendMessage("\u00A7aSemua Perintah dihapus");
                    } else {
                        player.sendMessage("\u00A7cTidak ada Patung yang ditemukan dengan ID itu");
                        return true;
                    }
                case "edit":
                    if (args.length < 3) {
                        player.sendMessage("\u00A7cPenggunaan: /npc edit <ID> <teleport|tp|gohere|handitem|hand|armor|size|scale|name|tphere>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        sender.sendMessage("\u00A7cPenggunaan: /npc edit <ID> <teleport|tp|gohere|handitem|hand|armor|size|scale|name|tphere>");
                        return true;
                    }
                    Entity e = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (e == null) {
                        player.sendMessage("\u00A7cTidak ada Perwujudan yang ditemukan dengan ID itu");
                        return true;
                    }
                    PlayerInventory pl = player.getInventory();
                    switch (args[2].toLowerCase()) {
                        case "handitem":
                        case "item":
                        case "hand":
                            if (e instanceof NPC_Human || e.namedTag.getBoolean("ishuman")) {
                                NPC_Human nh = (NPC_Human) e;
                                nh.getInventory().setItemInHand(pl.getItemInHand());
                                player.sendMessage("\u00A7aBarang diubah menjadi \u00A7e" + pl.getItemInHand().getName());
                                nh.namedTag.putString("Item", pl.getItemInHand().getName());
                                return true;
                            } else {
                                player.sendMessage("\u00A7cPerwujudan itu tidak dapat memegang barang");
                                return true;
                            }
                        case "armor":
                            if (e instanceof NPC_Human || e.namedTag.getBoolean("ishuman")) {
                                NPC_Human nh = (NPC_Human) e;
                                nh.getInventory().setHelmet(pl.getHelmet());
                                player.sendMessage("\u00A7aHelm diubah menjadi \u00A7e" + pl.getHelmet().getName());
                                nh.namedTag.putString("Helmet", pl.getHelmet().getName());
                                nh.getInventory().setChestplate(pl.getChestplate());
                                player.sendMessage("\u00A7aPeti Piring diubah menjadi \u00A7e" + pl.getChestplate().getName());
                                nh.namedTag.putString("Chestplate", pl.getChestplate().getName());
                                nh.getInventory().setLeggings(pl.getLeggings());
                                player.sendMessage("\u00A7aKaki diubah menjadi \u00A7e" + pl.getLeggings().getName());
                                nh.namedTag.putString("Leggings", pl.getLeggings().getName());
                                nh.getInventory().setBoots(pl.getBoots());
                                player.sendMessage("\u00A7aSepatu diubah menjadi \u00A7e" + pl.getBoots().getName());
                                nh.namedTag.putString("Boots", pl.getBoots().getName());
                                return true;
                            } else {
                                player.sendMessage("\u00A7cTidak ada Patung yang ditemukan dengan ID itu");
                                return true;
                            }
                        case "scale":
                        case "size":
                            if (args.length < 4) {
                                player.sendMessage("\u00A7cPenggunaan: /npc edit <ID> scale <int> \u00A7eStandar adalah 1.");
                                return true;
                            }
                            if (!isFloat(args[3])) {
                                player.sendMessage("\u00A7cPenggunaan: /npc edit <ID> scale <int>  \u00A7eStandar adalah 1.");
                                return true;
                            }
                            if (Float.parseFloat(args[3]) > 25) {
                                player.sendMessage("\u00A7cSkala maksimal adalah 25");
                                return true;
                            }
                            if (e instanceof NPC_Human || e instanceof NPC_Entity || e.namedTag.getBoolean("npc")) {
                                e.setScale(Float.parseFloat(args[3]));
                                e.namedTag.putFloat("scale", Float.parseFloat(args[3]));
                                player.sendMessage("\u00A7aSkala diubah menjadi \u00A7e" + args[3]);
                                return true;
                            } else {
                                player.sendMessage("\u00A7cTidak ada Patung yang ditemukan dengan ID itu");
                                return true;
                            }
                        case "name":
                            if (args.length < 3) {
                                player.sendMessage("\u00A7cPenggunaan: /npc edit <ID> name <nama patung baru>");
                                return true;
                            }
                            if (e instanceof NPC_Human || e instanceof NPC_Entity || e.namedTag.getBoolean("npc")) {
                                if (args.length != 3) {
                                    name = String.join(" ", args);
                                    name = name.replaceFirst("edit", "");
                                    name = name.replaceFirst("name", "");
                                    name = name.replaceFirst(args[1], "");
                                    name = name.replaceFirst(" ", "");
                                    name = name.replaceFirst(" ", "");
                                    name = name.replaceFirst(" ", "");
                                } else {
                                    name = "%k";
                                    e.setNameTagVisible(false);
                                    e.setNameTagAlwaysVisible(false);
                                    player.sendMessage("\u00A7aNama dihapus");
                                }
                                name = name.replaceAll("%n", "\n");
                                if (!name.equals("%k")) {
                                    e.setNameTag(name);
                                    e.setNameTagVisible();
                                    player.sendMessage("\u00A7aNama diubah menjadi \u00A7e" + name);
                                }
                                e.namedTag.putString("NameTag", name);
                                return true;
                            } else {
                                player.sendMessage("\u00A7cTidak ada Patung yang ditemukan dengan ID itu");
                                return true;
                            }
                        case "gohere":
                        case "tphere":
                        case "tp":
                        case "teleport":
                            if (args.length < 2) {
                                player.sendMessage("\u00A7cPenggunaan /npc edit <ID> tphere");
                                return true;
                            }
                            if (e instanceof NPC_Human || e instanceof NPC_Entity || e.namedTag.getBoolean("npc")) {
                                e.teleport(player);
                                e.respawnToAll();
                                player.sendMessage("\u00A7aPerwujudan teleportasi");
                                return true;
                            }
                    }
                case "remove":
                case "kill":
                    if (kill.contains(player.getName())) {
                        kill.remove(player.getName());
                        player.sendMessage("\u00A7cMode bunuh dinonaktifkan");
                    } else {
                        kill.add(player.getName());
                        player.sendMessage("\u00A7aMode Bunuh - klik suatu perwujudan untuk menghapusnya");
                    }
                    return true;
                case "entities":
                case "list":
                    sender.sendMessage("\u00A7aPerwujudan yang tersedia: \u00A73" + entities.toString());
                    return true;
                default:
                    sender.sendMessage("\u00A7l\u00A7a--- BANTUAN NPC ---");
                    sender.sendMessage("\u00A73Munculkan NPC: \u00A7e/npc spawn <perwujudan> <nama patung>");
                    sender.sendMessage("\u00A73Tambahkan perintah: \u00A7e/npc addcmd <ID> <perintah>");
                    sender.sendMessage("\u00A73Tambahkan perintah pemain: \u00A7e/npc addplayercmd <ID> <perintah>");
                    sender.sendMessage("\u00A73Hapus perintah: \u00A7e/npc delcmd <ID> <perintah>");
                    sender.sendMessage("\u00A73Hapus perintah pemain: \u00A7e/npc delplayercmd <ID> <perintah>");
                    sender.sendMessage("\u00A73Hapus semua perintah: \u00A7e/npc delallcmd <ID>");
                    sender.sendMessage("\u00A73Lihat semua perintah: \u00A7e/npc listcmd <ID>");
                    sender.sendMessage("\u00A73Edit Patung: \u00A7e/npc edit <ID> <teleport|tp|gohere|handitem|hand|armor|size|scale|name|tphere>");
                    sender.sendMessage("\u00A73Dapatkan id Patung: \u00A7e/npc getid");
                    sender.sendMessage("\u00A73Dapatkan daftar semua perwujudan yang tersedia: \u00A7e/npc entities");
                    sender.sendMessage("\u00A73Hapuskan Patung: \u00A7e/npc remove");
                    return true;
            }
        }

        return true;
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}