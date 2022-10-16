package de.rusticprism.vessentials.friends.subcommands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.ChatColor;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import net.kyori.adventure.text.Component;

public class MessageCommand  {

    public void performCommand(CommandSource source, String[] args) {
        if(source instanceof Player player) {
            if(Permission.hasPermission(player,"essentials.command.friends")) {
               if(VEssentials.PLUGIN.server.getPlayer(args[0]).isPresent()) {
                   StringBuilder builder = new StringBuilder();
                   for (int i = 1; i < args.length; i++) {
                       builder.append(args[i]).append(" ");
                   }
                   player.sendMessage(Component.text("§8[Message] §8You send a Message to §1" + VEssentials.PLUGIN.server.getPlayer(args[0]).get().getUsername()));
                   VEssentials.PLUGIN.server.getPlayer(args[0]).get().sendMessage(Component.text("§8[Message] §1" + player.getUsername() + " §8send you a Message >> §1" + ChatColor.translateAlternateColorCode("&", builder.toString())));
               }
            }else player.sendMessage(Messages.noperms);
        }else source.sendMessage(Messages.nocons);
    }
}
