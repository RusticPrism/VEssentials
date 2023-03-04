package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.command.CommandSource;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.scheduler.RestartScheduler;
import de.rusticprism.vessentials.util.*;
import net.kyori.adventure.text.Component;

@CommandInfo(name = "shutdown",permission = "essentials.command.shutdown",aliases = "end")
public class ShutdownCommand extends PluginCommand {

    private Restart restart = null;

    @Override
    public void execute(CommandSource source, String[] args) {
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
            if(restart.getTime() == 0) {
                restart = null;
                return;
            }
            source.sendMessage(Messages.prefix.append(Component.text("§8Restarting §1Proxy §8in §1" + time)));
            RestartScheduler.run(restart);
        }else source.sendMessage(Component.text("§cServer is already Shutting down in §4" + restart.getTime() + " §cSeconds"));
    }

    @Override
    public TabCompleter complete(String[] args) {
        if(args.length == 0) {
            return new TabCompleter(0,"1h","1m","1s");
        }
        String replacement = args[0].replace("h","").replace("m","").replace("s","");
        return new TabCompleter(0,replacement +  "h" ,replacement+"m", replacement+"s")
                .from(1,"[REASON]");
    }
}
