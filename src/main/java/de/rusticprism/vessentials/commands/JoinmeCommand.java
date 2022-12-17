package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.*;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

public class JoinmeCommand extends EssentialsCommand {
    public JoinmeCommand() {
        super("essentials.command.joinme");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if (source instanceof Player player) {
            if (Permission.hasPermission(source, "essentials.command.joinme")) {
                if (args.length == 0) {
                    VEssentials.PLUGIN.server.getAllPlayers().forEach(player1 -> player1.sendMessage(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.joinme,player)
                            .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND,PlaceHolders.replacePlaceHolders("joinme %player_server%",player)))
                            .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,Component.text("§8Click here to join")))));
                }else {
                    if(VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
                        player.createConnectionRequest(VEssentials.PLUGIN.server.getServer(args[0]).get());
                    }else player.sendMessage(Component.text("§cERROR"));
                }
            } else source.sendMessage(Messages.noperms);
        }
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.create();
    }
}
