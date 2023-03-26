package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;

@CommandInfo(name = "ping", permission = "essentials.command.ping", requiresPlayer = true)
public class PingCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Your Ping is: <blue>" + player.getPing())));
            return;
        }
        if (args.length == 1) {
            if (VEssentials.PLUGIN.server.getPlayer(args[0]).isEmpty()) {
                player.sendMessage(Messages.wrongArg(1, "Player"));
                return;
            }
            player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>" + VEssentials.PLUGIN.server.getPlayer(args[0]).get().getUsername()
                    + "´s Ping is <blue>" + VEssentials.PLUGIN.server.getPlayer(args[0]).get().getPing())));
        }
        player.sendMessage(Messages.toManyArgs);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.EMPTY;
    }
}
