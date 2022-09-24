package de.rusticprism.vessentials;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import de.rusticprism.vessentials.listener.JoinEvent;
import de.rusticprism.vessentials.util.commands.CommandManager;
import de.rusticprism.vessentials.util.commands.Setup;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "vessentials",
        name = "VEssentials",
        version = "1.0.0-SNAPSHOT",
        description = "A Essentials Velocity Plugin",
        authors = {"RusticPrism"}
)
public class VEssentials {

    public final Logger logger;
    public final ProxyServer server;
    public static VEssentials plugin;
    public final String prefix;
    public final String nocons;
    public final String noperms;
    public final Path path;
    public final CommandManager cmdman;

    @Inject
    public VEssentials(ProxyServer server, Logger logger, @DataDirectory Path path) {
        plugin = this;
        this.server = server;
        this.logger = logger;
        this.prefix = "§cVEssentials §7>>";
        this.noperms = prefix + " §cYou don´t have the Permission to perform that Command!";
        this.nocons = prefix + " §cYou have to be a Player to perform that Command!";
        this.path = path;
        cmdman = new CommandManager();
        new Setup();
    }



    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getEventManager().register(this,new JoinEvent());
    }
}
