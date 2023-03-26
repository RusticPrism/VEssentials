package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.*;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

@CommandInfo(name = "vkick",permission = "essentials.command.vkick")
public class KickCommand extends PluginCommand {

    @Override
    public void execute(CommandSource source, String[] args) {
        if(VEssentials.PLUGIN.server.getPlayer(args[0]).isPresent()) {
            Player target = VEssentials.PLUGIN.server.getPlayer(args[0]).get();
            if(Permission.hasPermission(target,"essentials.command.kick")) {
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>You don't have the Permission to kick this Player")));
                return;
            }
            StringBuilder builder = new StringBuilder();
            if (args.length == 1) {
                builder.append("Kicked by an Operator ");
            } else {
                for (int i = 1; i < args.length; i++) {
                    builder.append(args[i]).append(" ");
                }
            }
            Configurations.getConfig(DataConfig.class).setKickPlayer(source instanceof Player ? ((Player) source).getUsername() : "CONSOLE");
            Configurations.getConfig(DataConfig.class).setKickReason(builder.substring(0, builder.length() - 1));
            target.disconnect(PlaceHolders.translate("server-kick-message", target));
        } else source.sendMessage(Messages.wrongArg(1, "Player"));
    }
    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.EMPTY.from(1,"[REASON]");
    }
}