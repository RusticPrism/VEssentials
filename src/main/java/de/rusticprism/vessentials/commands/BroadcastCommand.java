package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.*;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;

public class BroadcastCommand extends EssentialsCommand {
    public BroadcastCommand() {
        super("essentials.command.broadcast");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if (source instanceof Player player) {
            if (Permission.hasPermission(player, "essentials.command.broadcast")) {
                if (args.length >= 1) {
                    if (VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
                        StringBuilder broadcast = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            broadcast.append(args[i]).append(" ");
                        }
                        VEssentials.PLUGIN.server.getServer(args[0]).get().sendMessage(PlaceHolders.replaceAsComponent("<dark_gray>[<dark_blue>Broadcast<dark_gray>]<reset> " + broadcast));
                    } else if (args[0].equalsIgnoreCase("all")) {
                        StringBuilder broadcast = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            broadcast.append(args[i]).append(" ");
                        }
                        VEssentials.PLUGIN.server.sendMessage(PlaceHolders.replaceAsComponent("<dark_gray>[<dark_blue>Broadcast<dark_gray>]<reset>" + broadcast));
                    } else player.sendMessage(Messages.prefix.append(Component.text("§cInvalid Arguments!")));
                } else player.sendMessage(Messages.toManyArgs);
            } else player.sendMessage(Messages.noperms);
        } else source.sendMessage(Messages.nocons);
    }

    @Override
    public TabCompleter complete(String[] args) {
        List<String> servers = new java.util.ArrayList<>(VEssentials.PLUGIN.server.getConfiguration().getServers().keySet().stream().toList());
        servers.add("all");
        return TabCompleter.create().at(0, CompletionSupplier.contains(servers))
                .from(1, CompletionSupplier.contains("[Message]"));
    }
}
