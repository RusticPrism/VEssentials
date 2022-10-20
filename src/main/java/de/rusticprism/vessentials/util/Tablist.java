package de.rusticprism.vessentials.util;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.player.TabListEntry;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.groups.Group;
import de.rusticprism.vessentials.groups.Groups;
import net.kyori.adventure.text.Component;

import java.util.Optional;
import java.util.UUID;

public class Tablist {

    public static void updateTablist() {
        for (Player player : VEssentials.PLUGIN.server.getAllPlayers()) {
            for (Player player1 : VEssentials.PLUGIN.server.getAllPlayers()) {
                if (!player.getTabList().containsEntry(player1.getUniqueId())) {
                    Group group = VEssentials.PLUGIN.setup.groups.getPlayerGroup(player.getUsername());
                    player.getTabList().addEntry(
                            TabListEntry.builder()
                                    .displayName(Component.text(ChatColor.translateAlternateColorCode("&",group.getPrefix()) +player1.getUsername() + ChatColor.translateAlternateColorCode("&",group.getSuffix())))
                                    .profile(player1.getGameProfile())
                                    .gameMode(0) // Impossible to get player game mode from proxy, always assume survival
                                    .tabList(player.getTabList())
                                    .build()
                    );
                }else {
                    player.getTabList().removeEntry(player.getUniqueId());
                    updateTablist();
                    return;
                }
            }

            for (TabListEntry entry : player.getTabList().getEntries()) {
                UUID uuid = entry.getProfile().getId();
                Optional<Player> playerOptional = VEssentials.PLUGIN.server.getPlayer(uuid);
                if (playerOptional.isPresent()) {
                    // Update ping
                    entry.setLatency((int) player.getPing() + 10);
                } else {
                    player.getTabList().removeEntry(uuid);
                }
            }
        }
    }
}
