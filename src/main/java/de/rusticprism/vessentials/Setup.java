package de.rusticprism.vessentials;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.commands.util.VEssentialsCommand;
import de.rusticprism.vessentials.configs.*;
import de.rusticprism.vessentials.groups.Group;
import de.rusticprism.vessentials.groups.Groups;
import de.rusticprism.vessentials.scheduler.TablistScheduler;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;

public class Setup {
    public Groups groups;
    public Setup() {
        new PlaceHolders(VEssentials.PLUGIN);
        registerAllConfigs();
        registerallCommands();
       if(groups.groups.isEmpty()) {
           Group player = groups.createGroup("Player");
           player.setPrefix("§7Player | §7");
           for(Player player1 : VEssentials.PLUGIN.server.getAllPlayers()) {
               player.addPlayer(player1.getUsername());
           }
       }
    }
    public void registerallCommands() {
        new VEssentialsCommand();
        VEssentialsCommand.INSTANCE.registerCommands();
    }
    public void registerAllConfigs() {
        groups = new Groups();
        Configurations.register(new DataConfig());
        Configurations.register(new BanConfig());
        Configurations.register(new NavigatorConfig());
    }
    public void registerScheduler() {
       TablistScheduler.run(VEssentials.PLUGIN.server);
    }
}
