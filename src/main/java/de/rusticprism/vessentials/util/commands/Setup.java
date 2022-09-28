package de.rusticprism.vessentials.util.commands;

import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.BroadcastCommand;
import de.rusticprism.vessentials.commands.FriendsCommand;
import de.rusticprism.vessentials.commands.OnlineCommand;
import de.rusticprism.vessentials.commands.ServerCommand;
import de.rusticprism.vessentials.configs.BanConfig;
import de.rusticprism.vessentials.configs.Configuration;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.friends.subcommands.*;

public class Setup {
    public Configurations configs;
    public Setup() {
        registerallCommands();
        configs = new Configurations();
        registerAllConfigs();
    }
    public void registerallCommands() {
        //Kein Bock Friends System weiterzumachen!
        // iwann vllt!
        // VEssentials.plugin.cmdman.registerMain("friends",new FriendsCommand(),"f");
        VEssentials.plugin.cmdman.registerMain("message", new de.rusticprism.vessentials.commands.MessageCommand(),"msg");
        VEssentials.plugin.cmdman.registerMain("online", new OnlineCommand(),"glist");
        VEssentials.plugin.cmdman.registerMain("broadcast", new BroadcastCommand(),"shout");
        VEssentials.plugin.cmdman.registerMain("server",new ServerCommand());
        VEssentials.plugin.cmdman.registerSub("add", new AddCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("remove", new RemoveCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("accept", new AcceptCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("decline", new DeclineCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("status", new StatusCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("msg", new MessageCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("list", new ListCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
    }
    public void registerAllConfigs() {
        configs.register(new BanConfig());
    }
}
