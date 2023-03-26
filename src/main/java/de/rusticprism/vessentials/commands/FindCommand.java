package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;

@CommandInfo(name = "find", permission = "essentials.command.find")
public class FindCommand extends PluginCommand {
    @Override
    public void execute(CommandSource source, String[] args) {
        if (args.length != 1) {
            source.sendMessage(Messages.args);
            return;
        }
        if (VEssentials.PLUGIN.server.getPlayer(args[0]).isEmpty()) {
            source.sendMessage(Messages.playerNotOnline);
            return;
        }
        source.sendMessage(Messages.prefix.append(PlaceHolders
                .replaceAsComponent("<gray>The Player is connected to <blue>%player_server%", VEssentials.PLUGIN.server.getPlayer(args[0]).get())));
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.EMPTY;
    }
}
