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
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.Set;

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
    public final Component prefix;
    public final Component nocons;
    public final Component noperms;
    public final Component arguments;
    public final Path path;
    public final CommandManager cmdman;
    public final Setup setup;

    @Inject
    public VEssentials(ProxyServer server, Logger logger, @DataDirectory Path path) {
        plugin = this;
        this.server = server;
        this.logger = logger;
        this.prefix = Component.text("§cVEssentials §7>>");
        this.noperms = prefix.append(Component.text(" §cYou don´t have the Permission to perform that Command!"));
        this.nocons = prefix.append(Component.text("§cYou have to be a Player to perform that Command!"));
        this.arguments = prefix.append(Component.text("§cYou gave to many arguments!"));
        this.path = path;
        cmdman = new CommandManager();
        setup = new Setup();
    }



    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getEventManager().register(this,new JoinEvent());
    }
}
