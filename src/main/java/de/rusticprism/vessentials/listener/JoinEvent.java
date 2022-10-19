package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.event.player.ServerPostConnectEvent;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.friends.OnlinePlayer;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.*;
import net.kyori.adventure.text.Component;

public class JoinEvent {

    @Subscribe
    public void onJoin(LoginEvent event) {
        Player player = event.getPlayer();
        Tablist.updateTablist(player);
        Players.addPlayer(new OnlinePlayer(player));
        BanConfig banConfig = (BanConfig) VEssentials.PLUGIN.setup.configs.getConfigByName("bannedplayers");
        DataConfig dataConfig = (DataConfig) VEssentials.PLUGIN.setup.configs.getConfigByName("data");
        if(banConfig.isBanned(player)) {
            event.setResult(ResultedEvent.ComponentResult.denied(Component.text(VEssentials.PLUGIN.messages.banmessage
                    .replace("%Reason%", ChatColor.translateAlternateColorCode("&",banConfig.config.get(player.getUniqueId().toString() + ".reason")))
                    .replace("%Duration%",banConfig.config.get(player.getUniqueId().toString() + ".time"))
                    .replace("%Player%",banConfig.config.get(player.getUniqueId().toString() + ".bannedby")))));
        }
        if(dataConfig.maintenance && !Permission.hasPermission(player,"essentials.wartung.bypass")) {
            event.setResult(ResultedEvent.ComponentResult.denied(Component.text(VEssentials.PLUGIN.messages.maintenance
                    .replaceAll("%Reason%", Messages.replace(dataConfig.maintenancereason))
                    .replaceAll("%Player%", Messages.replace(dataConfig.maintenanceplayer)))));
        }else if(dataConfig.maintenance) {
            player.sendMessage(Messages.prefix.append(Component.text("§8The network ist currently in §1maintenance. \n§8You can still join because you are §1permitted §8to!")));
        }
        if(VEssentials.PLUGIN.messages.tablist) {
            player.sendPlayerListHeaderAndFooter(Component.text(Messages.replacePlayerPlaceHolder(player,VEssentials.PLUGIN.messages.header)),
                    Component.text(Messages.replacePlayerPlaceHolder(player,VEssentials.PLUGIN.messages.footer)));
        }
    }
    @Subscribe
    public void onResourePack(PlayerResourcePackStatusEvent event) {
        if(event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {
            event.getPlayer().disconnect(Component.text("§cBitte aktzepiere das Texture Pack!"));
        }else if(event.getStatus() == (PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD)) {
            event.getPlayer().disconnect(Component.text("§4§lDer download des Texture Packs ist fehlgeschlagen!"));
        }
    }
    @Subscribe
    public void onServerChange(ServerPostConnectEvent event) {
        if(event.getPreviousServer() == null) {
            event.getPlayer().sendResourcePackOffer(new KreiscraftResourcePack());
        }
    }
    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        Players.removePlayer(Players.getPlayer(player));
    }
}
