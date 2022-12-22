package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.PlaceHolders;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.UUID;

public class ServerPingEvent {
    @Subscribe
    public void onPing(ProxyPingEvent event) {
        event.setPing(event.getPing().asBuilder().description(PlaceHolders.translate("server-motd-line-1").appendNewline().append(PlaceHolders.translate("server-motd-line-2"))).build());
        DataConfig config = VEssentials.PLUGIN.setup.configs.getConfig(DataConfig.class);
       if(config.isMaintenance()) {
            ServerPing ping = event.getPing();
            ServerPing.Builder builder = ping.asBuilder();
            builder.description(PlaceHolders.translate("server-maintenance-motd-line-1").appendNewline().append(PlaceHolders.translate("server-maintenance-motd-line-2")));
           builder.samplePlayers(new ServerPing.SamplePlayer(LegacyComponentSerializer.legacySection().serialize(PlaceHolders.translate("server-maintenance-hover")), UUID.randomUUID()));
           builder.version(new ServerPing.Version(1, LegacyComponentSerializer.legacySection().serialize(PlaceHolders.translate("server-maintenance-version"))));
           event.setPing(builder.build());
       }
    }
}
