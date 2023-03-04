package de.rusticprism.vessentials.listener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.api.proxy.server.ServerPing;
import com.velocitypowered.api.util.Favicon;
import com.velocitypowered.proxy.protocol.ProtocolUtils;
import com.velocitypowered.proxy.protocol.packet.StatusResponse;
import com.velocitypowered.proxy.protocol.util.FaviconSerializer;
import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;

public class ServerSafePacketListener extends AbstractPacketListener<StatusResponse> {
    public ServerSafePacketListener() {
        super(StatusResponse.class, Direction.UPSTREAM,0);
    }

    @Override
    public void packetReceive(PacketReceiveEvent<StatusResponse> packetReceiveEvent) {}

    @Override
    public void packetSend(PacketSendEvent<StatusResponse> packetSendEvent) {
        Gson gson = ProtocolUtils.getJsonChatSerializer(ProtocolVersion.getProtocolVersion(packetSendEvent.player().protocolVersion()))
                .serializer()
                .newBuilder()
                .registerTypeHierarchyAdapter(Favicon.class, FaviconSerializer.INSTANCE)
                .create();
        ServerPing ping = gson.fromJson(packetSendEvent.packet().getStatus(),ServerPing.class);
        JsonObject end = (JsonObject) gson.toJsonTree(ping);
        if(!end.has("preventsChatReports")) {
            end.addProperty("preventsChatReports", true);
        }
        packetSendEvent.packet(new StatusResponse(ProtocolUtils.getJsonChatSerializer(ProtocolVersion.MINECRAFT_1_19_3).serializer().toJson(end)));
    }
}
