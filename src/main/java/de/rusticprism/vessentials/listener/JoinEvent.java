package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent;
import com.velocitypowered.api.event.player.ServerPostConnectEvent;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.configs.util.FileConfiguration;
import de.rusticprism.vessentials.util.Permission;
import de.rusticprism.vessentials.util.PlaceHolders;

public class JoinEvent {

    @Subscribe
    public void onJoin(LoginEvent event) {
        Player player = event.getPlayer();
        BanConfig banConfig = Configurations.getConfig(BanConfig.class);
        DataConfig dataConfig = Configurations.getConfig(DataConfig.class);
        //Is Banned
        if (banConfig.isBanned(player)) {
            event.setResult(ResultedEvent.ComponentResult.denied(PlaceHolders.translate("server-ban-message", player)));
            return;
        }
        //Is Maintenance
        if (dataConfig.isMaintenance() && !Permission.hasPermission(player, "essentials.wartung.bypass")) {
            event.setResult(ResultedEvent.ComponentResult.denied(PlaceHolders.translate("server-maintenance-kick")));
        } else if (dataConfig.isMaintenance()) {
            player.sendMessage(PlaceHolders.translate("server-maintenance-message"));
        }
        if (VEssentials.PLUGIN.setup.groups.getPlayerGroup(player.getUsername()) == null) {
            VEssentials.PLUGIN.setup.groups.getGroup("Player").addPlayer(player.getUsername());
        }
    }
    @Subscribe
    public void onPostLogin(PostLoginEvent event) {
        DataConfig dataConfig = Configurations.getConfig(DataConfig.class);
        //Is Global Tablist
        if (dataConfig.isTablist()) {
            event.getPlayer().sendPlayerListHeader(PlaceHolders.replaceAsComponent("server-tablist-header", event.getPlayer()));
            event.getPlayer().sendPlayerListFooter(PlaceHolders.translate("server-tablist-footer", event.getPlayer()));
        }
    }

    @Subscribe
    public void onResourePack(PlayerResourcePackStatusEvent event) {
        if (event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {
            event.getPlayer().disconnect(PlaceHolders.replaceAsComponent("<red>You have to accept the Texturepack"));
        } else if (event.getStatus() == (PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD)) {
            event.getPlayer().disconnect(PlaceHolders.replaceAsComponent("<dark_red><b>Error while trying to download the Texturepack"));
        }
    }

    @Subscribe
    public void onServerChange(ServerPostConnectEvent event) {
        DataConfig dataconfig = Configurations.getConfig(DataConfig.class);
        if (event.getPreviousServer() == null && dataconfig.isTexturePack()) {
            event.getPlayer().sendResourcePackOffer(dataconfig.getTexturePack());
        }
    }
}
