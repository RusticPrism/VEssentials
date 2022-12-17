package de.rusticprism.vessentials.util;

import de.rusticprism.vessentials.VEssentials;
import net.kyori.adventure.text.Component;

public class Messages {
    public static Component prefix;
    public static Component nocons;
    public static Component noperms;

    public Messages() {
        prefix = PlaceHolders.replaceAsComponent("%plugin_prefix%");
        nocons = PlaceHolders.replaceAsComponent("%plugin_nocons%");
        noperms = PlaceHolders.replaceAsComponent("%plugin_noperms%");
    }
}
