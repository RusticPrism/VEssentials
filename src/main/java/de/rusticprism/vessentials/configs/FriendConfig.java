package de.rusticprism.vessentials.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Friend;
import de.rusticprism.vessentials.friends.OnlinePlayer;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FriendConfig extends Configuration {
        private final OnlinePlayer friends;
    public FriendConfig(Player player, OnlinePlayer friends) {
        super(player.getUsername() + "friends");
        this.friends = friends;
    }

    public void loadConfig() {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(file.toPath());

            // convert JSON file to map
            List<UUID> uuids = gson.fromJson(reader, List.class);
            if (uuids == null) {
                return;
            }

            // print map entries
            for (UUID uuid : uuids) {
              if(VEssentials.plugin.server.getPlayer(uuid).isPresent()) {
                  this.friends.friends.add(new Friend(VEssentials.plugin.server.getPlayer(uuid).get()));
              }
            }

            // close reader
            reader.close();
        } catch (IOException e) {
            VEssentials.plugin.logger.error("Couldn't read Config File!");
        }
    }

    public void saveConfig() {
        List<UUID> uuids = new ArrayList<>();
        friends.getFriends().forEach(friend -> {
            uuids.add(friend.getUUID());
        });
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
           try {
               FileWriter writer = new FileWriter(file);
               writer.write(builder.create().toJson(uuids));
               writer.flush();
               writer.close();
           } catch (IOException e) {
               VEssentials.plugin.logger.error("Couldn't save Config!");
           }
    }
}
