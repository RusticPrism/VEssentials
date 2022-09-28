package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.commands.SubCommand;
import net.kyori.adventure.text.Component;

public class DeclineCommand extends SubCommand {
    @Override
    public void performCommand(CommandSource source, String[] args) {
        if (source instanceof Player player) {
            if (Permission.hasPermission(player,"essentials.command.friends")) {
                if (args.length == 1) {
                    if(VEssentials.plugin.server.getPlayer(args[0]).isPresent()) {
                        Players.getPlayer(player).getRequests().remove(VEssentials.plugin.server.getPlayer(args[0]).get());
                        player.sendMessage(VEssentials.plugin.prefix.append(Component.text(  "§8Declined the Friend Request from §1" + VEssentials.plugin.server.getPlayer(args[0]).get().getUsername() + " §8!")));
                    }else player.sendMessage(VEssentials.plugin.prefix.append(Component.text( "§cCouldn't decline the Friend Request because this Player is Offline (Fixed soon)")));
                }else player.sendMessage(VEssentials.plugin.arguments);
            }else player.sendMessage(VEssentials.plugin.noperms);
        }else source.sendMessage(VEssentials.plugin.nocons);
    }
}
