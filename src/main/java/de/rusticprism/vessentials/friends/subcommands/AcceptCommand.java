package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.commands.SubCommand;
import net.kyori.adventure.text.Component;

public class AcceptCommand extends SubCommand {
    @Override
    public void performCommand(CommandSource source, String[] args) {
        if(source instanceof Player player) {
            if(player.hasPermission("essentials.command.friends")) {
                if (args.length == 1) {
                    if (VEssentials.plugin.server.getPlayer(args[0]).isPresent()) {
                        Players.getPlayer(player).addPlayer(VEssentials.plugin.server.getPlayer(args[0]).get());
                        player.sendMessage(Component.text(VEssentials.plugin.prefix + "§8Successfully accepted the friend request of §1" + VEssentials.plugin.server.getPlayer(args[0]).get().getUsername()));
                    } else player.sendMessage(Component.text(VEssentials.plugin.prefix + "§cCouldn't accept the Friend Request because this Player is Offline (Fixed soon)"));
                }
            }else player.sendMessage(Component.text(VEssentials.plugin.noperms));
        }else source.sendMessage(Component.text(VEssentials.plugin.nocons));
    }
}
