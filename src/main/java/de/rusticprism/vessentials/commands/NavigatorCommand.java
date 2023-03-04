package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import dev.simplix.protocolize.api.Protocolize;
import dev.simplix.protocolize.api.player.ProtocolizePlayer;

@CommandInfo(name = "navigator", permission = "essentials.command.navigator", requiresPlayer = true)
public class NavigatorCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        ProtocolizePlayer protPlayer = Protocolize.playerProvider().player(player.getUniqueId());
    }

    @Override
    public TabCompleter complete(String[] args) {
        return null;
    }
}
