package de.rusticprism.vessentials.configs;

import com.moandjiezana.toml.Toml;
import de.rusticprism.vessentials.VEssentials;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class MessageConfig {
    private final Toml config;
    public String banmessage;
    public String kickmessage;
    public String prefix;
    public String noperms;
    public String nocons;

    public MessageConfig() {
        config = loadConfig();
        loadToVariables();
    }

    private Toml loadConfig() {
        File folder = VEssentials.PLUGIN.path.toFile();
        File file = new File(folder, "messages.toml");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try (InputStream input = getClass().getResourceAsStream("/" + file.getName())) {
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
        return new Toml().read(file);
    }
    private void loadToVariables() {
        StringBuilder banmessagebuilder = new StringBuilder();
        for (Object str : config.getList("banmessage")) {
            if (str instanceof String) {
                banmessagebuilder.append(str).append("\n");
            }
        }
        banmessage = banmessagebuilder.substring(0, banmessagebuilder.length() - 1);
        StringBuilder kickmessagebuilder = new StringBuilder();
        for (Object str : config.getList("kickmessage")) {
            if (str instanceof String) {
                kickmessagebuilder.append(str).append("\n");
            }
        }
        kickmessage = kickmessagebuilder.substring(0, kickmessagebuilder.length() - 1);
        prefix = config.getString("prefix");
        nocons = config.getString("nocons");
        noperms = config.getString("noperms");
    }
}
