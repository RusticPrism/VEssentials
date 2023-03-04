package de.rusticprism.vessentials.commands.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TabCompleter {
    private final HashMap<Integer,String[]> completions;
    private int from = -1;
    public static TabCompleter EMPTY = new TabCompleter(-1,"EMPTY");
    private String[] fromcompletion;
    public TabCompleter(int pos,String... completion) {
        completions = new HashMap<>();
        completions.put(pos, completion);
    }
    public TabCompleter(int pos, List<String> completion) {
        completions = new HashMap<>();
        completions.put(pos,completion.toArray(new String[0]));
    }
    public TabCompleter at(int pos, String... completion) {
        completions.put(pos,completion);
        return this;
    }
    public TabCompleter at(int pos, List<String> completion) {
        completions.put(pos,completion.toArray(new String[0]));
        return this;
    }
    public TabCompleter from(int pos, String... completion) {
        from = pos;
        fromcompletion = completion;
        return this;
    }
    public TabCompleter from(int pos, List<String> completion) {
        from = pos;
        fromcompletion = completion.toArray(new String[1]);
        return this;
    }

    public int getFrom() {
        return from;
    }

    public HashMap<Integer, String[]> getCompletions() {
        return completions;
    }

    public String[] getCompletion() {
        return fromcompletion;
    }

    public static List<String> sort(List<String> completions,String completion) {
        List<String> end = new ArrayList<>();
        StringUtil.copyPartialMatches(completion,completions,end);
        Collections.sort(end);
        return end;
    }
}