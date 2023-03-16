package de.rusticprism.vessentials.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Messages {
    public static Component prefix = MiniMessage.miniMessage().deserialize( "<gradient:blue:aqua>VEssentials <gray>>>");;
    public static Component nocons = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You have to be a <dark_red>Player <red>to perform that Command!");
    public static Component noperms = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You don´t have the required <dark_red>permission <red>to perform that Command!");;
    public static Component toManyArgs = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You provided to many arguments!");;
    public static Component toFewArgs = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You provided to few arguments!");;
    public static Component playerNotOnline = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>The provided player isn´t online!");;
    public static Component args = PlaceHolders.replaceAsComponent("%plugin_prefix% <red>You provided to many/few arguments!");;
}
