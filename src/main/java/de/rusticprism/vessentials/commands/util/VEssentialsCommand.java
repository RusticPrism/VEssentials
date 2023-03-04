package de.rusticprism.vessentials.commands.util;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.Messages;
import net.kyori.adventure.text.Component;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class VEssentialsCommand implements SimpleCommand {
    public static VEssentialsCommand INSTANCE;
    private final HashMap<String, PluginCommand> commands;

    public VEssentialsCommand() {
        INSTANCE = this;
        commands = new HashMap<>();
    }
    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();
        PluginCommand pluginCommand = commands.get(invocation.alias());
        if(!pluginCommand.getCommandInfo().permission().isEmpty()) {
            if(!source.hasPermission(pluginCommand.getCommandInfo().permission())) {
                source.sendMessage(Messages.noperms.append(Component.text(" <dark_red>(" + pluginCommand.getCommandInfo().permission() + ")")));
                return;
            }
        }
        if(pluginCommand.getCommandInfo().requiresPlayer()) {
            if(source instanceof Player) {
                pluginCommand.execute((Player) source, args);
            }else {
                source.sendMessage(Messages.nocons);
            }
            return;
        }
        pluginCommand.execute(source,args);
    }


    @Override
    public CompletableFuture<List<String>> suggestAsync(Invocation invocation) {
        String[] args = invocation.arguments();
        TabCompleter completer = commands.get(invocation.alias()).complete(args);
        HashMap<Integer,String[]> completions = completer.getCompletions();
        int arg = args.length == 0 ? 0 : args.length - 1;
        String[] completion = completions.get(arg);
        //System.out.println(Arrays.toString(args) + " " + completions.keySet() + " " + completions.values() + " " + Arrays.toString(completion));
        //Return From Completion
        if(completer.getFrom() != -1 && completer.getFrom() <= arg) {
            //Check if Completion is null
            if(completer.getCompletion()[0] == null) {
                return CompletableFuture.completedFuture(Collections.emptyList());
            }
            return CompletableFuture.completedFuture(TabCompleter
                    .sort(Arrays.stream(completer.getCompletion()).collect(Collectors.toList()), arg == 0 ? " " : args[arg]));
        }
        //Check if Completion is null
        if(completion == null) {
            return CompletableFuture.completedFuture(TabCompleter
                    .sort(VEssentials.PLUGIN.server.getAllPlayers().stream().map(Player::getUsername).collect(Collectors.toList()), arg == 0 ? " " : args[arg]));
        }
        //Return Completion
        return CompletableFuture.completedFuture(TabCompleter
                .sort(Arrays.stream(completion).collect(Collectors.toList()), arg == 0 ? " " : args[arg]));
    }
    public void registerCommands() {
        for (Class<?> clazz : new Reflections("de.rusticprism.vessentials.commands").getTypesAnnotatedWith(CommandInfo.class)) {
            try {
                PluginCommand command = (PluginCommand) clazz.getDeclaredConstructor().newInstance();
                commands.put(command.getCommandInfo().name(),command);
                VEssentials.PLUGIN.server.getCommandManager().register(command.getCommandInfo().name(),this);
                for(String str : command.getCommandInfo().aliases()) {
                    if(str.equalsIgnoreCase("")) continue;
                    commands.put(str, command);
                    VEssentials.PLUGIN.server.getCommandManager().register(str, this);
                }
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                VEssentials.PLUGIN.logger.error("Error Registering Commands");
                e.printStackTrace();
            }
        }
    }
}