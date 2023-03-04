package de.rusticprism.vessentials.configs;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.configs.util.FileConfiguration;
import de.rusticprism.vessentials.configs.util.YamlConfiguration;

import java.util.*;

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

        return config.getValues(true).containsValue(uuid);
    }
    public boolean isBannedName(String name) {
      return config.getValues(true).containsValue(name);
    }
    public void banPlayer(Player player,String reason,String time,String bannedby) {
        bannedPlayers.add(player.getUniqueId().toString());
        config.set(player.getUniqueId().toString() + ".reason",reason);
        config.set(player.getUniqueId().toString(),"true");
        config.set(player.getUniqueId().toString()+".name",player.getUsername());
        config.set(player.getUniqueId().toString()+".time",time);
        config.set(player.getUniqueId().toString()+".bannedby",bannedby);
        config.saveToFile(getFile(),"Error occurred saving " + getName() + "!");
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
        config.saveToFile(getFile(),"Error occurred saving " + getName() + "!");
    }
    public void unbanPlayerName(String name) {
        for(Map.Entry<String,Object> entry : config.getValues(true).entrySet()) {
            if(((String) entry.getValue()).equalsIgnoreCase(name)) {
                unbanPlayer(entry.getKey().split("\\.")[0]);
                break;
            }
        }
    }
    public String getReason(Player player) {
        return String.valueOf(config.get(player.getUniqueId().toString() + ".reason"));
    }
    public String getTime(Player player) {
        return String.valueOf(config.get(player.getUniqueId().toString() + ".time"));
    }
    public String getBannedBy(Player player) {
        return String.valueOf(config.get(player.getUniqueId().toString() + ".bannedby"));
    }
}
