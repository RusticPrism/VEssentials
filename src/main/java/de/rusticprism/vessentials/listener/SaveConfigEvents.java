package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyReloadEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.Configuration;

public class SaveConfigEvents {

    @Subscribe
    public void onShutdown(ProxyShutdownEvent e) {
        for(Configuration configuration : VEssentials.PLUGIN.setup.configs.configs.values()) {
            configuration.saveConfig();
        }

    }
    @Subscribe
    public void onReload(ProxyReloadEvent e) {
        for(Configuration configuration : VEssentials.PLUGIN.setup.configs.configs.values()) {
            configuration.saveConfig();
        }
    }
}
