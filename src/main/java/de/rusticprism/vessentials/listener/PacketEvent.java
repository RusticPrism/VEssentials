package de.rusticprism.vessentials.listener;

import com.velocitypowered.proxy.protocol.MinecraftPacket;
import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;

public class PacketEvent extends AbstractPacketListener<MinecraftPacket> {
    public PacketEvent() {
        super(MinecraftPacket.class, Direction.UPSTREAM, 10);
    }

    @Override
    public void packetReceive(PacketReceiveEvent packetReceiveEvent) {
        System.out.println(packetReceiveEvent.packet().getClass().getPackageName());
    }

    @Override
    public void packetSend(PacketSendEvent packetSendEvent) {
        System.out.println(packetSendEvent.packet().getClass().getPackageName());
    }
}
