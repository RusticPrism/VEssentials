package de.rusticprism.vessentials.scheduler;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.PlaceHolders;

import java.util.concurrent.TimeUnit;

public class TablistScheduler {
    public static void run(ProxyServer server) {
            server.getScheduler().buildTask(VEssentials.PLUGIN, () -> {
                    for(Player player : VEssentials.PLUGIN.server.getAllPlayers()) {
                        if(VEssentials.PLUGIN.setup.configs.getConfig(DataConfig.class).isTablist()) {
                            player.sendPlayerListHeaderAndFooter(PlaceHolders.replaceAsComponent("server-tablist-header", player),
                                    PlaceHolders.replaceAsComponent("server-tablist-footer", player));
                        }
                    }
                }).repeat(1L, TimeUnit.SECONDS)
                    .schedule();
    }
}
