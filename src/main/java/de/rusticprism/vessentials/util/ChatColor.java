package de.rusticprism.vessentials.util;

import net.kyori.adventure.text.Component;

public class ChatColor {
    public static String translateAlternateColorCode(String code, String msg) {
        return msg.replaceAll(code,"§");
    }
}
