package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.*;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class KickCommand extends EssentialsCommand {

    public KickCommand() {
        super("essentials.command.kick");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
            if(Permission.hasPermission(source,"essentials.command.kick")) {
                if(VEssentials.PLUGIN.server.getPlayer(args[0]).isPresent()) {
                    Player target = VEssentials.PLUGIN.server.getPlayer(args[0]).get();
                    if(Permission.hasPermission(target,"essentials.command.kick")) {
                        source.sendMessage(Messages.prefix.append(Component.text("§cYou don't have the Permission to kick this Player")));
                        return;
                    }
                    StringBuilder builder = new StringBuilder();
                    if(args.length == 1) {
                        builder.append("Kicked by an Operator ");
                    }else {
                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }
                    }
                    target.disconnect(PlaceHolders.translate("server-kick-message",target));
                }else source.sendMessage(Messages.prefix.append(Component.text("§cThis Player isn't online!")));
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
