package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.scheduler.RestartScheduler;
import de.rusticprism.vessentials.util.*;
import de.rusticprism.vessentials.util.commands.EssentialsCommand;
import net.kyori.adventure.text.Component;

public class ShutdownCommand extends EssentialsCommand {

    private Restart restart = null;
    public ShutdownCommand() {
        super("essentials.command.shutdown");
    }

    @Override
    public void performCommand(CommandSource source, String command, String[] args) {
        if (Permission.hasPermission(source, "essentials.command.shutdown")) {
            if(restart == null) {
                String time;
                String reason;
                if (args.length == 0) {
                    time = "1m";
                    reason = "Proxy is shutting down!";
                } else if (args.length == 1) {
                    time = args[0];
                    reason = "Proxy is shutting down!";
                } else {
                    time = args[0];
                    StringBuilder reasonbuilder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        reasonbuilder.append(args[i]).append(" ");
                    }
                    reason = reasonbuilder.substring(0, reasonbuilder.length() - 1);
                }
                restart = new Restart(source, time, reason);
                source.sendMessage(Messages.prefix.append(Component.text("§8Restarting §1Proxy §8in §1" + time)));
                RestartScheduler.run(restart);
            }else source.sendMessage(Component.text("§cServer is already Shutting down in §4" + restart.getTime() + " §cSeconds"));
        } else source.sendMessage(Messages.noperms);
    }

    @Override
    public TabCompleter complete(String[] args) {
        if(args.length == 0) {
            return TabCompleter.create().at(0,CompletionSupplier.contains("1h","1m","1s"));
        }
        String replacement = args[0].replace("h","").replace("m","").replace("s","");
        return TabCompleter.create().at(0, CompletionSupplier.contains(replacement + "h", replacement + "m", replacement + "s")).from(1,CompletionSupplier.contains("[Reason]"));
    }
}
