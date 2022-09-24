package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.ChatColor;
import de.rusticprism.vessentials.util.commands.SubCommand;
import net.kyori.adventure.text.Component;

public class MessageCommand extends SubCommand {
    @Override
    public void performCommand(CommandSource source, String[] args) {
        if(source instanceof Player player) {
            if(player.hasPermission("essentials.command.friends")) {
               if(VEssentials.plugin.server.getPlayer(args[0]).isPresent()) {
                   StringBuilder builder = new StringBuilder();
                   for (int i = 1; i < args.length; i++) {
                       builder.append(args[i]).append(" ");
                   }
                   player.sendMessage(Component.text("§8[Message] §8You send a Message to §1" + VEssentials.plugin.server.getPlayer(args[0]).get().getUsername()));
                   VEssentials.plugin.server.getPlayer(args[0]).get().sendMessage(Component.text("§8[Message] §1" + player.getUsername() + " §8send you a Message >> §1" + ChatColor.translateAlternateColorCode("&", builder.toString())));
               }
            }else player.sendMessage(Component.text(VEssentials.plugin.noperms));
        }else source.sendMessage(Component.text(VEssentials.plugin.nocons));
    }
}
