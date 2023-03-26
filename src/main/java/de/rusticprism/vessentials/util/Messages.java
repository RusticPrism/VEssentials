package de.rusticprism.vessentials.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.List;

public class Messages {
    private static final List<String> vocals = List.of("a", "e", "i", "o", "u");
    public static Component prefix = MiniMessage.miniMessage().deserialize("<gradient:blue:aqua>VEssentials <gray>>>");
    public static Component nocons = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You have to be a <dark_red>Player <red>to perform that Command!");
    public static Component noperms = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You don't have the required <dark_red>permission <red>to perform that Command!");
    ;
    public static Component toManyArgs = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You provided to many arguments!");
    public static Component toFewArgs = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You provided to few arguments!");
    public static Component playerNotOnline = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>The provided player isn´t online!");
    public static Component args = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You provided to many/few arguments!");

    public static Component wrongArg(int position, String wrong) {
        if (vocals.contains(wrong.substring(0, 1))) {
            return PlaceHolders.replaceAsComponent("%plugin_prefix% <red>Argument <dark_red>" + position + " <red>has to be an <dark_red>" + wrong + "<red>!");
        }
        return PlaceHolders.replaceAsComponent("%plugin_prefix% <red>Argument <dark_red>" + position + " <red>has to be a <dark_red>" + wrong + "<red>!");
    }
}
