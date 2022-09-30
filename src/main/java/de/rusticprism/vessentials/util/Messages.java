package de.rusticprism.vessentials.util;

import de.rusticprism.vessentials.VEssentials;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.HashMap;
import java.util.List;

public class Messages {
    private static HashMap<String, String> placeholders;
    public static Component prefix;
    public static Component nocons;
    public static Component noperms;
    public Messages() {
        placeholders = new HashMap<>();
        registerAllPlaceHolders();
        prefix = Component.text(replace(VEssentials.PLUGIN.messages.prefix));
        nocons = Component.text(replace(VEssentials.PLUGIN.messages.nocons));
        noperms = Component.text(replace(VEssentials.PLUGIN.messages.noperms));
    }
    public static String replace(String message) {
        String msg = null;
        for(String placeholder : placeholders.keySet()) {
            for (String split : message.split(" ")) {
                if(split.equalsIgnoreCase(placeholder)) {
                  msg =  message.replace(split,placeholders.get(placeholder));
                }
            }
        }
        if(msg == null) {
            return message;
        }
        return msg;
    }
    public static void registerPlaceHolder(String placeholder, String replacement) {
        placeholders.put(placeholder.toLowerCase(),replacement);
    }
    private void registerAllPlaceHolders() {
        registerPlaceHolder("%Prefix%", VEssentials.PLUGIN.messages.prefix);
        registerPlaceHolder("%NoPerms%",VEssentials.PLUGIN.messages.noperms);
        registerPlaceHolder("%NoCons%", VEssentials.PLUGIN.messages.nocons);
    }
}
