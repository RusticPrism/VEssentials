package de.rusticprism.vessentials.util;

import com.velocitypowered.api.command.CommandSource;

public class Permission {
    public static boolean hasPermission(CommandSource player, String permission) {
        return player.hasPermission(permission) || player.hasPermission("essentials.admin");
    }
}
