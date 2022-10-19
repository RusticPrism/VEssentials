package de.rusticprism.vessentials.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.friends.Friend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.*;

public abstract class Configuration {
    public File file;
    public final HashMap<String, String> config;
    private final String name;
    public Configuration(String filename) {
        this.name = filename;
        file = new File(VEssentials.PLUGIN.path.toFile(),"data/" + filename + ".json");
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                VEssentials.PLUGIN.logger.error("Couldn't create Config!");
            }
        }
        config = new HashMap<>();
        loadConfig();
    }
    public void loadConfig() {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(file.toPath());

            // convert JSON file to map
            Map<String,String> uuids = gson.fromJson(reader, Map.class);
            if (uuids == null) {
                return;
            }

            // print map entries
            config.putAll(uuids);

            // close reader
            reader.close();
        } catch (IOException e) {
            VEssentials.PLUGIN.logger.error("Couldn't read Config File!");
        }
    }

    public String getName() {
        return name;
    }

    public void saveConfig() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(builder.create().toJson(config));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            VEssentials.PLUGIN.logger.error("Couldn't save Config!");
        }
    }
    public String getString(String key) {
        return config.get(key);
    }
    public void setString(String key,String value) {
        config.put(key,value);
    }
}
