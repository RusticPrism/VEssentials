package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.ChatColor;
import de.rusticprism.vessentials.util.CompletionSupplier;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MessageCommand extends EssentialsCommand {
    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if(source instanceof Player player) {
            if(Permission.hasPermission(player,"essentials.command.msg")) {
                if(VEssentials.plugin.server.getPlayer(args[0]).isPresent()) {
                    Player target = VEssentials.plugin.server.getPlayer(args[0]).get();
                    StringBuilder builder = new StringBuilder();
                    for(int i =  1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }
                    target.sendMessage(VEssentials.plugin.prefix.append(Component.text( "§8[§1" + player.getUsername() + " §8-> §1me§8]" + ChatColor.translateAlternateColorCode("&",builder.toString()))));
                }else player.sendMessage(VEssentials.plugin.prefix.append(Component.text(" §cThis Player isn't online!")));
            }else player.sendMessage(VEssentials.plugin.noperms);
        }else source.sendMessage(VEssentials.plugin.nocons);
    }

    @Override
    public TabCompleter complete(String[] args) {
        List<String> playernames = new ArrayList<>();
        for(Player player : VEssentials.plugin.server.getAllPlayers()) {
            playernames.add(player.getUsername());
        }
        return TabCompleter.create().from(0, CompletionSupplier.contains(playernames))
                .from(1,CompletionSupplier.contains("[Message]"));
    }
}
