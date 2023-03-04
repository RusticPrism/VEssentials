package de.rusticprism.vessentials.util;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.configs.util.FileConfiguration;
import de.rusticprism.vessentials.configs.util.YamlConfiguration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceHolders {
    private static VEssentials vEssentials;
    private final FileConfiguration language;
    private static final HashMap<String, String> translate = new HashMap<>();
    public PlaceHolders(VEssentials vEssentials) {
        PlaceHolders.vEssentials = vEssentials;
        this.language = getConfig();
        loadConfig();
    }
    public static String replacePlaceHolders(String text, @NotNull Player player) {
        text = text.replaceAll("%player_name%", player.getUsername());
        //text = text.replaceAll("%player_prefix%",setup.groups.getPlayerGroup(player.getUsername()).getPrefix());
        //text = text.replaceAll("%player_suffix%",setup.groups.getPlayerGroup(player.getUsername()).getSuffix());
        text = text.replaceAll("%player_ping%", String.valueOf((int) player.getPing()));
        text = text.replaceAll("%player_server%",player.getCurrentServer().isPresent() ? player.getCurrentServer().get().getServerInfo().getName() : "§cNull");
        text = text.replaceAll("%player_server_players%", player.getCurrentServer().isPresent() ? String.valueOf(player.getCurrentServer().get().getServer().getPlayersConnected().size()) : "§c0");
        //text = text.replaceAll("%player_group%",setup.groups.getPlayerGroup(player.getUsername()).getName());
        text = text.replaceAll("%player_banned%", String.valueOf(Configurations.getConfig(BanConfig.class).isBanned(player)));
        //if player is banned value will be replaced else placeholder is replaced with null!
        text = text.replaceAll("%player_banned_reason%",Configurations.getConfig(BanConfig.class).getReason(player));
        text = text.replaceAll("%player_banned_time%", Configurations.getConfig(BanConfig.class).getTime(player));
        text = text.replaceAll("%player_banned_bannedby%", Configurations.getConfig(BanConfig.class).getBannedBy(player));
        text = text.replaceAll("%player_kicked_kickedby%", Configurations.getConfig(DataConfig.class).kickPlayer);
        text = text.replaceAll("%player_kicked_reason%",Configurations.getConfig(DataConfig.class).kickReason);
        text = replacePlaceHolders(text);
        return text;
    }
    public static String replacePlaceHolders(String text) {
        text = text.replaceAll("%plugin_prefix%",MiniMessage.miniMessage().serialize(Messages.prefix));
        text = text.replaceAll("%system_time%", new SimpleDateFormat("HHmmss").format(DateFormat.getDateInstance().getCalendar().getTime()));
        text = text.replaceAll("%server_players%", String.valueOf(vEssentials.server.getAllPlayers().size()));
        text = text.replaceAll("%server_maintenance%", String.valueOf(Configurations.getConfig(DataConfig.class).isMaintenance()));
        text = text.replaceAll("%server_maintenance_player%", Configurations.getConfig(DataConfig.class).getMaintenanceplayer());
        text = text.replaceAll("%server_maintenance_reason%", Configurations.getConfig(DataConfig.class).getMaintenancereason());
        text = text.replaceAll("&","§");
        return text;
    }
    public static Component replaceAsComponent(String text) {
        return MiniMessage.miniMessage().deserialize(replacePlaceHolders(text));
    }
    public static Component replaceAsComponent(String text,Player player) {
        return MiniMessage.miniMessage().deserialize(replacePlaceHolders(text,player));
    }
    public static Component translate(String translate) {
       return replaceAsComponent(PlaceHolders.translate.getOrDefault(translate.toLowerCase(), translate));
    }
    public static Component translate(String translate, Player player) {
        return replaceAsComponent(PlaceHolders.translate.getOrDefault(translate.toLowerCase(), translate),player);
    }
    private FileConfiguration getConfig() {
        File folder = VEssentials.PLUGIN.path.toFile();
        File file = new File(folder, "/lang/messages.yml");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try (InputStream input = getClass().getResourceAsStream("/lang/" + file.getName())) {
                if (input != null) {
                    Files.copy(input, file.toPath());
                } else {
                    file.createNewFile();
                }
            } catch (IOException exception) {
                VEssentials.PLUGIN.logger.error(exception.getMessage());
                return null;
            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }
    private void loadConfig() {
        for(Map.Entry<String, Object> entry: language.getRoot().getValues(true).entrySet()) {
            if(entry.getValue() instanceof String value) {
                translate.put(entry.getKey(), value);
            }else if(entry.getValue() instanceof List<?> list) {
                StringBuilder builder = new StringBuilder();
                for(Object object : list) {
                    String str = (String) object;
                    builder.append(str).append("<br>");
                }
                translate.put(entry.getKey(),builder.substring(0,builder.length() - 4));
            }else throw new IllegalArgumentException(String.format("The Value of %s has to be an String!",entry.getKey()));
        }
    }
}
