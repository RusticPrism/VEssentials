package de.rusticprism.vessentials.configs;

import com.moandjiezana.toml.Toml;
import de.rusticprism.vessentials.VEssentials;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class MessageConfig {
    public final Toml config;
    public String banmessage;
    public String kickmessage;
    public String prefix;
    public String noperms;
    public String nocons;
    public String motd;
    public boolean tablist;
    public String header;
    public String footer;
    public String restartmessage;
    public String restartkick;
    public String maintenance;
    public String joinme;
    public String fewArgs;
    public String manyArgs;
    public String maintenancemotd;

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
        motd = config.getString("motdline1") + "\n" + config.getString("motdline2");
        tablist = config.getBoolean("tablist");
        fewArgs = config.getString("ToFewArgs");
        manyArgs = config.getString("ToManyArgs");
        StringBuilder footerbuilder = new StringBuilder();
        for(Object str : config.getList("footer")) {
            if(str instanceof String) {
                footerbuilder.append(str).append("\n");
            }
        }
        footer = footerbuilder.substring(0,footerbuilder.length() - 1);
        StringBuilder headerbuilder = new StringBuilder();
        for(Object str : config.getList("header")) {
            if(str instanceof String) {
                headerbuilder.append(str).append("\n");
            }
        }
        header = headerbuilder.substring(0,headerbuilder.length() - 1);
        restartmessage = config.getString("restart-message");
        StringBuilder restartkickbuilder = new StringBuilder();
        for(Object str : config.getList("restart-kick")) {
            if(str instanceof String) {
                restartkickbuilder.append(str).append("\n");
            }
        }
        restartkick = restartkickbuilder.substring(0,restartkickbuilder.length() -1);
        StringBuilder maintenancebuilder = new StringBuilder();
        for(Object str : config.getList("maintenance")) {
            if(str instanceof String) {
                maintenancebuilder.append(str).append("\n");
            }
        }
        maintenance = maintenancebuilder.substring(0, maintenancebuilder.length() - 1);
        System.out.println(maintenance);
        StringBuilder joinmebuilder = new StringBuilder();
        for(Object str : config.getList("joinme")) {
            if(str instanceof String) {
                joinmebuilder.append(str).append("\n");
            }
        }
        joinme = joinmebuilder.substring(0,joinmebuilder.length() -1);
        maintenancemotd = config.getString("MaintenanceMotd1") + "\n" + config.getString("MaintenanceMotd2");
    }
}
