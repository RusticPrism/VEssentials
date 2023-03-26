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
import de.rusticprism.vessentials.util.PlaceHolders;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CommandInfo(name = "vunban",permission = "essentials.command.unban",aliases = "vpardon")
public class UnbanCommand extends PluginCommand {

    @Override
    public void execute(CommandSource source, String[] args) {
        if (args.length == 1) {
            BanConfig config = Configurations.getConfig(BanConfig.class);
            if (config.isBannedName(args[0])) {
                config.unbanPlayerName(args[0]);
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully unbanned the Player <blue>" + args[0] + "<gray>.")));
            } else
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>This Player isn't Banned!")));
        } else
            source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>Usage: /unban <PlayerName>!")));
    }
    @Override
    public TabCompleter complete(String[] args) {
        BanConfig config = Configurations.getConfig(BanConfig.class);
        List<String> names = new ArrayList<>();
        for(String uuid : config.getBannedPlayers()) {
            names.add(config.config.getString(uuid + ".name"));
        }
        names.add("[PLAYER]");
        return TabCompleter.EMPTY.at(0,names).from(1, Collections.emptyList());
    }
}
