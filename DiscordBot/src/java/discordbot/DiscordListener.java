package discordbot;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.TextFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringJoiner;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordListener
extends ListenerAdapter {
    private static final Map<String, String> colors = new HashMap<String, String>(){
        {
            this.put("99AAB5", "\u00a7f");
            this.put("1ABC9C", "\u00a7a");
            this.put("2ECC71", "\u00a7a");
            this.put("3498DB", "\u00a73");
            this.put("9B59B6", "\u00a75");
            this.put("E91E63", "\u00a7d");
            this.put("F1C40F", "\u00a7e");
            this.put("E67E22", "\u00a76");
            this.put("E74C3C", "\u00a7c");
            this.put("95A5A6", "\u00a77");
            this.put("607D8B", "\u00a78");
            this.put("11806A", "\u00a72");
            this.put("1F8B4C", "\u00a72");
            this.put("206694", "\u00a71");
            this.put("71368A", "\u00a75");
            this.put("AD1457", "\u00a7d");
            this.put("C27C0E", "\u00a76");
            this.put("A84300", "\u00a76");
            this.put("992D22", "\u00a74");
            this.put("979C9F", "\u00a77");
            this.put("546E7A", "\u00a78");
        }
    };
    private String lastMessage;
    private String lastName;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor() == null || e.getMember() == null || e.getAuthor().getId() == null || Main.jda == null || Main.jda.getSelfUser() == null || Main.jda.getSelfUser().getId() == null || e.getAuthor().equals(Main.jda.getSelfUser())) {
            return;
        }
        if (!e.getChannel().getId().equals(Main.channelId)) {
            return;
        }
        if (e.getAuthor().isBot() && !Main.config.getBoolean("allowBotMessages")) {
            return;
        }
        String message = TextFormat.clean((String)e.getMessage().getContentStripped());
        if (message.isEmpty() && e.getMessage().getAttachments().isEmpty()) {
            return;
        }
        if (this.processPlayerListCommand(message)) {
            return;
        }
        if (message.contains("\u098b") || message.contains("\u0f00") || message.contains("\uf80a") || message.contains("\ue00f")) {
            return;
        }
        if (message.length() > Main.config.getInt("maxMessageLength")) {
            message = message.substring(0, Main.config.getInt("maxMessageLength"));
        }
        String role = "";
        if (this.getRole(e.getMember()) != null) {
            role = " \u00a7f| " + this.getRole(this.getRole(e.getMember()));
        }
        if (!Main.config.getBoolean("enableDiscordToMinecraft")) {
            return;
        }
        String name = e.getMember().getEffectiveName();
        if (Main.config.getBoolean("spamFilter")) {
            if (message.equals(this.lastMessage) && name.equals(this.lastName)) {
                return;
            }
            this.lastMessage = message;
            this.lastName = name;
        }
        if (Main.config.getBoolean("enableMessagesToConsole")) {
            Server.getInstance().broadcastMessage(Main.config.getString("discord_prefix").replace("%role%", role) + ' ' + name + " \u00bb " + message);
        } else {
            for (Player player : Server.getInstance().getOnlinePlayers().values()) {
                player.sendMessage(Main.config.getString("discord_prefix").replace("%role%", role) + ' ' + name + " \u00bb " + message);
            }
        }
    }

    private boolean processPlayerListCommand(String message) {
        if (message.equalsIgnoreCase("!playerlist") && Main.config.getBoolean("playerListCommand")) {
            if (Server.getInstance().getOnlinePlayers().isEmpty()) {
                API.sendMessage(Main.config.getString("command_playerlist_empty"));
            } else {
                String playerlistMessage = "";
                playerlistMessage = playerlistMessage + "**" + Main.config.getString("command_playerlist_players") + " (" + Server.getInstance().getOnlinePlayers().size() + '/' + Server.getInstance().getMaxPlayers() + "):**";
                playerlistMessage = playerlistMessage + "\n```\n";
                StringJoiner players = new StringJoiner(", ");
                for (Player player : Server.getInstance().getOnlinePlayers().values()) {
                    players.add(player.getName());
                }
                if ((playerlistMessage = playerlistMessage + players.toString()).length() > 1996) {
                    playerlistMessage = playerlistMessage.substring(0, 1993) + "...";
                }
                playerlistMessage = playerlistMessage + "\n```";
                API.sendMessage(playerlistMessage);
            }
            return true;
        }
        if (message.equalsIgnoreCase("!ip") && Main.config.getBoolean("ipCommand")) {
            API.sendMessage("```\n" + Main.config.getString("commands_ip_address") + ' ' + Main.config.getString("serverIp") + '\n' + Main.config.getString("commands_ip_port") + ' ' + Main.config.getString("serverPort") + "\n```");
            return true;
        }
        return false;
    }

    private Role getRole(Member m) {
        Iterator<Role> iterator = m.getRoles().iterator();
        if (iterator.hasNext()) {
            Role role = iterator.next();
            return role;
        }
        return null;
    }

    private String getRole(Role role) {
        String hex;
        if (role == null) {
            return "";
        }
        String string = hex = role.getColor() != null ? Integer.toHexString(role.getColor().getRGB()).toUpperCase() : "99AAB5";
        if (hex.length() == 8) {
            hex = hex.substring(2);
        }
        return colors.get(hex) + role.getName();
    }

}

