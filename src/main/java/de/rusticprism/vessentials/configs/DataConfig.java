package de.rusticprism.vessentials.configs;

public class DataConfig extends Configuration{

    public String kickReason = "Kicked by an Operator";
    public String kickPlayer = "CONSOLE";
    public DataConfig() {
        super("data");
    }

    public boolean isMaintenance() {
        return Boolean.parseBoolean(config.get("maintenance"));
    }

    public void setMaintenance(boolean maintenance) {
        config.put("maintenance", String.valueOf(maintenance));
        saveConfig();
    }

    public String getMaintenancereason() {
        return config.get("maintenance-reason");
    }

    public void setMaintenancereason(String maintenancereason) {
        config.put("maintenance-reason", maintenancereason);
        saveConfig();
    }

    public String getMaintenanceplayer() {
        return config.get("maintenance-player");
    }

    public void setMaintenanceplayer(String maintenanceplayer) {
        config.put("maintenance-player", maintenanceplayer);
        saveConfig();
    }

    public boolean isTablist() {
        return Boolean.parseBoolean(config.get("tablist"));
    }

    public void setTablist(boolean tablist) {
        config.put("tablist", String.valueOf(tablist));
        saveConfig();
    }
}
