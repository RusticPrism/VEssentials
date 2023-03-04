package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.configs.util.FileConfiguration;
import de.rusticprism.vessentials.configs.util.YamlConfiguration;
import de.rusticprism.vessentials.util.Messages;

import java.io.File;
import java.util.ArrayList;

@CommandInfo(name = "nick", permission = "essnentials.command.nick", requiresPlayer = true)
public class NickCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        if(args.length == 1) {
            File file = new File(VEssentials.PLUGIN.path.toString() + "/lang/messages.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set("Test","Test");
            config.set("Fortnite", new String[]{"Player","Player1", "Player2"});
            config.saveToFile(file,"Error");
        }else player.sendMessage(Messages.toFewArgs);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.EMPTY;
    }
}
