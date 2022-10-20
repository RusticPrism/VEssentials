package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.groups.Group;
import de.rusticprism.vessentials.groups.Groups;
import de.rusticprism.vessentials.util.*;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand extends EssentialsCommand {
    public GroupCommand() {
        super("essentials.command.group");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if (Permission.hasPermission(source, "essentials.command.group")) {
            if (args.length == 0) {
                source.sendMessage(Messages.prefix.append(Component.text("§cTo few Arguments")));
            } else switch (args[0].toLowerCase()) {
                case "create" -> {
                    Group group = VEssentials.PLUGIN.setup.groups.createGroup(args[1]);
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully created the group §1" + group.getName())));
                }
                case "remove" -> {
                    VEssentials.PLUGIN.setup.groups.removeGroup(args[1]);
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully removed the group §1" + args[1])));
                }
                case "addplayer" -> {
                    if (VEssentials.PLUGIN.server.getPlayer(args[2]).isPresent()) {
                        Player player = VEssentials.PLUGIN.server.getPlayer(args[2]).get();
                        VEssentials.PLUGIN.setup.groups.getGroup(args[1]).addPlayer(player.getUsername());
                        source.sendMessage(Messages.prefix.append(Component.text("§8Successfully added §1" + player.getUsername() + " §8to the group §1" + args[1])));
                    } else source.sendMessage(Messages.prefix.append(Component.text("§cThis Player isn't online!")));
                }
                case "removeplayer" -> {
                    VEssentials.PLUGIN.setup.groups.getGroup(args[1]).removePlayer(args[2]);
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully removed §1" + args[2] + " §8to the group §1" + args[1])));
                }
                case "list" -> {
                    StringBuilder names = new StringBuilder();
                    for(String player : VEssentials.PLUGIN.setup.groups.getGroup(args[1]).getPlayers()) {
                        names.append(player).append("§8, §1");
                    }
                    source.sendMessage(Messages.prefix.append(Component.text("§8List of Player in that group: §1" + names.substring(0, names.length() -4) + "§8.")));
                }
                case "prefix" -> {
                    StringBuilder prefixbuilder = new StringBuilder();
                    for(int i = 2; i < args.length; i ++) {
                        prefixbuilder.append(args[i]).append(" ");
                    }
                    VEssentials.PLUGIN.setup.groups.getGroup(args[1]).setPrefix(prefixbuilder.substring(0,prefixbuilder.length() -1));
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully set the §1prefix §8of the group §1" + args[1])));
                }
                case "suffix" -> {
                    StringBuilder suffixbuilder = new StringBuilder();
                    for(int i = 2; i < args.length; i ++) {
                        suffixbuilder.append(args[i]).append(" ");
                    }
                    VEssentials.PLUGIN.setup.groups.getGroup(args[1]).setSuffix(suffixbuilder.substring(0,suffixbuilder.length() -1));
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully set the §1suffix §8of the group §1" + args[1])));
                }
                default -> source.sendMessage(Messages.prefix.append(Component.text("§cYou gave a wrong Argument!")));
            }
            Tablist.updateTablist();
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
        return TabCompleter.create()
                .at(0, CompletionSupplier.contains("create", "addplayer", "list", "prefix", "remove", "removeplayer", "suffix"))
                .at(1, args.length == 0 || args[0].equalsIgnoreCase("create") ? CompletionSupplier.EMPTY : CompletionSupplier.contains(list))
                .at(2, args.length <= 1 || args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("remove") ? CompletionSupplier.EMPTY :
                        args[0].equalsIgnoreCase("addplayer") ? CompletionSupplier.contains(names) :
                                args[0].equalsIgnoreCase("removeplayer") ? CompletionSupplier.contains(VEssentials.PLUGIN.setup.groups.getGroup(args[1]).getPlayers()) : CompletionSupplier.contains("[Value]"));
    }
}
