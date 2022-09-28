package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.SimpleCommand;
import de.rusticprism.vessentials.VEssentials;
import net.kyori.adventure.text.Component;

import java.util.Arrays;
import java.util.List;

public class EssentialsCommand implements SimpleCommand {


    @Override
    public void execute(Invocation invocation) {
        if(!VEssentials.plugin.cmdman.perform(invocation.alias(),invocation.source(),invocation.arguments())) {
            invocation.source().sendMessage(VEssentials.plugin.prefix.append(Component.text("§cI don't know that Command. Use /help for help!")));
        }
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        return VEssentials.plugin.cmdman.complete(invocation.alias(),invocation.source(),invocation.arguments());
    }
}
