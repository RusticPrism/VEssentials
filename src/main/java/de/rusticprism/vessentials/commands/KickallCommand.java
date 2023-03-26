package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
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

import java.util.ArrayList;
import java.util.List;

@CommandInfo(name = "kickall",permission = "essentials.command.kickall")
public class KickallCommand extends PluginCommand {
    @Override
    public void execute(CommandSource source, String[] args) {
        StringBuilder reason = new StringBuilder();
        if(args.length == 0) {
            VEssentials.PLUGIN.server.getAllPlayers().forEach(player ->
                    player.disconnect(PlaceHolders.translate("server-kick-message", player)));
        }
        if (args.length == 1) {
            reason.append("Kicked by an Operator ");
        } else {
            for (int i = 1; i < args.length; i++) {
                reason.append(args[i]).append(" ");
            }
        }
        Configurations.getConfig(DataConfig.class).setKickPlayer(source instanceof Player ? ((Player) source).getUsername() : "CONSOLE");
        Configurations.getConfig(DataConfig.class).setKickReason(reason.substring(0, reason.length() - 1));
        if (VEssentials.PLUGIN.server.getServer(args[0]).isPresent()) {
            VEssentials.PLUGIN.server.getServer(args[0]).get().getPlayersConnected().forEach(player ->
                    player.disconnect(PlaceHolders.translate("server-kick-message", player)));
        } else if (args[0].equalsIgnoreCase("all")) {
            VEssentials.PLUGIN.server.getAllPlayers().forEach(player ->
                    player.disconnect(PlaceHolders.translate("server-kick-message", player)));
        } else source.sendMessage(Messages.args);
    }
    @Override
    public TabCompleter complete(String[] args) {
        List<String> names = new ArrayList<>();
        for(RegisteredServer server : VEssentials.PLUGIN.server.getAllServers()) {
            names.add(server.getServerInfo().getName());
        }
        names.add("all");
        return new TabCompleter(0,names)
                .from(1,"[REASON]");
    }
}