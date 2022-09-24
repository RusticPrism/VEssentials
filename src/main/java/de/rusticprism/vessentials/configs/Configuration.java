package de.rusticprism.vessentials.configs;

import de.rusticprism.vessentials.VEssentials;

import java.io.File;
import java.io.IOException;

public abstract class Configuration {
    public File file;
    public Configuration(String filename) {
        file = new File(VEssentials.plugin.path.toFile(),filename + ".json");
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                VEssentials.plugin.logger.error("Couldn't create Config!");
            }
        }
    }
}
