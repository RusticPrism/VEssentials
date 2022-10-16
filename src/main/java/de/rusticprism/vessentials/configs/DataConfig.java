package de.rusticprism.vessentials.configs;

public class DataConfig extends Configuration{
    public DataConfig() {
        super("data");
        maintenance = Boolean.parseBoolean(config.get("maintenance"));
        maintenancereason = config.get("maintenance-reason");
        maintenanceplayer = config.get("maintenance-player");
    }
    public boolean maintenance;
    public String maintenancereason;
    public String maintenanceplayer;
}
