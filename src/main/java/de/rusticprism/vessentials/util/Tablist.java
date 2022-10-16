package de.rusticprism.vessentials.util;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.player.TabListEntry;
import net.kyori.adventure.text.Component;

public class Tablist {

    public static void updateTablist(Player player) {
        player.getTabList().addEntry(TabListEntry.builder().profile(player.getGameProfile()).tabList(player.getTabList()).displayName(Component.text(player.getUsername())).latency(10).gameMode(0).build());
    }
}
