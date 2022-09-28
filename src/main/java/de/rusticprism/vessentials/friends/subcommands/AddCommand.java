package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.commands.SubCommand;
import net.kyori.adventure.text.Component;

public class AddCommand extends SubCommand {

    @Override
    public void performCommand(CommandSource source, String[] args) {
        if(source instanceof Player player) {
            if(Permission.hasPermission(player,"essentials.command.friends")) {
                if(VEssentials.plugin.server.getPlayer(args[0]).isEmpty()) {
                    player.sendMessage(Component.text(VEssentials.plugin.prefix + "§cThis Player is not online!"));
                }
                Players.getPlayer(VEssentials.plugin.server.getPlayer(args[0]).get()).sendRequest(player);
                player.sendMessage(VEssentials.plugin.prefix.append(Component.text( "§8Sent a friend request to §1" + VEssentials.plugin.server.getPlayer(args[0]).get().getUsername()+ " §8!")));
            }else player.sendMessage(VEssentials.plugin.noperms);
        }else source.sendMessage(VEssentials.plugin.nocons);
    }
}
