package de.rusticprism.vessentials.configs;

import com.velocitypowered.api.proxy.player.ResourcePackInfo;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.PlaceHolders;

public class DataConfig extends Config {

    private String kickReason = "Kicked by an Operator";
    private String kickPlayer = "CONSOLE";

    public boolean friendsMessage = true;

    public DataConfig() {
        super("data", true);
    }

    public String getLobbyServer() {
        return config.getString("LobbyServer");
    }

    public String getKickReason() {
        return kickReason;
    }

    public void setKickReason(String kickReason) {
        this.kickReason = kickReason;
    }

    public String getKickPlayer() {
        return kickPlayer;
    }

    public void setKickPlayer(String kickPlayer) {
        this.kickPlayer = kickPlayer;
    }

    public boolean isMaintenance() {
        return config.getBoolean("maintenance");
    }

    public boolean isTexturePack() {
        return config.getBoolean("texturepack");
    }
    public ResourcePackInfo getTexturePack() {
        return VEssentials.PLUGIN.server.createResourcePackBuilder(config.getString("texturepack-link"))
                .setPrompt(PlaceHolders.translate("server-texturepack-promt"))
                .build();
    }

    public void setMaintenance(boolean maintenance) {
        config.set("maintenance", maintenance);
        saveConfig();
    }

    public String getMaintenancereason() {
        return String.valueOf(config.get("maintenance-reason"));
    }

    public void setMaintenancereason(String maintenancereason) {
        config.set("maintenance-reason", maintenancereason);
        saveConfig();
    }

    public String getMaintenanceplayer() {
        return String.valueOf(config.get("maintenance-player"));
    }

    public void setMaintenanceplayer(String maintenanceplayer) {
        config.set("maintenance-player", maintenanceplayer);
        saveConfig();
    }

    public boolean isTablist() {
        return config.getBoolean("global-tablist");
    }

    public void setTablist(boolean tablist) {
        config.set("global-tablist", tablist);
        saveConfig();
    }
    public boolean isFriendsMessage() {
        return friendsMessage;
    }

    @Override
    public void createDefault() {
        config.set("maintenance", false);
        config.set("global-tablist", true);
        config.set("friends-message", true);
        config.set("LobbyServer", "lobby");
        config.set("texturepack", false);
        config.set("texturepack-link", "https://www.kreiscraft.net/uploads/Texturepack.zip");
    }
}
