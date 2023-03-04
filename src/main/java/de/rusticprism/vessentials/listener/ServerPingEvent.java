package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import com.velocitypowered.api.util.Favicon;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.PlaceHolders;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ServerPingEvent {
    public static ServerPingEvent INSTANCE;

    public ServerPingEvent() {
        INSTANCE = this;
    }

    @Subscribe
    public void onPing(ProxyPingEvent event) throws IOException {
        ServerPing.Builder builder = event.getPing().asBuilder();
        File file = new File(VEssentials.PLUGIN.path.toAbsolutePath().getParent().getParent().toAbsolutePath().toString(), "server-icon.png");
        if (file.exists()) {
            builder.favicon(Favicon.create(file.toPath()));
        }
        builder.description(PlaceHolders.translate("server-motd-line-1").appendNewline().append(PlaceHolders.translate("server-motd-line-2")));
        DataConfig config = Configurations.getConfig(DataConfig.class);
        if (config.isMaintenance()) {
            builder.description(PlaceHolders.translate("server-maintenance-motd-line-1").appendNewline().append(PlaceHolders.translate("server-maintenance-motd-line-2")));
            builder.samplePlayers(new ServerPing.SamplePlayer(LegacyComponentSerializer.legacySection().serialize(PlaceHolders.translate("server-maintenance-hover")), UUID.randomUUID()));
            builder.version(new ServerPing.Version(1, LegacyComponentSerializer.legacySection().serialize(PlaceHolders.translate("server-maintenance-version"))));
        }
        event.setPing(builder.build());
    }
}
