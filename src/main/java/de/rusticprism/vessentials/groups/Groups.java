package de.rusticprism.vessentials.groups;

import de.rusticprism.vessentials.VEssentials;

import java.util.HashMap;
import java.util.List;

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
}
