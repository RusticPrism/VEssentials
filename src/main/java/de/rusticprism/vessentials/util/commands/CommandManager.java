package de.rusticprism.vessentials.util.commands;

import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.TabCompleter;

import java.util.*;

public class CommandManager {
    private final HashMap<String, EssentialsCommand> maincommands;
    private final HashMap<SubCommand, String> subcommands;

    public CommandManager() {
        maincommands = new HashMap<>();
        subcommands = new HashMap<>();
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

    public void registerSub(String command, SubCommand subCommand, EssentialsCommand maincommand, String... alias) {
        subcommands.put(subCommand, command);
        subCommand.setMainCommand(maincommand);
    }

    public List<EssentialsCommand> getMainCommands() {
        return maincommands.values().stream().toList();
    }

    public EssentialsCommand getMainCommand(String command) {
        return maincommands.get(command);
    }

    public List<String> getSubCommands(EssentialsCommand command) {
        List<String> commands = new ArrayList<>();
        for (SubCommand command1 : subcommands.keySet()) {
            if (command1.getMainCommand().equals(command)) {
                commands.add(subcommands.get(command1));
            }
        }
        return commands;
    }

    public boolean perform(String command, CommandSource source, String[] args) {
        if(args.length == 0 && maincommands.containsKey(command)) {
            maincommands.get(command).performCommand(source,command,args);
            return true;
        }else if (maincommands.containsKey(command)) {
            maincommands.get(command).performCommand(source, command, args);
            String[] subargs = new String[args.length - 1];
            System.arraycopy(args, 1, subargs, 0, args.length - 1);
            for (Map.Entry<SubCommand, String> entry : subcommands.entrySet()) {
                if (entry.getValue().equalsIgnoreCase(args[0])) {
                    entry.getKey().performCommand(source, subargs);
                }
            }
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
