package de.rusticprism.vessentials.configs;

import java.util.ArrayList;
import java.util.List;

public class Configurations {
    public final List<Configuration> configs;
    public Configurations() {
        configs = new ArrayList<>();
    }
    public void register(Configuration configuration) {
        configs.add(configuration);
    }
    public Configuration getConfigByName(String name) {
        Configuration configuration = null;
        for(Configuration config : configs) {
            if(config.getName().equalsIgnoreCase(name)) {
                configuration = config;
            }
        }
        return configuration;
    }
}
