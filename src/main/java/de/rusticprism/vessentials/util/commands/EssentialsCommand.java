package de.rusticprism.vessentials.util.commands;

import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.util.TabCompleter;

public abstract class EssentialsCommand {
    private final String permission;

    public EssentialsCommand(String permission) {
        this.permission = permission;
    }
    public abstract void performCommand(CommandSource source, String command, String[] args);

    public abstract TabCompleter complete(String[] args);

    public String getPermission() {
        return permission;
    }
}
