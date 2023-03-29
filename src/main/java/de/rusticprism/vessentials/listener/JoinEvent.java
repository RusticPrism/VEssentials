package de.rusticprism.vessentials.listener;

import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.DataConfig;
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
        //Is Global Tablist
        if (dataConfig.isTablist()) {
            player.sendPlayerListHeaderAndFooter(PlaceHolders.replaceAsComponent("server-tablist-header", player),
                    PlaceHolders.translate("server-tablist-footer", player));
        }
        if (VEssentials.PLUGIN.setup.groups.getPlayerGroup(player.getUsername()) == null) {
            VEssentials.PLUGIN.setup.groups.getGroup("Player").addPlayer(player.getUsername());
        }
    }

   /* @Subscribe
    public void onResourePack(PlayerResourcePackStatusEvent event) {
        if (event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {
            event.getPlayer().disconnect(PlaceHolders.replaceAsComponent("<red>Bitte akzepiere das Texture Pack!"));
        } else if (event.getStatus() == (PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD)) {
            event.getPlayer().disconnect(PlaceHolders.replaceAsComponent("<dark_red><b>Der download des Texture Packs ist fehlgeschlagen!"));
        }
    }

    @Subscribe
    public void onServerChange(ServerPostConnectEvent event) {
        if (event.getPreviousServer() == null) {
            event.getPlayer().sendResourcePackOffer(
                    VEssentials.PLUGIN.server.createResourcePackBuilder("https://download.mc-packs.net/pack/083434e2244c6ada87c0286420a5f191c8567850.zip")
                            .setPrompt(PlaceHolders
                                    .replaceAsComponent("<gray><st>                     <reset><gray>| <blue>TexturePack <gray>|<gray><st>                     ")
                                    .appendNewline()
                                    .appendNewline()
                                    .append(PlaceHolders.replaceAsComponent("<gray>Bitte akzeptiere das Kreiscraft Texture Pack"))
                                    .appendNewline()
                                    .append(PlaceHolders.replaceAsComponent("<gray>Das Texturepack ist ein Teil des neuen Plugins Slimefun"))
                                    .appendNewline()
                                    .appendNewline()
                                    .append(PlaceHolders.replaceAsComponent("<gray><st>                     <reset><gray>| <blue>TexturePack <gray>|<gray><st>                     ")))
                            .build());
        }
    }
    */
}
