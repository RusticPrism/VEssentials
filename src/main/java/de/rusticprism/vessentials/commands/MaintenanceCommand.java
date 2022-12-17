package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.*;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class MaintenanceCommand extends EssentialsCommand {
    public MaintenanceCommand() {
        super("essentials.command.maintenance");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        DataConfig config = VEssentials.PLUGIN.setup.configs.getConfig(DataConfig.class);
        if (Permission.hasPermission(source, "essentials.command.maintenance")) {
            if (args.length == 0) {
                source.sendMessage(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.maintenance));
                if (config.maintenance) {
                    config.maintenance = false;
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully §1disabled §8the maintenance mode!")));
                } else {
                    config.maintenance = true;
                    config.maintenancereason = "&1&lNetwork Maintenance";
                    config.maintenanceplayer = source instanceof Player player ? player.getUsername() : "CONSOLE";
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully §1enabled §8the maintenance mode!")));
                }
            } else {
                if (config.maintenance) {
                    config.maintenance = false;
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully §1disabled §8the maintenance mode!")));
                } else {
                    StringBuilder builder = new StringBuilder();
                    for (String str : args) {
                        builder.append(str).append(" ");
                    }
                    config.maintenance = true;
                    config.maintenancereason = builder.substring(0, builder.length() - 1);
                    config.maintenanceplayer = source instanceof Player player ? player.getUsername() : "CONSOLE";
                    source.sendMessage(Messages.prefix.append(Component.text("§8Successfully §1enabled §8the maintenance mode!")));
                }
            }
        } else source.sendMessage(Messages.noperms);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.create().from(0, CompletionSupplier.contains("Construction", "Update", "DDOSAttacks", "[Reason]"));
    }
}
