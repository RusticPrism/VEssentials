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

    public MessageConfig() {
        config = loadConfig();
        loadToVariables();
    }

    private Toml loadConfig() {
        File folder = VEssentials.plugin.path.toFile();
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
                VEssentials.plugin.logger.error(exception.getMessage());
                return null;
            }
        }
        return new Toml().read(file);
    }
    private void loadToVariables() {
        StringBuilder banmessagebuilder = new StringBuilder();
        for(Object str : config.getList("BanMessage")) {
            if(str instanceof String) {
                banmessagebuilder.append(str).append(" ");
            }
        }
        banmessage = banmessagebuilder.substring(0,banmessagebuilder.length() - 1);
    }
}
