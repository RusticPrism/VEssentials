package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.util.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

@CommandInfo(name = "joinme",permission = "essentials.command.joinme",requiresPlayer = true)
public class JoinmeCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            VEssentials.PLUGIN.server.getAllPlayers().forEach(player1 -> player1.sendMessage(PlaceHolders.translate("server-joinme-message", player)
                    .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, PlaceHolders.replacePlaceHolders("joinme %player_server%", player)))
                    .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, PlaceHolders.replaceAsComponent("<gray>Click here to join")))));
        }else {
            if (VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
                player.createConnectionRequest(VEssentials.PLUGIN.server.getServer(args[0]).get());
            } else player.sendMessage(PlaceHolders.replaceAsComponent("<red>ERROR"));
        }
    }


    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.EMPTY;
    }
}
