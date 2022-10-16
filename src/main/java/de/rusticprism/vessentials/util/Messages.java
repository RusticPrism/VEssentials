package de.rusticprism.vessentials.util;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import net.kyori.adventure.text.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

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
        for (String placeholder : placeholders.keySet()) {
            for (String split : message.split(" ")) {
                if (split.equalsIgnoreCase(placeholder)) {
                    msg = message.replaceAll(split, placeholders.get(placeholder));
                }
            }
        }
        if (msg == null) {
            msg = message;
        }
        return ChatColor.translateAlternateColorCode("&", msg);
    }

    public static void registerPlaceHolder(String placeholder, String replacement) {
        placeholders.put(placeholder, replacement);
    }

    private void registerAllPlaceHolders() {
        registerPlaceHolder("%Prefix%", VEssentials.PLUGIN.messages.prefix);
        registerPlaceHolder("%NoPerms%", VEssentials.PLUGIN.messages.noperms);
        registerPlaceHolder("%NoCons%", VEssentials.PLUGIN.messages.nocons);
    }
    public static String replacePlayerPlaceHolder(Player player,String message) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        String date = format.format(new Date());
        return replace(message)
                .replaceAll("%Player%",player.getUsername())
                .replaceAll("%Ping%",String.valueOf(player.getPing()))
                .replaceAll("%Server%",player.getCurrentServer().isPresent() ? player.getCurrentServer().get().getServerInfo().getName() : "No Server")
                .replaceAll("%Time%",date);
    }
}
