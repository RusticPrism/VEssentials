package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyReloadEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.Configuration;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.GroupConfig;
import de.rusticprism.vessentials.friends.OnlinePlayer;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.groups.Group;

public class SaveConfigEvents {

    @Subscribe
    public void onShutdown(ProxyShutdownEvent e) {
        for(OnlinePlayer player : Players.getPlayers()) {
            player.getConfig().saveConfig();
        }
        for(Configuration configuration : VEssentials.PLUGIN.setup.configs.configs) {
            configuration.saveConfig();
        }

    }
    @Subscribe
    public void onReload(ProxyReloadEvent e) {
        for(OnlinePlayer player : Players.getPlayers()) {
            player.getConfig().saveConfig();
            for(Configuration configuration : VEssentials.PLUGIN.setup.configs.configs) {
                configuration.saveConfig();
            }
        }
    }
}
