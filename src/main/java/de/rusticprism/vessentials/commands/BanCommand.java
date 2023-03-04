package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.PlaceHolders;
import net.kyori.adventure.text.Component;

@CommandInfo(name = "vban", permission = "essentials.command.ban")
public class BanCommand extends PluginCommand {
    @Override
    public void execute(CommandSource source, String[] args) {
            if(args.length >= 1) {
                if (VEssentials.PLUGIN.server.getPlayer(args[0]).isPresent()) {
                    Player target = VEssentials.PLUGIN.server.getPlayer(args[0]).get();
                    if (Permission.hasPermission(target, "essentials.command.ban")) {
                        source.sendMessage(Messages.prefix.append(Component.text("§cYou don't have the Permission to ban this Player")));
                        return;
                    }
                    StringBuilder builder = new StringBuilder();
                    if (args.length == 1) {
                        builder.append("Banned by an Operator ");
                    } else {
                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }
                    }
                    BanConfig config = Configurations.getConfig(BanConfig.class);
                    config.banPlayer(target, builder.toString(), "Lifetime", source instanceof Player player ? player.getUsername() : "CONSOLE");
                    target.disconnect(PlaceHolders.translate("server-ban-message",target));
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully banned the Player§1 " + target.getUsername() + "§8.")));
                } else source.sendMessage(Messages.prefix.append(Component.text("§cThis Player isn't online!")));
            }else source.sendMessage(Messages.toFewArgs);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.EMPTY.from(1,"[REASON]");
    }
}
