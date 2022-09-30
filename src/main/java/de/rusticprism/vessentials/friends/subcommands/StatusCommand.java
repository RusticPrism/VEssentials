package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.commands.SubCommand;
import net.kyori.adventure.text.Component;

public class StatusCommand extends SubCommand {
    @Override
    public void performCommand(CommandSource source, String[] args) {
        if(source instanceof Player player) {
            if(Permission.hasPermission(player,"essentials.command.friends")) {
                if(args.length == 0) {
                    player.sendMessage(Messages.prefix.append(Component.text( "§8Your status is: §1" + Players.getPlayer(player).getStatus() + "§8!")));
                    return;
                }
                Players.getPlayer(player).setStatus(args);
                player.sendMessage(Messages.prefix.append(Component.text( "§8Set you status to §1" + Players.getPlayer(player).getStatus() + " §8!")));
            }else player.sendMessage(Messages.noperms);
        }else source.sendMessage(Messages.nocons);
    }
}
