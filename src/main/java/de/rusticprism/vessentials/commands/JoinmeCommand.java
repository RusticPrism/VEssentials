package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.player.ResourcePackInfo;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.KreiscraftResourcePack;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;

public class JoinmeCommand extends EssentialsCommand {
    public JoinmeCommand() {
        super("essentials.command.joinme");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if (source instanceof Player player) {
            if (Permission.hasPermission(source, getPermission())) {
                if (args.length == 0) {
                    String servername = player.getCurrentServer().get().getServerInfo().getName();
                    VEssentials.PLUGIN.server.getAllPlayers().forEach(player1 -> player1.sendMessage(Component.text(VEssentials.PLUGIN.messages.joinme
                            .replaceAll("%Player%",player.getUsername()).replaceAll("%server%", servername))
                            .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND,"joinme " + servername))));
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
