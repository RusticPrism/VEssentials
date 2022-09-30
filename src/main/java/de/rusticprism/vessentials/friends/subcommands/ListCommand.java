package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Friend;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.commands.SubCommand;
import net.kyori.adventure.text.Component;

public class ListCommand extends SubCommand {
    @Override
    public void performCommand(CommandSource source, String[] args) {
        if(source instanceof Player player) {
            if(Permission.hasPermission(player,"essentials.command.friends")) {
                if(Players.getPlayer(player).getFriends().isEmpty()) {
                    player.sendMessage(Messages.prefix.append(Component.text("§cYou don't have any friends yet!")));
                }else {
                    StringBuilder builder = new StringBuilder();
                    for (Friend friend : Players.getPlayer(player).getFriends()) {
                        builder.append(friend.getName()).append(", ");
                    }
                   String friends = builder.substring(0,builder.length() - 1);
                    player.sendMessage(Messages.prefix.append(Component.text( "§8List of you friends: §1" + friends + "§8!")));
                }
            }else player.sendMessage(Messages.noperms);
        }else source.sendMessage(Messages.nocons);
    }
}
