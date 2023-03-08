package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;

@CommandInfo(name = "send", permission = "essentials.command.send", requiresPlayer = false)
public class SendCommand extends PluginCommand {

    @Override
    public void execute(CommandSource source, String[] args) {
        if(args.length == 2) {

        }else source.sendMessage(Message);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return null;
    }
}
