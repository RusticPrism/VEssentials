package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.friends.PluginMessage;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class PluginMessageListener{

    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        if(event.getIdentifier().getId().equalsIgnoreCase("BungeeCord")) {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
            try {
                String subchannel = in.readUTF();
                if(subchannel.equalsIgnoreCase("getStatus")) {
                    short len = in.readShort();
                    byte[] data = new byte[len];
                    in.readFully(data);
                    String s = new String(data);
                    if(VEssentials.plugin.server.getPlayer(s).isPresent()) {
                        StringBuilder builder = new StringBuilder();
                        for(String str : Players.getPlayer(VEssentials.plugin.server.getPlayer(s).get()).getStatus()) {
                            builder.append(str).append(" ");
                        }
                        PluginMessage.send(VEssentials.plugin.server.getPlayer(s).get(),"getStatus",builder.toString());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
