package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

public class OnlineCommand extends EssentialsCommand {
    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if(source instanceof Player player) {
            if (Permission.hasPermission(player, "essentials.command.online")) {
                if(args.length == 0) {
                    player.sendMessage(VEssentials.plugin.prefix.append(Component.text(" §8There are §1" + VEssentials.plugin.server.getPlayerCount() + " §8out of §1" + VEssentials.plugin.server.getConfiguration().getShowMaxPlayers() + " §8Players online!")));
                }else player.sendMessage(VEssentials.plugin.arguments);
            }else player.sendMessage(VEssentials.plugin.noperms);
        }else source.sendMessage(VEssentials.plugin.nocons);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.create();
    }
}
