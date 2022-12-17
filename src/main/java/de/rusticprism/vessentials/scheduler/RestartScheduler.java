package de.rusticprism.vessentials.scheduler;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;
import de.rusticprism.vessentials.util.Restart;
import net.kyori.adventure.text.Component;

import java.util.concurrent.TimeUnit;

public class RestartScheduler {

    public static void run(Restart restart) {
        VEssentials.PLUGIN.server.getScheduler().buildTask(VEssentials.PLUGIN,() -> {
            switch (restart.getTime()) {
                case 1800,600,60,30,10,9,8,7,6,5,4,3,2,1 ->
                        VEssentials.PLUGIN.server.sendMessage(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.restartmessage.replaceAll("%restarttime%", String.valueOf(restart.getTime()))));
                case 0 ->
                        VEssentials.PLUGIN.server.shutdown(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.restartkick.replaceAll("%Reason%",restart.getReason()).replaceAll("%Player%", restart.getSource() instanceof Player player ? player.getUsername() : "CONSOLE")));
            }
            restart.setTime(restart.getTime() - 1);
        }).repeat(1L, TimeUnit.SECONDS)
                .schedule();
    }
}
