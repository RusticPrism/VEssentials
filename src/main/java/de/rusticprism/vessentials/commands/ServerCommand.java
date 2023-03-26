package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

import java.util.ArrayList;
import java.util.List;

@CommandInfo(name = "server",permission = "essentials.command.server",requiresPlayer = true)
public class ServerCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        if (player.getCurrentServer().isPresent()) {
            if (args.length == 0) {
                Component servermsg = Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>You are currently connected to <blue>" + player.getCurrentServer().get().getServerInfo().getName() + "<gray>.")
                        .append(PlaceHolders.replaceAsComponent("\n"))
                        .append(Messages.prefix)
                        .append(PlaceHolders.replaceAsComponent("<gray>Available Servers: <blue>")));
                List<RegisteredServer> rs = (List<RegisteredServer>) VEssentials.PLUGIN.server.getAllServers();
                for (int i = 0; i < rs.size(); i++) {
                    RegisteredServer server = rs.get(i);
                    servermsg = servermsg.append(formatServer(server, player));
                    if (i != rs.size() - 1) {
                        servermsg = servermsg.append(PlaceHolders.replaceAsComponent("<gray>, "));
                    }
                }
                player.sendMessage(servermsg);
            } else if (args.length == 1) {
                if (VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
                    RegisteredServer server = VEssentials.PLUGIN.server.getServer(args[0]).get();
                    player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Trying to connect to <blue>" + server.getServerInfo().getName() + "<gray>...")));
                    player.createConnectionRequest(server).fireAndForget();
                }
            } else player.sendMessage(Messages.toManyArgs);
        } else
            player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>You are on no server!")));
    }
    @Override
    public TabCompleter complete(String[] args) {
        List<String> names = new ArrayList<>();
        for(RegisteredServer server : VEssentials.PLUGIN.server.getAllServers()) {
            names.add(server.getServerInfo().getName());
        }
        names.add("all");
        return new TabCompleter(0,names);
    }
    public Component formatServer(RegisteredServer server, Player player) {
        Component component = PlaceHolders.replaceAsComponent("<blue>server.getServerInfo().getName()");
        if(server.getPlayersConnected().contains(player)) {
            component = component.hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, PlaceHolders.replaceAsComponent("<gray>You are connected to this Server! \n<blue>" +
                            server.getPlayersConnected().size() + " <gray>players connected")))
                    .color(TextColor.color(0x0000AA));
        }else {
            component = component.hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, PlaceHolders.replaceAsComponent("<gray>Click to Connect to this Server \n<blue>"
                            + server.getPlayersConnected().size() + " <gray>players connected")))
                    .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + server.getServerInfo().getName()))
                    .color(TextColor.color(0x555555));
        }
    return component;
    }
}
