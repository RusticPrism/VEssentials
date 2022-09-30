package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.util.UuidUtils;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.util.CompletionSupplier;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class UnbanCommand extends EssentialsCommand {
    public UnbanCommand() {
        super("essentials.command.unban");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
            if (Permission.hasPermission(source, "essentials.command.unban")) {
                if(args.length == 1) {
                    BanConfig config = (BanConfig) VEssentials.PLUGIN.setup.configs.getConfigByName("bannedplayers");
                   if(config.isBannedName(args[0])) {
                       config.unbanPlayerName(args[0]);
                       source.sendMessage(Messages.prefix.append(Component.text("§8Successfully unbanned the Player §1" + args[0] + "§8.")));
                    }else source.sendMessage(Messages.prefix.append(Component.text("§cThis Player isn't Banned!")));
                }else source.sendMessage(Messages.prefix.append(Component.text("§cUsage: /unban <PlayerName>!")));
            } else source.sendMessage(Messages.noperms);
    }

    @Override
    public TabCompleter complete(String[] args) {
        BanConfig config = (BanConfig) VEssentials.PLUGIN.setup.configs.getConfigByName("bannedplayers");
        List<String> names = new ArrayList<>();
        for(String uuid : config.getBannedPlayers()) {
            names.add(config.config.get(uuid + ".name"));
        }
        return TabCompleter.create().from(0, CompletionSupplier.contains(names));
    }
}
