package de.rusticprism.vessentials.friends;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.FriendConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class OnlinePlayer {
    public final List<Friend> friends;
    private FriendConfig config;
    private final Player player;
    private final List<String> status;
    private final List<Player> requests;

    public OnlinePlayer(Player player, Friend... friends) {
        this.friends = List.of(friends);
        this.player = player;
        this.status = new ArrayList<>();
        this.requests = new ArrayList<>();
    }
    public OnlinePlayer(Player player) {
        this.friends = new ArrayList<>();
        this.player = player;
        this.config = new FriendConfig(player, this);
        this.requests = new ArrayList<>();
        this.status = new ArrayList<>();
    }
    public List<Friend> getFriends() {
        return friends;
    }

    public FriendConfig getConfig() {
        return config;
    }

    public void sendRequest(Player from) {
        requests.add(from);
        player.sendMessage(Component.text("§1" + from.getUsername() + " §8sent you a friend request")
                .append(Component.text("\n§1Click here to accept!").clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND,"/friends accept " + from.getUsername()))));
    }
    public void addPlayer(Player player) {
        if(requests.contains(player)) {
            requests.remove(player);
            Players.getPlayer(player).addPlayer(getPlayer());
            friends.add(new Friend(player));
        }
    }
    public void removeFriend(String name) {
            if(VEssentials.PLUGIN.server.getPlayer(name).isPresent()) {
                friends.remove(new Friend(VEssentials.PLUGIN.server.getPlayer(name).get()));
            }else System.out.println("Test");
    }
    public void removeFriend(UUID uuid) {
            if(VEssentials.PLUGIN.server.getPlayer(uuid).isPresent()) {
                friends.remove(new Friend(VEssentials.PLUGIN.server.getPlayer(uuid).get()));
            }
    }
    public boolean contains(Friend friend) {
       return friends.contains(friend);
    }
    public Friend getFriend(UUID uuid) {
        Friend friend = null;
        for(Friend friend1 : friends) {
            if(friend1.getUUID().equals(uuid)) {
                friend = friend1;
                break;
            }
        }
        return friend;
    }
    public void setStatus(String... status) {
        this.status.addAll(Arrays.asList(status));
    }
    public List<String> getStatus() {
        return status;
    }
    public Friend getFriend(String name) {
        Friend friend = null;
        for(Friend friend1 : friends) {
            if(friend1.getName().equalsIgnoreCase(name)) {
                friend = friend1;
                break;
            }
        }
        return friend;
    }

    public Player getPlayer() {
        return player;
    }
    public List<Player> getRequests() {
        return requests;
    }
}
