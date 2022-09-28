package de.rusticprism.vessentials.util;

import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.BanConfig;

public class BannedPlayer{
    private final String uuid;
    private final String reason;
    public BannedPlayer(String uuid,String reason) {
        this.uuid = uuid;
        this.reason = reason;
    }
    public String getBanTime() {
        BanConfig config = (BanConfig) VEssentials.plugin.setup.configs.getConfigByName("bannedplayers");
        return config.getString(uuid + ".time");
    }
    public String getReason() {
        return reason;
    }

    public String getUuid() {
        return uuid;
    }
}
