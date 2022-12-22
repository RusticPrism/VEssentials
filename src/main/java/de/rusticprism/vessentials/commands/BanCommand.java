package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.util.*;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class BanCommand extends EssentialsCommand {
    public BanCommand() {
        super("essentials.command.ban");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
            if(Permission.hasPermission(source,"essentials.command.ban")) {
                if(args.length >= 1) {
                    if (VEssentials.PLUGIN.server.getPlayer(args[0]).isPresent()) {
                        Player target = VEssentials.PLUGIN.server.getPlayer(args[0]).get();
                        if (Permission.hasPermission(target, "essentials.command.ban")) {
                            source.sendMessage(Messages.prefix.append(Component.text("§cYou don't have the Permission to ban this Player")));
                            return;
                        }
                        StringBuilder builder = new StringBuilder();
                        if (args.length == 1) {
                            builder.append("Banned by an Operator ");
                        } else {
                            for (int i = 1; i < args.length; i++) {
                                builder.append(args[i]).append(" ");
                            }
                        }
                        BanConfig config = VEssentials.PLUGIN.setup.configs.getConfig(BanConfig.class);
                        config.banPlayer(target, builder.toString(), "Lifetime", source instanceof Player player ? player.getUsername() : "CONSOLE");
                        target.disconnect(PlaceHolders.translate("server-ban-message",target));
                        source.sendMessage(Messages.prefix.append(Component.text("§8Successfully banned the Player§1 " + target.getUsername() + "§8.")));
                    } else source.sendMessage(Messages.prefix.append(Component.text("§cThis Player isn't online!")));
                }else source.sendMessage(Messages.toFewArgs);
            }else source.sendMessage(Messages.noperms);
    }

    @Override
    public TabCompleter complete(String[] args) {
        List<String> names = new ArrayList<>();
        for(Player player : VEssentials.PLUGIN.server.getAllPlayers()) {
            names.add(player.getUsername());
        }
        return TabCompleter.create().at(0, CompletionSupplier.contains(names)).from(1,CompletionSupplier.contains("[Reason]"));
    }
}
