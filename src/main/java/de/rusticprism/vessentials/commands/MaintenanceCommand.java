package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.*;
import net.kyori.adventure.text.Component;

@CommandInfo(name = "vmaintenance",permission = "essentials.command.vmaintenance")
public class MaintenanceCommand extends PluginCommand {
    @Override
    public void execute(CommandSource source, String[] args) {
        DataConfig config = Configurations.getConfig(DataConfig.class);
        if (args.length == 0) {
            if (config.isMaintenance()) {
                config.setMaintenance(false);
                source.sendMessage(Messages.prefix.append(Component.text("§8Successfully §1disabled §8the maintenance mode!")));
            } else {
                config.setMaintenance(true);
                config.setMaintenancereason("Network Maintenance");
                config.setMaintenanceplayer(source instanceof Player player ? player.getUsername() : "CONSOLE");
                source.sendMessage(Messages.prefix.append(Component.text("§8Successfully §1enabled §8the maintenance mode!")));
                kickAllPlayer();
            }
        } else {
            if (config.isMaintenance()) {
                config.setMaintenance(false);
                source.sendMessage(Messages.prefix.append(Component.text("§8Successfully §1disabled §8the maintenance mode!")));
            } else {
                StringBuilder builder = new StringBuilder();
                for (String str : args) {
                    builder.append(str).append(" ");
                }
                config.setMaintenance(true);
                config.setMaintenancereason(builder.substring(0, builder.length() - 1));
                config.setMaintenanceplayer(source instanceof Player player ? player.getUsername() : "CONSOLE");
                source.sendMessage(Messages.prefix.append(Component.text("§8Successfully §1enabled §8the maintenance mode!")));
                kickAllPlayer();
            }
        }
    }
    private void kickAllPlayer() {
        for(Player player : VEssentials.PLUGIN.server.getAllPlayers()) {
            if(Permission.hasPermission(player,"vessentials.maintenance.bypass")) {
                player.sendMessage(PlaceHolders.translate("server-maintenance-message"));
                return;
            }
            player.disconnect(PlaceHolders.translate("server-maintenance-kick",player));
        }
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.EMPTY.from(0,"Construction","Update","DDOSAttacks","[REASON]");
    }
}
