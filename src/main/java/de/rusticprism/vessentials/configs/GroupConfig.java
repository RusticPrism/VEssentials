package de.rusticprism.vessentials.configs;

import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.groups.Group;
import de.rusticprism.vessentials.groups.Groups;

import java.util.*;

public class GroupConfig extends Configuration{
    public GroupConfig(Groups groups) {
        super("groupconfig");
        List<String> groupnames = new ArrayList<>();
        config.forEach((s, s2) -> {
            if(!groupnames.contains(s.split("\\.")[0])) {
                groupnames.add(s.split("\\.")[0]);
            }
        });
        for (String groupname : groupnames) {
            groups.groups.put(groupname,new Group(groupname,config.get(groupname + ".prefix"),config.get(groupname + ".suffix"))
                    .setPlayers(Arrays.stream(config.get(groupname + ".player").split(" ")).toList()));
        }
    }
    public void set(Group group) {
        StringBuilder players = new StringBuilder();
        for (String player : group.getPlayers()) {
            players.append(player).append(" ");
        }
        System.out.println("Test");
        config.put(group.getName() + ".prefix",group.getPrefix());
        config.put(group.getName() + ".suffix", group.getSuffix());
        config.put(group.getName() + ".player", players.substring(0,players.length() -1));
    }

    @Override
    public void saveConfig() {
        for (Group group : VEssentials.PLUGIN.setup.groups.getGroups()) {
            set(group);
        }
        super.saveConfig();
    }
}
