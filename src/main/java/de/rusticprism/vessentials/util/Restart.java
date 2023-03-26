package de.rusticprism.vessentials.util;

import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.text.Component;

public class Restart {
    private Integer time;
    private final String reason;
    private final CommandSource source;
    public Restart(CommandSource restarter,String time,String reason) {
        this.time = replaceTimeUnits(restarter,time);
        this.reason = reason;
        this.source = restarter;
    }

    public CommandSource getSource() {
        return source;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public Integer replaceTimeUnits(CommandSource source, String time) {
        int endtime = 0;
        if(time.toLowerCase().contains("h")) {
            try {
                Integer.parseInt(time.replace("h",""));
            }catch (NumberFormatException e) {
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>No TimeUnit because of <dark_red>" + e.getMessage())));
                return 0;
            }
            endtime = Integer.parseInt(time.replace("h","")) * 3600;
        }else if(time.toLowerCase().contains("m")) {
            try {
                Integer.parseInt(time.replace("m",""));
            }catch (NumberFormatException e) {
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>No TimeUnit because of <dark_red>" + e.getMessage())));
                return 0;
            }
            endtime = Integer.parseInt(time.replace("m","")) * 60;
        }else if (time.toLowerCase().contains("s")) {
            try {
                Integer.parseInt(time.replace("s", ""));
            } catch (NumberFormatException e) {
                source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>No TimeUnit because of <dark_red>" + e.getMessage())));
                return 0;
            }
            endtime = Integer.parseInt(time.replace("s", ""));
        } else
            source.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>No Timeunit could be found!")));
        return endtime;
    }
}
