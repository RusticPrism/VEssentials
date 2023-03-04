package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyReloadEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.Config;
import de.rusticprism.vessentials.configs.Configurations;

public class SaveConfigEvents {

    @Subscribe
    public void onShutdown(ProxyShutdownEvent e) {
        for(Config config : Configurations.configs.values()) {
            config.getConfig().saveToFile(config.getFile(),"Error creating the File " + config.getName() + "!");
        }
    }
    @Subscribe
    public void onReload(ProxyReloadEvent e) {
        for(Config config : Configurations.configs.values()) {
            config.getConfig().saveToFile(config.getFile(),"Error creating the File " + config.getName() + "!");
        }
    }
}
