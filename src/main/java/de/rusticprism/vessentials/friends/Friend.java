package de.rusticprism.vessentials.friends;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;

import java.util.UUID;

public class Friend {
    private final Player player;
    public Friend(Player player) {
        this.player = player;
    }

    public String getName() {
        return player.getUsername();
    }
    public UUID getUUID() {
        return player.getUniqueId();
    }
    public ServerConnection getServer() {
        if(player.getCurrentServer().isPresent()) {
            return player.getCurrentServer().get();
        }else return null;
    }
}
