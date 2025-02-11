package de.rusticprism.vessentials;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import de.rusticprism.vessentials.listener.*;
import dev.simplix.protocolize.api.Protocolize;
import net.kyori.adventure.Adventure;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.translation.GlobalTranslator;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "vessentials",
        name = "VEssentials",
        version = "1.0.0-SNAPSHOT",
        description = "A Essentials Velocity Plugin",
        authors = {"RusticPrism"},
        dependencies = {
                @Dependency(id = "luckperms", optional = true),
                @Dependency(id = "protocolize", optional = true)
        }
)
public class VEssentials {

    public final Logger logger;
    public final ProxyServer server;
    public static VEssentials PLUGIN;
    public final Path path;
    public Setup setup;

    @Inject
    public VEssentials(ProxyServer server, Logger logger, @DataDirectory Path path) {
        PLUGIN = this;
        this.server = server;
        this.logger = logger;
        this.path = path;
        this.setup = new Setup();
        if(server.getPluginManager().isLoaded("Protocolize")) {
            Protocolize.listenerProvider().registerListener(new ServerSafePacketListener());
            Protocolize.listenerProvider().registerListener(new SecureProfilePacketListener());
        }else System.out.println("Error loading Protocolize");
    }


    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new JoinEvent());
        server.getEventManager().register(this, new SaveConfigEvents());
        server.getEventManager().register(this, new ServerPingEvent());
        server.getEventManager().register(this, new PluginMessageListener());
        setup.registerScheduler();
    }
}