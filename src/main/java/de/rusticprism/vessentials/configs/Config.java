package de.rusticprism.vessentials.configs;

import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.configs.util.FileConfiguration;
import de.rusticprism.vessentials.configs.util.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config {
    private final File file;
    public FileConfiguration config;
    private final String name;
    public Config(String filename, boolean data) {
        file = new File(VEssentials.PLUGIN.path.toString() + (data ? "/data/" + filename + ".yml" : filename + ".yml"));
        name = filename;
        load();
    }
    public void load() {
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                VEssentials.PLUGIN.logger.warn("Error creating " + file.getName(), e);
                return;
            }
            config = YamlConfiguration.loadConfiguration(file);
            createDefault();
        }else config = YamlConfiguration.loadConfiguration(file);
    }
    public abstract void createDefault();

    public String getName() {
        return name;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }
}
