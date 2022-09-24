package de.rusticprism.vessentials.util.commands;

import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.commands.FriendsCommand;
import de.rusticprism.vessentials.friends.subcommands.*;

public class Setup {
    public Setup() {
        registerallCommands();
    }
    public void registerallCommands() {
        VEssentials.plugin.cmdman.registerMain("friends",new FriendsCommand(),"f");
        VEssentials.plugin.cmdman.registerSub("add", new AddCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("remove", new RemoveCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("accept", new AcceptCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("decline", new DeclineCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("status", new StatusCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("msg", new MessageCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
        VEssentials.plugin.cmdman.registerSub("list", new ListCommand(),VEssentials.plugin.cmdman.getMainCommand("friends"));
    }
}
