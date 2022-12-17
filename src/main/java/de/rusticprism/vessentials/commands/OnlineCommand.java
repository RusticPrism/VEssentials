package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.CompletionSupplier;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

import java.util.concurrent.ExecutionException;

public class OnlineCommand extends EssentialsCommand {
    public OnlineCommand() {
        super("essentials.command.online");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
            if (Permission.hasPermission(source, "essentials.command.online")) {
                if(args.length == 0) {
                    source.sendMessage(Messages.prefix.append(Component.text(" §8There are §1" + VEssentials.PLUGIN.server.getPlayerCount() + " §8out of §1" + VEssentials.PLUGIN.server.getConfiguration().getShowMaxPlayers() + " §8Players online!")));
                } else if(args.length == 1){
                    if(VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
                        RegisteredServer server  = VEssentials.PLUGIN.server.getServer(args[0]).get();
                        try {
                            source.sendMessage(Messages.prefix.append(Component.text( "§8There are §1" + server.getPlayersConnected().size() + " §8out of §1" + server.ping().get().asBuilder().getMaximumPlayers() + " §8Players on the Server §1" + server.getServerInfo().getName() + "§8!")));
                        } catch (InterruptedException | ExecutionException e) {
                            source.sendMessage(Messages.prefix.append(Component.text("§cCouldn't get Server information! Server is maybe offline!")));
                        }
                    }else source.sendMessage(Messages.prefix.append(Component.text("§cThis server doesn't exist!")));
                }else source.sendMessage(Component.text(VEssentials.PLUGIN.messages.manyArgs));
            }else source.sendMessage(Messages.noperms);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.create().at(0, CompletionSupplier.contains(VEssentials.PLUGIN.server.getConfiguration().getServers().keySet()));
    }
}
