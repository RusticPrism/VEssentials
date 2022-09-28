package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.util.ChatColor;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import de.rusticprism.vessentials.util.commands.Setup;
import net.kyori.adventure.text.Component;

public class BanCommand extends EssentialsCommand {
    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if(source instanceof Player player) {
            if(Permission.hasPermission(player,"essentials.command.ban")) {
                if(VEssentials.plugin.server.getPlayer(args[0]).isPresent()) {
                    Player target = VEssentials.plugin.server.getPlayer(args[0]).get();
                    if(Permission.hasPermission(target,"essentials.command.ban")) {
                        player.sendMessage(VEssentials.plugin.prefix.append(Component.text("§cYou don't have the Permission to ban this Player")));
                        return;
                    }
                    StringBuilder builder = new StringBuilder();
                    for(int i = 1; i< args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }
                    BanConfig config = (BanConfig) VEssentials.plugin.setup.configs.getConfigByName("bannedplayers");
                    config.banPlayer(String.valueOf(player.getUniqueId()),builder.toString());
                    target.disconnect(Component.text("§8-------------------------------\n"
                            + "\n §1§lYou got Banned by " + player.getUsername()
                            + "\n"
                            + "§8Reason: §1" + ChatColor.translateAlternateColorCode("&", builder.toString())
                            +"\n§8Duration: §1Lifetime"
                            + "\n \n§8-------------------------------"));
                }else player.sendMessage(VEssentials.plugin.prefix.append(Component.text("§cThis Player isn't online!")));
            }else player.sendMessage(VEssentials.plugin.noperms);
        }else source.sendMessage(VEssentials.plugin.nocons);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return null;
    }
}
