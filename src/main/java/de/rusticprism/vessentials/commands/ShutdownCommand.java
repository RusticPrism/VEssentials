package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.TabCompleter;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

public class ShutdownCommand extends EssentialsCommand {
    public ShutdownCommand() {
        super("essentials.command.shutdown");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if(Permission.hasPermission(source,"essentials.command.shutdown")) {
            if(args.length == 0) {
                String name;
                if(source instanceof Player player) {
                    name = player.getUsername();
                }else name = "CONSOLE";
                source.sendMessage(Messages.prefix.append(Component.text("§8Restarting §1Proxy §8...")));
                VEssentials.PLUGIN.server.shutdown(Component.text("§8---------------------------------- \n"
                        + "\n§1Restart \n"
                        + "\n§8Reason: §1Proxy is restarting! \n"
                        + "§8Restarter: §1" + name + "\n"
                        + " \n§1Restart \n"
                        +"\n§8----------------------------------"));
            }else source.sendMessage(VEssentials.PLUGIN.arguments);
        }else source.sendMessage(Messages.noperms);
    }

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.create();
    }
}
