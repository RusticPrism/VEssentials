package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.CompletionSupplier;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

import java.util.List;

public class KickallCommand extends EssentialsCommand {
    public KickallCommand() {
        super("essentials.command.kickall");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if (Permission.hasPermission(source, "essentials.command.kickall")) {
            StringBuilder reason = new StringBuilder();
            if (args.length == 1) {
                reason.append("Kicked by an Operator");
            } else {
                for (int i = 1; i < args.length; i++) {
                    reason.append(args[i]).append(" ");
                }
            }
            if (VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
                VEssentials.PLUGIN.server.getServer(args[0]).get().getPlayersConnected().forEach(player ->
                        player.disconnect(Component.text(VEssentials.PLUGIN.messages.kickmessage
                                .replace("%Player%", source instanceof Player player1 ? player1.getUsername() : "CONSOLE")
                                .replace("%Reason%", reason))));
            } else if (args[0].equalsIgnoreCase("all")) {
                VEssentials.PLUGIN.server.getAllPlayers().forEach(player ->
                        player.disconnect(Component.text(VEssentials.PLUGIN.messages.kickmessage
                                .replace("%Player%", source instanceof Player player1 ? player1.getUsername() : "CONSOLE")
                                .replace("%Reason%", reason))));
            } else source.sendMessage(Messages.prefix.append(Component.text("§cInvalid Arguments!")));
        } else source.sendMessage(Messages.noperms);
    }

    @Override
    public TabCompleter complete(String[] args) {
        List<String> server = new java.util.ArrayList<>(VEssentials.PLUGIN.server.getConfiguration().getServers().keySet().stream().toList());
        server.add("all");
        return TabCompleter.create().at(0, CompletionSupplier.contains(server)).from(1,CompletionSupplier.contains("[Reason]"));
    }
}
