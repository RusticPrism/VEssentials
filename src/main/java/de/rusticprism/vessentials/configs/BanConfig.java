package de.rusticprism.vessentials.configs;

import com.velocitypowered.api.proxy.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BanConfig extends Config{
    private final List<String> bannedPlayers;
    public BanConfig() {
        super("bannedplayers", true);
        bannedPlayers = new ArrayList<>();
        for (String uuid : config.getValues(true).keySet()) {
           if(!uuid.contains(".")) {
               bannedPlayers.add(uuid);
           }
        }
    }
    public List<String> getBannedPlayers() {
        return bannedPlayers;
    }
    public boolean isBanned(Player player) {
        return isBannedUuid(player.getUniqueId().toString());
    }
    public boolean isBannedUuid(String uuid) {
        AtomicBoolean result = new AtomicBoolean(false);
        config.getRoot().getKeys(false).forEach(s -> {
            if (s.equalsIgnoreCase(uuid)) {
                result.set(true);
            }
        });
        return result.get();
    }
    public boolean isBannedName(String name) {
        AtomicBoolean result = new AtomicBoolean(false);
        config.getRoot().getKeys(false).forEach(s -> {
            if (config.getString(s + ".name") != null && config.getString(s + ".name").equalsIgnoreCase(name)) {
                result.set(true);
            }
        });
        return result.get();
    }
    public void banPlayer(Player player,String reason,String time,String bannedby) {
        bannedPlayers.add(player.getUniqueId().toString());
        config.set(player.getUniqueId().toString() + ".reason", reason);
        config.set(player.getUniqueId().toString() + ".name", player.getUsername());
        config.set(player.getUniqueId().toString() + ".time", time);
        config.set(player.getUniqueId().toString() + ".bannedby", bannedby);
        saveConfig();
    }
    public void unbanPlayer(String uuid) {
        for (String player1 : bannedPlayers) {
            if(player1.equalsIgnoreCase(uuid)) {
                bannedPlayers.remove(player1);
                config.set(uuid, null);
                config.set(uuid + ".reason", null);
                config.set(uuid + ".name", null);
                config.set(uuid + ".time", null);
                config.set(uuid + ".bannedby", null);
                break;
            }
        }
        saveConfig();
    }
    public void unbanPlayerName(String name) {
        config.getRoot().getKeys(false).forEach(s -> {
            if (config.getString(s + ".name").equalsIgnoreCase(name)) {
                unbanPlayer(s);
            }
        });
    }
    public String getReason(Player player) {
        return config.getString(player.getUniqueId() + ".reason");
    }
    public String getTime(Player player) {
        return config.getString(player.getUniqueId() + ".time");
    }
    public String getBannedBy(Player player) {
        return config.getString(player.getUniqueId() + ".bannedby");
    }

    @Override
    public void createDefault() {

    }
}
