package de.rusticprism.vessentials.scheduler;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;
import de.rusticprism.vessentials.util.Tablist;
import net.kyori.adventure.text.Component;

import java.util.concurrent.TimeUnit;

public class TablistScheduler {
    public static void run(ProxyServer server) {
            server.getScheduler().buildTask(VEssentials.PLUGIN, () -> {
                    for(Player player : VEssentials.PLUGIN.server.getAllPlayers()) {
                        if(player.getCurrentServer().isPresent() && (player.getCurrentServer().get().getServerInfo().getName().equals("lobby") || player.getCurrentServer().get().getServerInfo().getName().equals("survival"))) {
                            return;
                        }
                            player.sendPlayerListHeaderAndFooter(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.header,player),
                                    PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.footer,player));
                    }
                }).repeat(1L, TimeUnit.SECONDS)
                    .schedule();
    }
}
