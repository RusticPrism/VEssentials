package de.rusticprism.vessentials.configs;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.BannedPlayer;

import java.util.*;

public class BanConfig extends Configuration{
    private final List<BannedPlayer> bannedPlayers;
    public BanConfig() {
        super("bannedplayers");
        bannedPlayers = new ArrayList<>();
        for (String uuid : config.keySet()) {
            bannedPlayers.add(new BannedPlayer(uuid,config.get(uuid + ".reason")));
        }
    }
    public List<BannedPlayer> getBannedPlayers() {
        return bannedPlayers;
    }
    public boolean isBanned(Player player) {
        return config.containsKey(player.getUniqueId().toString());
    }
    public boolean isBanned(String uuid) {
        return config.containsKey(uuid);
    }
    public BannedPlayer getBannedPlayer(String uuid) {
        BannedPlayer player = null;
        for (BannedPlayer player1 : bannedPlayers) {
            if(player1.getUuid().equalsIgnoreCase(uuid)) {
                player = player1;
                break;
            }
        }
        return player;
    }
    public void banPlayer(String uuid,String reason) {
        bannedPlayers.add(new BannedPlayer(uuid, reason));
        config.put(uuid + ".reason",reason);
        config.put(uuid,"true");
    }
    public void unbanPlayer(String uuid) {
        for (BannedPlayer player1 : bannedPlayers) {
            if(player1.getUuid().equalsIgnoreCase(uuid)) {
                bannedPlayers.remove(player1);
                config.remove(uuid);
                config.remove(uuid + ".reason");
            }
        }
    }
}
