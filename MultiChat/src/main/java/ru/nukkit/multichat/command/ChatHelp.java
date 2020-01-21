package ru.nukkit.multichat.command;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import ru.nukkit.multichat.MultiChat;
import ru.nukkit.multichat.util.Message;

import static cn.nukkit.permission.BanEntry.format;

@CmdDefine(command = "chat", alias = "multichat", subCommands = "help", permission = "multichat.config.chathelp", description = Message.CHAT_HELP)
public class ChatHelp extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        Message.CHAT_HELP.print(sender);
        Message.CHAT_RELOAD.print(sender);
        Message.CHAT_SET.print(sender);
        Message.CHAT_HELP_NAMETAG_CURRENT.print(sender, MultiChat.getCfg().nametagEnabled, MultiChat.getCfg().nametagFormat);
        Message.CHAT_HELP_CURRENT.print(sender, MultiChat.getCfg().chatFormat);
        sender.sendMessage(TextFormat.colorize(format).replace("%player%", "{%0}").replace("%message%", "{%1}").replace("{%0}", "Fahrul8676").replace("{%1}", "Boom besar!"));
        return true;
    }
}
