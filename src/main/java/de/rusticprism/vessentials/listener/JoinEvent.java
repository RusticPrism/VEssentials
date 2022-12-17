package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent;
import com.velocitypowered.api.event.player.ServerPostConnectEvent;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.friends.OnlinePlayer;
import de.rusticprism.vessentials.friends.Players;
import de.rusticprism.vessentials.util.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.concurrent.TimeUnit;

public class JoinEvent {

    @Subscribe
    public void onJoin(LoginEvent event) {
        Player player = event.getPlayer();
        Players.addPlayer(new OnlinePlayer(player));
        BanConfig banConfig = VEssentials.PLUGIN.setup.configs.getConfig(BanConfig.class);
        DataConfig dataConfig = VEssentials.PLUGIN.setup.configs.getConfig(DataConfig.class);
        if(banConfig.isBanned(player)) {
           event.setResult(ResultedEvent.ComponentResult.denied(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.banmessage,player)));
           return;
        }
        if(dataConfig.maintenance && !Permission.hasPermission(player,"essentials.wartung.bypass")) {
            event.setResult(ResultedEvent.ComponentResult.denied(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.maintenance)));
        }else if(dataConfig.maintenance) {
            player.sendMessage(Messages.prefix.append(Component.text("§8The network ist currently in §1maintenance. \n§8You can still join because you are §1permitted §8to!")));
        }
        if(VEssentials.PLUGIN.messages.tablist) {
            player.sendPlayerListHeaderAndFooter(PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.header,player),
                    PlaceHolders.replaceAsComponent(VEssentials.PLUGIN.messages.footer,player));
        }
        if(VEssentials.PLUGIN.setup.groups.getPlayerGroup(player.getUsername()) == null) {
            VEssentials.PLUGIN.setup.groups.getGroup("Player").addPlayer(player.getUsername());
        }
        VEssentials.PLUGIN.server.getScheduler().buildTask(VEssentials.PLUGIN, Tablist::updateTablist).delay(4, TimeUnit.SECONDS).schedule();
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
    public void onServerChange(ServerPreConnectEvent event) {
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
