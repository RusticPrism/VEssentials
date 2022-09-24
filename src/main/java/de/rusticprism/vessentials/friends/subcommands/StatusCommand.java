package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.commands.SubCommand;
import net.kyori.adventure.text.Component;

public class StatusCommand extends SubCommand {
    @Override
    public void performCommand(CommandSource source, String[] args) {
        if(source instanceof Player player) {
            if(player.hasPermission("essentials.command.friends")) {
                if(args.length == 0) {
                    player.sendMessage(Component.text(VEssentials.plugin.prefix + "§8Your status is: §1" + Players.getPlayer(player).getStatus() + "§8!"));
                    return;
                }
                Players.getPlayer(player).setStatus(args);
                player.sendMessage(Component.text(VEssentials.plugin.prefix + "§8Set you status to §1" + Players.getPlayer(player).getStatus() + " §8!"));
            }else player.sendMessage(Component.text(VEssentials.plugin.noperms));
        }else source.sendMessage(Component.text(VEssentials.plugin.nocons));
    }
}
