package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.friends.OnlinePlayer;
import de.rusticprism.vessentials.friends.Players;

public class JoinEvent {

    @Subscribe
    public void onJoin(PostLoginEvent event) {
        Player player = event.getPlayer();
        Players.addPlayer(new OnlinePlayer(player));
    }
    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        Players.removePlayer(Players.getPlayer(player));
    }
}
