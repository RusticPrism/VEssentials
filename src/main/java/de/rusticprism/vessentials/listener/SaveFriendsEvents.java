package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.proxy.ProxyReloadEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import de.rusticprism.vessentials.friends.OnlinePlayer;
import de.rusticprism.vessentials.friends.Players;

public class SaveFriendsEvents {
    public void onShutdown(ProxyShutdownEvent e) {
        for(OnlinePlayer player : Players.getPlayers()) {
            player.getConfig().saveConfig();
        }

    }
    public void onReload(ProxyReloadEvent e) {
        for(OnlinePlayer player : Players.getPlayers()) {
            player.getConfig().saveConfig();
        }
    }
}
