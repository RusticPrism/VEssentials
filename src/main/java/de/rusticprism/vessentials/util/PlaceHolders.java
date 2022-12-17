package de.rusticprism.vessentials.util;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.util.commands.Setup;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Set;

public class PlaceHolders {
    private static VEssentials vEssentials;
    private static Setup setup;
    public PlaceHolders(VEssentials vEssentials, Setup setup) {
        PlaceHolders.vEssentials = vEssentials;
        PlaceHolders.setup = setup;
    }
    public static String replacePlaceHolders(String text, @NotNull Player player) {
        text = text.replaceAll("%player_name%", player.getUsername());
        text = text.replaceAll("%player_prefix%",setup.groups.getPlayerGroup(player.getUsername()).getPrefix());
        text = text.replaceAll("%player_suffix%",setup.groups.getPlayerGroup(player.getUsername()).getSuffix());
        text = text.replaceAll("%player_ping%", String.valueOf((int) player.getPing()));
        text = text.replaceAll("%player_server%",player.getCurrentServer().isPresent() ? player.getCurrentServer().get().getServerInfo().getName() : "§cError");
        text = text.replaceAll("%player_server_players%", String.valueOf(player.getCurrentServer().get().getServer().getPlayersConnected().size()));
        text = text.replaceAll("%player_group%",setup.groups.getPlayerGroup(player.getUsername()).getName());
        text = text.replaceAll("%player_banned%", String.valueOf(setup.configs.getConfig(BanConfig.class).isBanned(player)));
        //if player is banned value will be replaced else placeholder is replaced with null!
        text = text.replaceAll("%player_banned_reason%",setup.configs.getConfig(BanConfig.class).getReason(player));
        text = text.replaceAll("%player_banned_time%", setup.configs.getConfig(BanConfig.class).getTime(player));
        text = text.replaceAll("%player_banned_bannedby", setup.configs.getConfig(BanConfig.class).getBannedBy(player));
        text = replacePlaceHolders(text);
        return text;
    }
    public static String replacePlaceHolders(String text) {
        text = text.replaceAll("%system_time%", new SimpleDateFormat("HHmmss").format(DateFormat.getDateInstance().getCalendar().getTime()));
        text = text.replaceAll("%server_players%", String.valueOf(vEssentials.server.getAllPlayers().size()));
        text = text.replaceAll("%server_maintenance%", String.valueOf(setup.configs.getConfig(DataConfig.class).maintenance));
        text = text.replaceAll("%server_maintenance_player%", setup.configs.getConfig(DataConfig.class).maintenanceplayer);
        text = text.replaceAll("%server_maintenance_reason%", setup.configs.getConfig(DataConfig.class).maintenancereason);
        text = text.replaceAll("%plugin_noperms%",VEssentials.PLUGIN.messages.noperms);
        text = text.replaceAll("%plugin_nocons%",VEssentials.PLUGIN.messages.nocons);
        text = replacePrefix(text);
        text = text.replaceAll("&","§");
        Component component = MiniMessage.miniMessage().deserialize(text);
        text = MiniMessage.miniMessage().serialize(component);
        return text;
    }
    public static String replacePrefix(String text) {
        text = text.replaceAll("%plugin_prefix%",VEssentials.PLUGIN.messages.prefix);
        return text;
    }
    public static Component replaceAsComponent(String text) {
        return MiniMessage.miniMessage().deserialize(replacePlaceHolders(text));
    }
    public static Component replaceAsComponent(String text,Player player) {
        return MiniMessage.miniMessage().deserialize(replacePlaceHolders(text,player));
    }
}
