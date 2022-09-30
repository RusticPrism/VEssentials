package de.rusticprism.vessentials;

import com.google.inject.Inject;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.permission.Tristate;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import de.rusticprism.vessentials.configs.MessageConfig;
import de.rusticprism.vessentials.listener.JoinEvent;
import de.rusticprism.vessentials.listener.SaveConfigEvents;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.commands.CommandManager;
import de.rusticprism.vessentials.util.commands.Setup;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
    public static VEssentials PLUGIN;
    public final MessageConfig messages;
    public final Component arguments;
    public final Path path;
    public final CommandManager cmdman;
    public final Setup setup;

    @Inject
    public VEssentials(ProxyServer server, Logger logger, @DataDirectory Path path) {
        PLUGIN = this;
        this.server = server;
        this.logger = logger;
        this.path = path;
        cmdman = new CommandManager();
        messages = new MessageConfig();
        setup = new Setup();
        this.arguments = Messages.prefix.append(Component.text("§cYou gave to many arguments!"));
    }



    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getEventManager().register(this,new JoinEvent());
        server.getEventManager().register(this,new SaveConfigEvents());
    }
}
