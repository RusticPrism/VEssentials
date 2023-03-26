package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;

@CommandInfo(name = "message",permission = "essentials.command.message",aliases = {"msg","whisper"},requiresPlayer = true)
public class MessageCommand extends PluginCommand {
    @Override
    public void execute(Player player, String[] args) {
        if(args.length >= 2) {
            if (VEssentials.PLUGIN.server.getPlayer(args[0]).isPresent()) {
                Player target = VEssentials.PLUGIN.server.getPlayer(args[0]).get();
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    builder.append(args[i]).append(" ");
                }
                player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>[<blue>me <gray>-> <blue>" + target.getUsername() + "<gray>]<reset>" + builder)));
                target.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>[<blue>" + player.getUsername() + " <gray>-> <blue>me<gray>]<reset>" + builder)));
            } else player.sendMessage(Messages.playerNotOnline);
        }else player.sendMessage(Messages.toFewArgs);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return new TabCompleter(0,VEssentials.PLUGIN.server.getAllPlayers().stream().map(Player::getUsername).toList())
                .from(1,"[MESSAGE]");
    }
}
