package de.rusticprism.vessentials;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import de.rusticprism.vessentials.listener.JoinEvent;
import de.rusticprism.vessentials.listener.SaveConfigEvents;
import de.rusticprism.vessentials.listener.ServerPingEvent;
import de.rusticprism.vessentials.util.FileSystemUtils;
import de.rusticprism.vessentials.util.PlaceHolders;
import de.rusticprism.vessentials.util.commands.CommandManager;
import de.rusticprism.vessentials.util.commands.Setup;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.translation.GlobalTranslator;
import net.kyori.adventure.translation.TranslationRegistry;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

@Plugin(
        id = "vessentials",
        name = "VEssentials",
        version = "1.0.0-SNAPSHOT",
        description = "A Essentials Velocity Plugin",
        authors = {"RusticPrism"},
        dependencies = {
                @Dependency(id = "luckperms", optional = true)
        }
)
public class VEssentials {

    public final Logger logger;
    public final ProxyServer server;
    public static VEssentials PLUGIN;
    public final Path path;
    public final CommandManager cmdman;
    public Setup setup;

    @Inject
    public VEssentials(ProxyServer server, Logger logger, @DataDirectory Path path) {
        PLUGIN = this;
        this.server = server;
        this.logger = logger;
        this.path = path;
        this.cmdman = new CommandManager();
        this.setup = new Setup();
    }


    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new JoinEvent());
        server.getEventManager().register(this, new SaveConfigEvents());
        server.getEventManager().register(this, new ServerPingEvent());
        setup.registerScheduler();
    }
}