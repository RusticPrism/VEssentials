package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.PluginMessage;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import de.rusticprism.vessentials.util.commands.StringUtil;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendsCommand extends EssentialsCommand {

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if(source instanceof Player player) {
            if(player.hasPermission("essentials.command.friends")) {
                if (args.length == 0) {
                    PluginMessage.openInv(player);
                }
            }else player.sendMessage(Component.text(VEssentials.plugin.noperms));
        }else source.sendMessage(Component.text(VEssentials.plugin.nocons));
    }

    @Override
    public List<String> complete(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> completions = new ArrayList<>();
        String[] argument = new String[]{"add", "remove", "list", "status", "accept", "decline", "msg"};
        if(args.length == 0) {
            completions = List.of(argument);
        }else if(args.length == 1) {
            StringUtil.copyPartialMatches(args[0],List.of(argument),completions);
            Collections.sort(completions);
        }else if(args.length == 2) {
            if (args[0].equalsIgnoreCase("status")) {
                list.add("[Status]");
            } else {
                list.add("[Player]");
            }
            StringUtil.copyPartialMatches(args[1],list,completions);
        }else {
            if(args[0].equalsIgnoreCase("status")) {
                list.add("[Status]");
            }else if(args[0].equalsIgnoreCase("msg")) {
                list.add("[Message]");
            }
            StringUtil.copyPartialMatches(args[args.length -1],list,completions);
        }
        return completions;
    }
}
