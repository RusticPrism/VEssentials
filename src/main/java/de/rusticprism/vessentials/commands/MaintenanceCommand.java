package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.*;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

public class MaintenanceCommand extends EssentialsCommand {
    public MaintenanceCommand() {
        super("essentials.command.maintenance");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        DataConfig config = VEssentials.PLUGIN.setup.configs.getConfig(DataConfig.class);
        if (Permission.hasPermission(source, "essentials.command.maintenance")) {
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
        } else source.sendMessage(Messages.noperms);
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
        return TabCompleter.create().from(0, CompletionSupplier.contains("Construction", "Update", "DDOSAttacks", "[Reason]"));
    }
}
