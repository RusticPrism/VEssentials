package de.rusticprism.vessentials.util.commands;

import com.velocitypowered.api.command.CommandSource;

public abstract class SubCommand {
    private EssentialsCommand maincommand;

    public abstract void performCommand(CommandSource source, String[] args);

    public void setMainCommand(EssentialsCommand command) {
        maincommand = command;
    }
    public EssentialsCommand getMainCommand() {
        return maincommand;
    }
}
