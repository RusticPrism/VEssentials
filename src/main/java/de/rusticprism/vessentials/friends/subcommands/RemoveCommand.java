package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.commands.SubCommand;
import net.kyori.adventure.text.Component;

public class RemoveCommand extends SubCommand {
    @Override
    public void performCommand(CommandSource source, String[] args) {
        if(source instanceof Player player) {
            if(player.hasPermission("essentials.command.friends")) {
                Players.getPlayer(player).removeFriend(args[0]);
                player.sendMessage(Component.text(VEssentials.plugin.prefix + "§8Removed §1" + VEssentials.plugin.server.getPlayer(args[0]).get().getUsername()+ " §8from your friends list!"));
            }else player.sendMessage(Component.text(VEssentials.plugin.noperms));
        }else source.sendMessage(Component.text(VEssentials.plugin.nocons));
    }
}
