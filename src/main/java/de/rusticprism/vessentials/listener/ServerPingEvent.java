package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.ChatColor;
import net.kyori.adventure.text.Component;

public class ServerPingEvent {
    @Subscribe
    public void onPing(ProxyPingEvent event) {
        event.setPing(event.getPing().asBuilder().description(Component.text(ChatColor.translateAlternateColorCode("&",VEssentials.PLUGIN.messages.motd))).version(new ServerPing.Version(event.getPing().getVersion().getProtocol(),"§cMaintenance")).build());
    }
}
