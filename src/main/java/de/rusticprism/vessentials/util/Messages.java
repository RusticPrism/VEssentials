package de.rusticprism.vessentials.util;

import de.rusticprism.vessentials.VEssentials;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Messages {
    public static Component prefix;
    public static Component nocons;
    public static Component noperms;
    public static Component toManyArgs;
    public static Component toFewArgs;
    public static Component playerNotOnline;

    public Messages() {
        prefix = MiniMessage.miniMessage().deserialize( "<gradient:dark_blue:aqua>VEssentials <gray>>>");
        nocons = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You have to be a <dark_red>Player <red>to perform that Command!");
        noperms = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You don´t have the <dark_red>permission <red>to perform that Command!");
        toManyArgs = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You provided to many arguments!");
        toFewArgs = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You provided to few arguments!");
        playerNotOnline = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>The provided player isn´t online!");
    }
}
