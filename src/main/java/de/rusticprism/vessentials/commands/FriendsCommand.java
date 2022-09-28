package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.PluginMessage;
import de.rusticprism.vessentials.util.CompletionSupplier;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;

public class FriendsCommand extends EssentialsCommand {

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if(source instanceof Player player) {
            if(Permission.hasPermission(player,"essentials.command.friends")) {
                if (args.length == 0) {
                    PluginMessage.openInv(player);
                }
            }else player.sendMessage(VEssentials.plugin.noperms);
        }else source.sendMessage(VEssentials.plugin.nocons);
    }

    @Override
    public TabCompleter complete(String[] args) {
        if(args.length != 0) {
            if (args[0].equalsIgnoreCase("status")) {
                return TabCompleter.create().from(0, CompletionSupplier.contains("add","remove","list", "accept","decline","msg","status")).from(1, CompletionSupplier.contains("[Status]"));
            }else return TabCompleter.create().from(0, CompletionSupplier.contains("add","remove","list", "accept","decline","msg","status")).from(1,CompletionSupplier.contains("[Player]"));
        }else return TabCompleter.create().from(0, CompletionSupplier.contains("add","remove","list", "accept","decline","msg","status"));
    }
}
