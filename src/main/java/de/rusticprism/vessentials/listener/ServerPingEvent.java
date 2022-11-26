package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import com.velocitypowered.api.util.ModInfo;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.MaintenanceCommand;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.ChatColor;
import de.rusticprism.vessentials.util.CompletionSupplier;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public class ServerPingEvent {
    @Subscribe
    public void onPing(ProxyPingEvent event) {
        event.setPing(event.getPing().asBuilder().description(Component.text(ChatColor.translateAlternateColorCode("&", VEssentials.PLUGIN.messages.motd))).build());
        DataConfig config = (DataConfig) VEssentials.PLUGIN.setup.configs.getConfigByName("data");
       if(config.maintenance) {
            ServerPing ping = event.getPing();
            ServerPing.Builder builder = ping.asBuilder();
            builder.description(Component.text(ChatColor.translateAlternateColorCode("&", VEssentials.PLUGIN.messages.maintenancemotd)));
           builder.samplePlayers(new ServerPing.SamplePlayer("Server is currently under Maintenance", new UUID(0,0)));
           builder.version(new ServerPing.Version(1,"§4Maintenance"));
           event.setPing(builder.build());
       }
    }
}
