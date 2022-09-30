package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.commands.SubCommand;
import net.kyori.adventure.text.Component;

public class AcceptCommand extends SubCommand {
    @Override
    public void performCommand(CommandSource source, String[] args) {
        if(source instanceof Player player) {
            if(Permission.hasPermission(player,"essentials.command.friends")) {
                if (args.length == 1) {
                    if (VEssentials.PLUGIN.server.getPlayer(args[0]).isPresent()) {
                        Players.getPlayer(player).addPlayer(VEssentials.PLUGIN.server.getPlayer(args[0]).get());
                        player.sendMessage(Messages.prefix.append(Component.text("§8Successfully accepted the friend request of §1" + VEssentials.PLUGIN.server.getPlayer(args[0]).get().getUsername())));
                    } else player.sendMessage(Messages.prefix.append(Component.text("§cCouldn't accept the Friend Request because this Player is Offline (Fixed soon)")));
                }
            }else player.sendMessage(Messages.noperms);
        }else source.sendMessage(Messages.nocons);
    }
}
