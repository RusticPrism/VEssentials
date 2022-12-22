package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.*;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class MessageCommand extends EssentialsCommand {
    public MessageCommand() {
        super("essentials.command.msg");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if(source instanceof Player player) {
            if(Permission.hasPermission(player,"essentials.command.msg")) {
                if(args.length >= 2) {
                    if (VEssentials.PLUGIN.server.getPlayer(args[0]).isPresent()) {
                        Player target = VEssentials.PLUGIN.server.getPlayer(args[0]).get();
                        StringBuilder builder = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }
                        player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<dark_gray>[<dark_blue>me <dark_gray>-> <dark_blue>" + target.getUsername() + "<dark_gray>]<reset>" + builder)));
                        target.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<dark_gray>[<dark_blue>" + player.getUsername() + " <dark_gray>-> <dark_blue>me<dark_gray>]<reset>" + builder)));
                    } else player.sendMessage(Messages.playerNotOnline);
                }else player.sendMessage(Messages.toFewArgs);
            }else player.sendMessage(Messages.noperms);
        }else source.sendMessage(Messages.nocons);
    }

    @Override
    public TabCompleter complete(String[] args) {
        List<String> playernames = new ArrayList<>();
        for(Player player : VEssentials.PLUGIN.server.getAllPlayers()) {
            playernames.add(player.getUsername());
        }
        return TabCompleter.create().at(0, CompletionSupplier.contains(playernames))
                .from(1,CompletionSupplier.contains("[Message]"));
    }
}
