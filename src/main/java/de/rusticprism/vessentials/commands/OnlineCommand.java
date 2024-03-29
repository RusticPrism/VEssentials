package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@CommandInfo(name = "online", permission = "essentials.command.online", aliases = {"glist", "vlist", "players", "vplayers"})
public class OnlineCommand extends PluginCommand {
    @Override
    public void execute(CommandSource source, String[] args) {
        if (args.length == 0) {
            source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent(" <gray>There are <blue>" + VEssentials.PLUGIN.server.getPlayerCount() + " <gray>out of <blue>" + VEssentials.PLUGIN.server.getConfiguration().getShowMaxPlayers() + " <gray>Players online!")));
        } else if (args.length == 1) {
            if (VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
                RegisteredServer server = VEssentials.PLUGIN.server.getServer(args[0]).get();
                try {
                    source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>There are <blue>" + server.getPlayersConnected().size() + " <gray>out of <blue>" + server.ping().get().asBuilder().getMaximumPlayers() + " <gray>Players on the Server <blue>" + server.getServerInfo().getName() + "<gray>!")));
                } catch (InterruptedException | ExecutionException e) {
                    source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>Couldn't get Server information! Server is maybe offline!")));
                }
            } else if (args[0].equalsIgnoreCase("all")) {
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent(" <gray>There are <blue>" + VEssentials.PLUGIN.server.getPlayerCount() + " <gray>out of <blue>" + VEssentials.PLUGIN.server.getConfiguration().getShowMaxPlayers() + " <gray>Players online!")));
            } else
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>This server doesn't exist!")));
        } else source.sendMessage(Messages.toManyArgs);
    }

    @Override
    public TabCompleter complete(String[] args) {
        List<String> names = new ArrayList<>();
        for (RegisteredServer server : VEssentials.PLUGIN.server.getAllServers()) {
            names.add(server.getServerInfo().getName());
        }
        names.add("all");
        return new TabCompleter(0, names);
    }
}
