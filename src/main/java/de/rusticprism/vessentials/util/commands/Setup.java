package de.rusticprism.vessentials.util.commands;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.*;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.DataConfig;
import de.rusticprism.vessentials.configs.GroupConfig;
import de.rusticprism.vessentials.groups.Group;
import de.rusticprism.vessentials.groups.Groups;
import de.rusticprism.vessentials.scheduler.TablistScheduler;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.PlaceHolders;

public class Setup {
    public Configurations configs;
    public Groups groups;
    public Setup() {
        configs = new Configurations();
        registerAllConfigs();
        new PlaceHolders(VEssentials.PLUGIN, this);
        new Messages();
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
        VEssentials.PLUGIN.cmdman.registerMain("message", new de.rusticprism.vessentials.commands.MessageCommand(),"msg");
        VEssentials.PLUGIN.cmdman.registerMain("online", new OnlineCommand(),"glist", "vlist");
        VEssentials.PLUGIN.cmdman.registerMain("broadcast", new BroadcastCommand(),"shout", "alert");
        VEssentials.PLUGIN.cmdman.registerMain("server",new ServerCommand());
        VEssentials.PLUGIN.cmdman.registerMain("vban", new BanCommand());
        VEssentials.PLUGIN.cmdman.registerMain("vunban", new UnbanCommand(),"vpardon");
        VEssentials.PLUGIN.cmdman.registerMain("shutdown", new ShutdownCommand(),"end", "vrestart");
        VEssentials.PLUGIN.cmdman.registerMain("vkick", new KickCommand());
        VEssentials.PLUGIN.cmdman.registerMain("kickall", new KickallCommand());
        VEssentials.PLUGIN.cmdman.registerMain("vgroup",new GroupCommand());
        VEssentials.PLUGIN.cmdman.registerMain("vmaintenance", new MaintenanceCommand());
        VEssentials.PLUGIN.cmdman.registerMain("joinme", new JoinmeCommand());
    }
    public void registerAllConfigs() {
        groups = new Groups();
        configs.register(new BanConfig());
        //configs.register(new GroupConfig(groups));
        configs.register(new DataConfig());
    }
    public void registerScheduler() {
       TablistScheduler.run(VEssentials.PLUGIN.server);
    }
}
