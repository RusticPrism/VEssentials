package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CommandInfo(name = "vunban",permission = "essentials.command.unban",aliases = "vpardon")
public class UnbanCommand extends PluginCommand {

    @Override
    public void execute(CommandSource source, String[] args) {
        if(args.length == 1) {
            BanConfig config = Configurations.getConfig(BanConfig.class);
            if(config.isBannedName(args[0])) {
                config.unbanPlayerName(args[0]);
                source.sendMessage(Messages.prefix.append(Component.text("§8Successfully unbanned the Player §1" + args[0] + "§8.")));
            }else source.sendMessage(Messages.prefix.append(Component.text("§cThis Player isn't Banned!")));
        }else source.sendMessage(Messages.prefix.append(Component.text("§cUsage: /unban <PlayerName>!")));
    }
    @Override
    public TabCompleter complete(String[] args) {
        BanConfig config = Configurations.getConfig(BanConfig.class);
        List<String> names = new ArrayList<>();
        for(String uuid : config.getBannedPlayers()) {
            //names.add(config.config.get(uuid + ".name"));
        }
        names.add("[PLAYER]");
        return TabCompleter.EMPTY.at(0,names).from(1, Collections.emptyList());
    }
}
