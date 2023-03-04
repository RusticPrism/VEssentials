package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.util.Messages;
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
                Component servermsg = Messages.prefix.append(Component.text("§8You are currently connected to §1" + player.getCurrentServer().get().getServerInfo().getName() + "§8.")
                        .append(Component.text("\n"))
                        .append(Messages.prefix)
                        .append(Component.text("§8Available Servers: §1")));
                List<RegisteredServer> rs = (List<RegisteredServer>) VEssentials.PLUGIN.server.getAllServers();
                for(int i = 0; i < rs.size(); i++) {
                    RegisteredServer server = rs.get(i);
                    servermsg = servermsg.append(formatServer(server,player));
                    if(i != rs.size() -1) {
                        servermsg = servermsg.append(Component.text(", ", NamedTextColor.DARK_GRAY));
                    }
                }
                player.sendMessage(servermsg);
            }else if(args.length == 1) {
                if(VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
                    RegisteredServer server = VEssentials.PLUGIN.server.getServer(args[0]).get();
                    player.sendMessage(Messages.prefix.append(Component.text("§8Trying to connect to §1" + server.getServerInfo().getName() + "§8...")));
                    player.createConnectionRequest(server).fireAndForget();
                }
            }else player.sendMessage(Messages.toManyArgs);
        }else player.sendMessage(Messages.prefix.append(Component.text("§cYou are on no server!")));
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
        Component component = Component.text(server.getServerInfo().getName());
        if(server.getPlayersConnected().contains(player)) {
           component = component.hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,Component.text("§8You are connected to this Server! \n§1" +
                    server.getPlayersConnected().size() + " §8players connected")))
                   .color(TextColor.color(0x0000AA));
        }else {
           component= component.hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, Component.text("§8Click to Connect to this Server \n§1"
                    + server.getPlayersConnected().size() + " §8players connected")))
                   .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND,"/server " + server.getServerInfo().getName()))
                   .color(TextColor.color(0x555555));
        }
    return component;
    }
}
