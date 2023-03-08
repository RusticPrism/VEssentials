package de.rusticprism.vessentials.listener;

import com.velocitypowered.proxy.protocol.MinecraftPacket;
import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;
import dev.simplix.protocolize.data.packets.SetSlot;

public class PacketEvent extends AbstractPacketListener<SetSlot> {
    public PacketEvent() {
        super(SetSlot.class, Direction.UPSTREAM, 10);
    }

    @Override
    public void packetReceive(PacketReceiveEvent packetReceiveEvent) {
        System.out.println(((SetSlot)packetReceiveEvent.packet()).itemStack());
        System.out.println(((SetSlot)packetReceiveEvent.packet()).slot());
        System.out.println(((SetSlot)packetReceiveEvent.packet()).windowId());
    }

    @Override
    public void packetSend(PacketSendEvent packetSendEvent) {
        System.out.println(((SetSlot)packetSendEvent.packet()).itemStack());
        System.out.println(((SetSlot)packetSendEvent.packet()).slot());
        System.out.println(((SetSlot)packetSendEvent.packet()).windowId());
    }
}
