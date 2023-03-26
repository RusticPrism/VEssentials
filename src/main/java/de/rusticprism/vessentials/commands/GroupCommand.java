package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.groups.Group;
import de.rusticprism.vessentials.util.*;
import net.kyori.adventure.text.Component;

import java.util.*;

//@CommandInfo(name = "vgroup",permission = "essentials.command.group")
public class GroupCommand extends PluginCommand {

    @Override
    public void execute(CommandSource source, String[] args) {
        if (args.length == 0) {
            source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>To few Arguments")));
        } else switch (args[0].toLowerCase()) {
            case "create" -> {
                Group group = VEssentials.PLUGIN.setup.groups.createGroup(args[1]);
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully created the group <blue>" + group.getName())));
            }
            case "remove" -> {
                VEssentials.PLUGIN.setup.groups.removeGroup(args[1]);
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully removed the group <blue>" + args[1])));
            }
            case "addplayer" -> {
                if (VEssentials.PLUGIN.server.getPlayer(args[2]).isPresent()) {
                    Player player = VEssentials.PLUGIN.server.getPlayer(args[2]).get();
                    VEssentials.PLUGIN.setup.groups.getGroup(args[1]).addPlayer(player.getUsername());
                    source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully added <blue>" + player.getUsername() + " <gray>to the group <blue>" + args[1])));
                } else
                    source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>This Player isn't online!")));
            }
            case "removeplayer" -> {
                VEssentials.PLUGIN.setup.groups.getGroup(args[1]).removePlayer(args[2]);
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully removed <blue>" + args[2] + " <gray>to the group <blue>" + args[1])));
            }
            case "list" -> {
                StringBuilder names = new StringBuilder();
                for(String player : VEssentials.PLUGIN.setup.groups.getGroup(args[1]).getPlayers()) {
                    names.append(player).append("<gray>, <blue>");
                }
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>List of Player in that group: <blue>" + names.substring(0, names.length() - 4) + "<gray>.")));
            }
            case "prefix" -> {
                StringBuilder prefixbuilder = new StringBuilder();
                for(int i = 2; i < args.length; i ++) {
                    prefixbuilder.append(args[i]).append(" ");
                }
                VEssentials.PLUGIN.setup.groups.getGroup(args[1]).setPrefix(prefixbuilder.substring(0, prefixbuilder.length() - 1));
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully set the <blue>prefix <gray>of the group <blue>" + args[1])));
            }
            case "suffix" -> {
                StringBuilder suffixbuilder = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    suffixbuilder.append(args[i]).append(" ");
                }
                VEssentials.PLUGIN.setup.groups.getGroup(args[1]).setSuffix(suffixbuilder.substring(0, suffixbuilder.length() - 1));
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully set the <blue>suffix <gray>of the group <blue>" + args[1])));
            }
            default ->
                    source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>You gave a wrong Argument!")));
        }
    }
    @Override
    public TabCompleter complete(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (Group group : VEssentials.PLUGIN.setup.groups.getGroups()) {
            list.add(group.getName());
        }
        for (Player player : VEssentials.PLUGIN.server.getAllPlayers()) {
            names.add(player.getUsername());
        }
        return new TabCompleter(0,"create","addplayer,","list","prefix","remove","removeplayer","suffix")
                .at(1, args.length == 0 || args[0].equalsIgnoreCase("create") ? Collections.emptyList() : list)
                .at(2, args.length <= 1 || args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("remove") ? Collections.emptyList() :
                                                        args[0].equalsIgnoreCase("addplayer") ? names :
                                                                args[0].equalsIgnoreCase("removeplayer") ? Arrays.stream(VEssentials.PLUGIN.setup.groups.getGroup(args[1]).getPlayers()).toList() : List.of("[VALUE]"));
    }
}
