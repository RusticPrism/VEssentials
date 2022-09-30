package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.friends.OnlinePlayer;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.ChatColor;
import net.kyori.adventure.text.Component;

public class JoinEvent {

    @Subscribe
    public void onJoin(LoginEvent event) {
        Player player = event.getPlayer();
        Players.addPlayer(new OnlinePlayer(player));
        BanConfig banConfig = (BanConfig) VEssentials.PLUGIN.setup.configs.getConfigByName("bannedplayers");
        if(banConfig.isBanned(player)) {
            event.setResult(ResultedEvent.ComponentResult.denied(Component.text(VEssentials.PLUGIN.messages.banmessage
                    .replace("%Reason%", ChatColor.translateAlternateColorCode("&",banConfig.config.get(player.getUniqueId().toString() + ".reason")))
                    .replace("%Duration%",banConfig.config.get(player.getUniqueId().toString() + ".time"))
                    .replace("%Player%",banConfig.config.get(player.getUniqueId().toString() + ".bannedby")))));
        }
    }
    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        Players.removePlayer(Players.getPlayer(player));
    }
}
