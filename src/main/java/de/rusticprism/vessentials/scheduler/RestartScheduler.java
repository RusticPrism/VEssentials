package de.rusticprism.vessentials.scheduler;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.PlaceHolders;
import de.rusticprism.vessentials.util.Restart;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;

import java.util.concurrent.TimeUnit;

public class RestartScheduler {

    public static void run(Restart restart) {
        VEssentials.PLUGIN.server.getScheduler().buildTask(VEssentials.PLUGIN,() -> {
            switch (restart.getTime()) {
                case 1800,600,60,30,10,9,8,7,6,5,4,3,2,1 ->
                        VEssentials.PLUGIN.server.sendMessage(PlaceHolders.translate("server-restart-message")
                                .replaceText(TextReplacementConfig.builder().matchLiteral("%restart-time%").replacement(String.valueOf(restart.getTime())).build())
                                .replaceText(TextReplacementConfig.builder().matchLiteral("%restart-reason%").replacement(restart.getReason()).build()));
                case 0 ->
                        VEssentials.PLUGIN.server.shutdown(PlaceHolders.translate("server-restart-kick")
                                .replaceText(TextReplacementConfig.builder().matchLiteral("%player_name%")
                                        .replacement(restart.getSource() instanceof Player ? ((Player) restart.getSource()).getUsername() : "CONSOLE").build())
                                .replaceText(TextReplacementConfig.builder().matchLiteral("%restart-reason%").replacement(restart.getReason()).build()));
            }
            restart.setTime(restart.getTime() - 1);
        }).repeat(1L, TimeUnit.SECONDS)
                .schedule();
    }
}
