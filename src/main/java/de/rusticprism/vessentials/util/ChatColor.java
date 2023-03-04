package de.rusticprism.vessentials.util;

public class ChatColor {
    public static String translateAlternateColorCode(String code, String msg) {
        return msg.replaceAll(code,"§");
    }
}
