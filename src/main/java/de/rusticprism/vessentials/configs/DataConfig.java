package de.rusticprism.vessentials.configs;

public class DataConfig extends Config{

    public String kickReason = "Kicked by an Operator";
    public String kickPlayer = "CONSOLE";

    public boolean friendsMessage = true;
    public DataConfig() {
        super("data", true);
        config.set("maintenance", "false");
        config.set("global-tablist", "true");
        config.set("friends-message", "true");
    }

    public boolean isMaintenance() {
        return Boolean.parseBoolean(String.valueOf(config.get("maintenance")));
    }

    public void setMaintenance(boolean maintenance) {
        config.set("maintenance", String.valueOf(maintenance));
        config.saveToFile(getFile(),"Error occurred saving " + getName() + "!");
    }

    public String getMaintenancereason() {
        return String.valueOf(config.get("maintenance-reason"));
    }

    public void setMaintenancereason(String maintenancereason) {
        config.set("maintenance-reason", maintenancereason);
        config.saveToFile(getFile(),"Error occurred saving " + getName() + "!");
    }

    public String getMaintenanceplayer() {
        return String.valueOf(config.get("maintenance-player"));
    }

    public void setMaintenanceplayer(String maintenanceplayer) {
        config.set("maintenance-player", maintenanceplayer);
        config.saveToFile(getFile(),"Error occurred saving " + getName() + "!");
    }

    public boolean isTablist() {
        return Boolean.parseBoolean(String.valueOf(config.get("tablist")));
    }

    public void setTablist(boolean tablist) {
        config.set("tablist", String.valueOf(tablist));
        config.saveToFile(getFile(),"Error occurred saving " + getName() + "!");
    }
    public boolean isFriendsMessage() {
        return friendsMessage;
    }
}
