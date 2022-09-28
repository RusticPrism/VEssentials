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

import java.util.List;

public class BroadcastCommand extends EssentialsCommand {
    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if (source instanceof Player player) {
            if (Permission.hasPermission(player, "essentials.command.broadcast")) {
                if (args.length >= 1) {
                    if (VEssentials.plugin.server.getServer(args[0]).isPresent()) {
                        StringBuilder broadcast = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            broadcast.append(args[i]).append(" ");
                        }
                        VEssentials.plugin.server.getServer(args[0]).get().sendMessage(Component.text("§8[§1Broadcast§8]§1" + ChatColor.translateAlternateColorCode("&", broadcast.toString())));
                    } else if (args[0].equalsIgnoreCase("all")) {
                        StringBuilder broadcast = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            broadcast.append(args[i]).append(" ");
                        }
                        VEssentials.plugin.server.sendMessage(Component.text("§8[§1Broadcast§8]§1" + ChatColor.translateAlternateColorCode("&", broadcast.toString())));
                    } else player.sendMessage(VEssentials.plugin.prefix.append(Component.text("§cInvalid Arguments!")));
                } else player.sendMessage(VEssentials.plugin.prefix.append(Component.text("§cTo few Arguments!")));
            } else player.sendMessage(VEssentials.plugin.noperms);
        } else source.sendMessage(VEssentials.plugin.nocons);
    }

    @Override
    public TabCompleter complete(String[] args) {
        List<String> servers = new java.util.ArrayList<>(VEssentials.plugin.server.getConfiguration().getServers().keySet().stream().toList());
        servers.add("all");
        return TabCompleter.create().from(0, CompletionSupplier.contains(servers))
                .from(1, CompletionSupplier.contains("[Message]"));
    }
}
