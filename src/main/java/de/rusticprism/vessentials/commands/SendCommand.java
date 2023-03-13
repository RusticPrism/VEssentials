package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;

import java.util.stream.Collectors;

@CommandInfo(name = "send", permission = "essentials.command.send")
public class SendCommand extends PluginCommand {
    @Override
    public void execute(CommandSource source, String[] args) {
        if (args.length == 2) {
            if (VEssentials.PLUGIN.server.getPlayer(args[0]).isEmpty()) {
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("Argument 1 has to be an Player.")));
                return;
            }
            if (VEssentials.PLUGIN.server.getServer(args[1]).isEmpty()) {
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("Argument 2 has to be a Server.")));
                return;
            }
            VEssentials.PLUGIN.server.getPlayer(args[0]).get().createConnectionRequest(VEssentials.PLUGIN.server.getServer(args[1]).get()).fireAndForget();
            source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("Successfully connect %player_name% to %player_server%", VEssentials.PLUGIN.server.getPlayer(args[0]).get())));
        } else source.sendMessage(Messages.toManyArgs);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return new TabCompleter(1, VEssentials.PLUGIN.server.getAllServers().stream().map(registeredServer ->
                registeredServer.getServerInfo().getName()).collect(Collectors.toList()));
    }
}
