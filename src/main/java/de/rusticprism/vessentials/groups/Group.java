package de.rusticprism.vessentials.groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group {
    private final String name;
    private String prefix;
    private String suffix;
    private List<String> players;

    public Group(String name, String prefix, String suffix) {
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
        players = new ArrayList<>();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(String name) {
        players.add(name);
    }
    public void removePlayer(String name) {
        players.remove(name);
    }
    public String[] getPlayers() {
        return players.toArray(new String[0]);
    }
    public Group setPlayers(List<String> names) {
        players.addAll(names);
        return this;
    }
}
