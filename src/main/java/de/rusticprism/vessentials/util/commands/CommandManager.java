package de.rusticprism.vessentials.util.commands;

import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.TabCompleter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
      /*  LiteralCommandNode<CommandSource> command1 = LiteralArgumentBuilder.<CommandSource>literal(command)
                .requires(source -> source.hasPermission(maincommands.get(command).getPermission()))
                .executes(context -> {
                    maincommands.get(command).performCommand(context.getSource(),command,context.getArguments().keySet().toArray(new String[0]));
                    return Command.SINGLE_SUCCESS;
                }).build();
        ArgumentCommandNode<CommandSource, String> players =  RequiredArgumentBuilder.<CommandSource,  String>argument("argument", StringArgumentType.string())
                .suggests((ctx, builder) -> {
                    for(String str : maincommands.get(command).complete(ctx.getArguments().keySet().toArray(new String[0])).complete(ctx.getArguments().keySet().stream().toList())) {
                        builder.suggest(str);
                    }
                    return builder.buildFuture();
                })
                .executes(context -> {
                            maincommands.get(command).performCommand(context.getSource(),command,context.getArguments().keySet().toArray(new String[0]));
                            // Returning Command.SINGLE_SUCCESS means that the execution was successful
                            // Returning BrigadierCommand.FORWARD will send the command to the server
                            return Command.SINGLE_SUCCESS;
                        }).build();
        command1.addChild(players);
        VEssentials.PLUGIN.server.getCommandManager().register(new BrigadierCommand(command1));
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
