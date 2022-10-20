package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;

public class TestCommand extends EssentialsCommand {
    public TestCommand() {
        super("test");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.create();
    }
}
