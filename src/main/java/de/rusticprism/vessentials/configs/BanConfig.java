package de.rusticprism.vessentials.configs;

import com.velocitypowered.api.proxy.Player;

import java.util.*;

public class BanConfig extends Configuration{
    private final List<String> bannedPlayers;
    public BanConfig() {
        super("bannedplayers");
        bannedPlayers = new ArrayList<>();
        for (String uuid : config.keySet()) {
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
        return config.containsKey(uuid);
    }
    public boolean isBannedName(String name) {
      return config.containsValue(name);
    }
    public void banPlayer(Player player,String reason,String time,String bannedby) {
        bannedPlayers.add(player.getUniqueId().toString());
        config.put(player.getUniqueId().toString() + ".reason",reason);
        config.put(player.getUniqueId().toString(),"true");
        config.put(player.getUniqueId().toString()+".name",player.getUsername());
        config.put(player.getUniqueId().toString()+".time",time);
        config.put(player.getUniqueId().toString()+".bannedby",bannedby);
        saveConfig();
    }
    public void unbanPlayer(String uuid) {
        for (String player1 : bannedPlayers) {
            if(player1.equalsIgnoreCase(uuid)) {
                bannedPlayers.remove(player1);
                config.remove(uuid);
                config.remove(uuid + ".reason");
                config.remove(uuid + ".name");
                config.remove(uuid + ".time");
                config.remove(uuid + ".bannedby");
                saveConfig();
                break;
            }
        }
    }
    public void unbanPlayerName(String name) {
        for(Map.Entry<String,String> entry : config.entrySet()) {
            if(entry.getValue().equalsIgnoreCase(name)) {
                unbanPlayer(entry.getKey().split("\\.")[0]);
                break;
            }
        }
    }
    public String getReason(Player player) {
        return config.get(player.getUniqueId().toString() + ".reason");
    }
    public String getTime(Player player) {
        return config.get(player.getUniqueId().toString() + ".time");
    }
    public String getBannedBy(Player player) {
        return config.get(player.getUniqueId().toString() + ".bannedby");
    }
}
