package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;
import net.kyori.adventure.text.Component;

import java.util.List;

@CommandInfo(name = "broadcast",permission = "essentials.command.broadcast",aliases = "alert")
public class BroadcastCommand extends PluginCommand {

    @Override
    public void execute(CommandSource source, String[] args) {
        if (args.length >= 1) {
            if (VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
                StringBuilder broadcast = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    broadcast.append(args[i]).append(" ");
                }
                VEssentials.PLUGIN.server.getServer(args[0]).get().sendMessage(PlaceHolders.replaceAsComponent("<dark_gray>[<dark_blue>Broadcast<dark_gray>]<reset> " + broadcast));
            } else if (args[0].equalsIgnoreCase("all")) {
                StringBuilder broadcast = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    broadcast.append(args[i]).append(" ");
                }
                VEssentials.PLUGIN.server.sendMessage(PlaceHolders.replaceAsComponent("<dark_gray>[<dark_blue>Broadcast<dark_gray>]<reset>" + broadcast));
            } else source.sendMessage(Messages.prefix.append(Component.text("§cInvalid Arguments!")));
        } else source.sendMessage(Messages.toManyArgs);
    }
@Override
    public TabCompleter complete(String[] args) {
        List<String> servers = new java.util.ArrayList<>(VEssentials.PLUGIN.server.getConfiguration().getServers().keySet().stream().toList());
        servers.add("all");
        return new TabCompleter(0, servers).from(1,"[MESSAGE]");
    }
}
