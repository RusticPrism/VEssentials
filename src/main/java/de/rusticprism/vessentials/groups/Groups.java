package de.rusticprism.vessentials.groups;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Groups {
    public final HashMap<String, Group> groups;
    public Groups() {
        this.groups = new HashMap<>();
    }

    public Group createGroup(String name) {
            Group group = new Group(name,"","");
            groups.put(name,group);
            return group;
    }
    public void removeGroup(String name) {
        groups.remove(name);
    }
    public Group getGroup(String name) {
        return groups.get(name);
    }

    public List<Group> getGroups() {
        return groups.values().stream().toList();
    }

    public Group getPlayerGroup(String player) {
        Group group = null;
        for(Map.Entry<String,Group> entry : groups.entrySet()) {
           if(Arrays.stream(entry.getValue().getPlayers()).toList().contains(player)) {
               group = entry.getValue();
           }
        }
        return group;
    }
}
