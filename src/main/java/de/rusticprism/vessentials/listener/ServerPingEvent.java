package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.ChatColor;
import de.rusticprism.vessentials.util.PlaceHolders;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public class ServerPingEvent {
    @Subscribe
    public void onPing(ProxyPingEvent event) {
        event.setPing(event.getPing().asBuilder().description(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.motd)).build());
        DataConfig config = VEssentials.PLUGIN.setup.configs.getConfig(DataConfig.class);
       if(config.maintenance) {
            ServerPing ping = event.getPing();
            ServerPing.Builder builder = ping.asBuilder();
            builder.description(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.maintenancemotd));
           builder.samplePlayers(new ServerPing.SamplePlayer("Server is currently under Maintenance", new UUID(0,0)));
           builder.version(new ServerPing.Version(1,"§4Maintenance"));
           event.setPing(builder.build());
       }
    }
}
