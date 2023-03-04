package de.rusticprism.vessentials.commands.util;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.Messages;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.Objects;

public abstract class PluginCommand {

    private final CommandInfo commandInfo;
    public PluginCommand() {
        commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo,"Commands must have CommandInfo annotations");
    }

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    public void execute(Player player, String[] args) {}

    public void execute(CommandSource source, String[] args) {}
    public abstract TabCompleter complete(String[] args);
}
