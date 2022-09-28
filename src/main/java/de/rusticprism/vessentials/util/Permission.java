package de.rusticprism.vessentials.util;

import com.velocitypowered.api.proxy.Player;

public class Permission {
    public static boolean hasPermission(Player player, String permission) {
        return player.hasPermission(permission) || player.hasPermission("essentials.admin");
    }
}
