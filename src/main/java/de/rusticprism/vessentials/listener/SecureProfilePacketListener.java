package de.rusticprism.vessentials.listener;

import com.velocitypowered.proxy.protocol.packet.ServerData;
import de.rusticprism.vessentials.VEssentials;
import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;

import java.lang.reflect.Field;

public class SecureProfilePacketListener extends AbstractPacketListener<ServerData> {
    public SecureProfilePacketListener() {
        super(ServerData.class,Direction.UPSTREAM,0);
    }

    @Override
    public void packetReceive(PacketReceiveEvent<ServerData> packetReceiveEvent) {

    }

    @Override
    public void packetSend(PacketSendEvent<ServerData> packetSendEvent) {
        ServerData data = packetSendEvent.packet();
        try {
            Field secureprofile = data.getClass().getDeclaredField("secureChatEnforced");
            secureprofile.setAccessible(true);
            secureprofile.set(data,false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            VEssentials.PLUGIN.logger.error("Error changing SecureChatEnforcement (" +e.getMessage()+")");
        }
    }
}
