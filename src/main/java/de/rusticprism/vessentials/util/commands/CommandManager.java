package de.rusticprism.vessentials.util.commands;

import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.TabCompleter;

import java.util.*;

public class CommandManager {
    private final HashMap<String, EssentialsCommand> maincommands;

    public CommandManager() {
        maincommands = new HashMap<>();
    }

    public void registerMain(String command, EssentialsCommand perform, String... alias) {
        maincommands.put(command, perform);
        for (String alia : alias) {
            maincommands.put(alia,perform);
        }
        /*
        new BrigadierCommand();
        /\
        Um Command nur anzuzeigen wenn richtige Permission!
         */
        VEssentials.PLUGIN.server.getCommandManager().register(command,new de.rusticprism.vessentials.commands.EssentialsCommand(),alias);
    }

    public List<EssentialsCommand> getMainCommands() {
        return maincommands.values().stream().toList();
    }

    public EssentialsCommand getMainCommand(String command) {
        return maincommands.get(command);
    }

    public boolean perform(String command, CommandSource source, String[] args) {
        if(maincommands.containsKey(command)) {
            maincommands.get(command).performCommand(source,command,args);
            return true;
        }else return false;
    }

    public List<String> complete(String command, CommandSource source, String[] args) {
        if(!maincommands.containsKey(command)) {
            return TabCompleter.create().complete(Arrays.stream(args).toList());
        }
        return maincommands.get(command).complete(args).complete(Arrays.stream(args).toList());
    }
}
