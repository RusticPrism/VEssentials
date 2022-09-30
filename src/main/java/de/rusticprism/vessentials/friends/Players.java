package de.rusticprism.vessentials.friends;

import com.velocitypowered.api.proxy.Player;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private static final List<OnlinePlayer> players = new ArrayList<>();

    public static void addPlayer(OnlinePlayer friends) {
        friends.getConfig().loadConfig();
        players.add(friends);
    }
    public static void removePlayer(OnlinePlayer friends) {
        if(friends == null) {
            return;
        }
        friends.getConfig().saveConfig();
        players.remove(friends);
    }

    public static List<OnlinePlayer> getPlayers() {
        return players;
    }
    public static OnlinePlayer getPlayer(Player player) {
        OnlinePlayer onlinePlayer = null;
        for(OnlinePlayer friends : players) {
            if(friends.getPlayer().equals(player)) {
                onlinePlayer = friends;
                break;
            }
        }
        return onlinePlayer;
    }
}
