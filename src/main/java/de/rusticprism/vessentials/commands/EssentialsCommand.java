package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.SimpleCommand;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.Messages;
import net.kyori.adventure.text.Component;

import java.util.Arrays;
import java.util.List;

public class EssentialsCommand implements SimpleCommand {


    @Override
    public void execute(Invocation invocation) {
        if(!VEssentials.PLUGIN.cmdman.perform(invocation.alias(),invocation.source(),invocation.arguments())) {
            invocation.source().sendMessage(Messages.prefix.append(Component.text("§cI don't know that Command. Use /help for help!")));
        }
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        return VEssentials.PLUGIN.cmdman.complete(invocation.alias(),invocation.source(),invocation.arguments());
    }
}
