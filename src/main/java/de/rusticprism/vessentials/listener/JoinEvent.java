package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.friends.OnlinePlayer;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.ChatColor;
import net.kyori.adventure.text.Component;

public class JoinEvent {

    @Subscribe
    public void onJoin(PostLoginEvent event) {
        Player player = event.getPlayer();
        Players.addPlayer(new OnlinePlayer(player));
        BanConfig banConfig = (BanConfig) VEssentials.plugin.setup.configs.getConfigByName("bannedplayers");
        if(banConfig.isBanned(player)) {
            player.disconnect(Component.text("§8-------------------------------\n"
                    + "\n §1§lYou got Banned by " + player.getUsername()
                    + "\n"
                    + "§8Reason: §1" + ChatColor.translateAlternateColorCode("&", banConfig.getBannedPlayer(String.valueOf(player.getUniqueId())).getReason())
                    +"\n§8Duration: §1Lifetime"
                    + "\n \n§8-------------------------------"));
        }
    }
    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        Players.removePlayer(Players.getPlayer(player));
    }
}
