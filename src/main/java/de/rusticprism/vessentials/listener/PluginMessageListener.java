package de.rusticprism.vessentials.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.rusticprism.vessentials.VEssentials;
import javassist.bytecode.annotation.MemberValueVisitor;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class PluginMessageListener{
    private final MinecraftChannelIdentifier MODERNCHANNEL;

    public PluginMessageListener() {
        MODERNCHANNEL = MinecraftChannelIdentifier.from("lobby:main");
        VEssentials.PLUGIN.server.getChannelRegistrar().register(MODERNCHANNEL);
    }
    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        if(event.getIdentifier().equals(MODERNCHANNEL)) {
            if (!(event.getSource() instanceof ServerConnection connection)) {
                event.setResult(PluginMessageEvent.ForwardResult.forward());
                return;
            }
            event.setResult(PluginMessageEvent.ForwardResult.handled());

            ByteArrayDataInput in = event.dataAsDataStream();
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            String subChannel = in.readUTF();
            if (subChannel.equalsIgnoreCase("GetServer")) {
                String name = in.readUTF();
                Optional<RegisteredServer> optional = VEssentials.PLUGIN.server.getServer(name);
                if (optional.isPresent()) {
                    RegisteredServer server = optional.get();
                    out.writeUTF("ServerInfo");
                    out.writeUTF(server.getServerInfo().getName());
                    try {
                        server.ping().get();
                        out.writeUTF("true");
                        out.writeUTF(String.valueOf(server.ping().get().asBuilder().getOnlinePlayers()));
                        out.writeUTF(String.valueOf(server.ping().get().asBuilder().getMaximumPlayers()));
                    } catch (InterruptedException | ExecutionException e) {
                        out.writeUTF("false");
                        out.writeUTF("0");
                        out.writeUTF("0");
                    }
                } else {
                    out.writeUTF("ServerInfo");
                    out.writeUTF(name);
                    out.writeUTF("false");
                    out.writeUTF("0");
                    out.writeUTF("0");
                }
                connection.getServer().sendPluginMessage(MODERNCHANNEL, out.toByteArray());
            }
            if (subChannel.equalsIgnoreCase("connect")) {
                String server = in.readUTF();
                if (VEssentials.PLUGIN.server.getServer(server).isEmpty()) {
                    event.setResult(PluginMessageEvent.ForwardResult.forward());
                    return;
                }
                connection.getPlayer().createConnectionRequest(VEssentials.PLUGIN.server.getServer(server).get()).fireAndForget();
                event.setResult(PluginMessageEvent.ForwardResult.handled());
            }
        }
        event.setResult(PluginMessageEvent.ForwardResult.forward());
    }
}
