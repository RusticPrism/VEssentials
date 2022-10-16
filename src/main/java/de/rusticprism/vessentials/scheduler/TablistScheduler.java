package de.rusticprism.vessentials.scheduler;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.Messages;
import net.kyori.adventure.text.Component;

import java.util.concurrent.TimeUnit;

public class TablistScheduler {
    public static void run(ProxyServer server) {
            server.getScheduler().buildTask(VEssentials.PLUGIN, () -> {
                    for(Player player : VEssentials.PLUGIN.server.getAllPlayers()) {
                        player.sendPlayerListHeaderAndFooter(Component.text(Messages.replacePlayerPlaceHolder(player,VEssentials.PLUGIN.messages.header)),
                                Component.text(Messages.replacePlayerPlaceHolder(player,VEssentials.PLUGIN.messages.footer)));
                    }
                }).repeat(1L, TimeUnit.SECONDS)
                    .schedule();
    }
}
