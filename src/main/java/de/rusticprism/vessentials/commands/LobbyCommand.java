package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;

import java.util.Optional;

@CommandInfo(name = "lobby", permission = "essentials.command.lobby", requiresPlayer = true)
public class LobbyCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        Optional<RegisteredServer> lobbyserver = VEssentials.PLUGIN.server.getServer(Configurations.getConfig(DataConfig.class).getLobbyServer());
        if(lobbyserver.isEmpty()) {
            player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>The configurated LobbyServer doesn't exist!")));
            return;
        }
        player.createConnectionRequest(lobbyserver.get()).fireAndForget();
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.EMPTY;
    }
}
