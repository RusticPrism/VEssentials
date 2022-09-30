package de.rusticprism.vessentials.util.commands;

import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.*;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.friends.subcommands.MessageCommand;
import de.rusticprism.vessentials.friends.subcommands.*;
import de.rusticprism.vessentials.util.Messages;
import org.checkerframework.checker.units.qual.K;

public class Setup {
    public Configurations configs;
    public Setup() {
        new Messages();
        registerallCommands();
        configs = new Configurations();
        registerAllConfigs();
    }
    public void registerallCommands() {
        //Kein Bock Friends System weiterzumachen!
        // iwann vllt!
        // VEssentials.PLUGIN.cmdman.registerMain("friends",new FriendsCommand(),"f");
        VEssentials.PLUGIN.cmdman.registerMain("message", new de.rusticprism.vessentials.commands.MessageCommand(),"msg");
        VEssentials.PLUGIN.cmdman.registerMain("online", new OnlineCommand(),"glist");
        VEssentials.PLUGIN.cmdman.registerMain("broadcast", new BroadcastCommand(),"shout", "alert");
        VEssentials.PLUGIN.cmdman.registerMain("server",new ServerCommand());
        VEssentials.PLUGIN.cmdman.registerMain("pban", new BanCommand());
        VEssentials.PLUGIN.cmdman.registerMain("punban", new UnbanCommand(),"ppardon");
        VEssentials.PLUGIN.cmdman.registerMain("shutdown", new ShutdownCommand(),"end", "prestart");
        VEssentials.PLUGIN.cmdman.registerMain("pkick", new KickCommand());
        VEssentials.PLUGIN.cmdman.registerMain("kickall", new KickallCommand());
        VEssentials.PLUGIN.cmdman.registerSub("add", new AddCommand(),VEssentials.PLUGIN.cmdman.getMainCommand("friends"));
        VEssentials.PLUGIN.cmdman.registerSub("remove", new RemoveCommand(),VEssentials.PLUGIN.cmdman.getMainCommand("friends"));
        VEssentials.PLUGIN.cmdman.registerSub("accept", new AcceptCommand(),VEssentials.PLUGIN.cmdman.getMainCommand("friends"));
        VEssentials.PLUGIN.cmdman.registerSub("decline", new DeclineCommand(),VEssentials.PLUGIN.cmdman.getMainCommand("friends"));
        VEssentials.PLUGIN.cmdman.registerSub("status", new StatusCommand(),VEssentials.PLUGIN.cmdman.getMainCommand("friends"));
        VEssentials.PLUGIN.cmdman.registerSub("msg", new MessageCommand(),VEssentials.PLUGIN.cmdman.getMainCommand("friends"));
        VEssentials.PLUGIN.cmdman.registerSub("list", new ListCommand(),VEssentials.PLUGIN.cmdman.getMainCommand("friends"));
    }
    public void registerAllConfigs() {
        configs.register(new BanConfig());
    }
}
