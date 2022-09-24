package de.rusticprism.vessentials.util.commands;

import com.velocitypowered.api.command.CommandSource;

import java.util.List;

public abstract class EssentialsCommand {
    public abstract void performCommand(CommandSource source, String command, String[] args);

    public abstract List<String> complete(String[] args);
}
