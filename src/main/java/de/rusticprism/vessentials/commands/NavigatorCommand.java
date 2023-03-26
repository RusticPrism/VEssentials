package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;
import de.rusticprism.vessentials.configs.Configurations;
import de.rusticprism.vessentials.configs.NavigatorConfig;
import de.rusticprism.vessentials.util.Messages;
import de.rusticprism.vessentials.util.NumberUtils;
import de.rusticprism.vessentials.util.PlaceHolders;
import dev.simplix.protocolize.api.Protocolize;
import dev.simplix.protocolize.api.inventory.Inventory;
import dev.simplix.protocolize.api.item.BaseItemStack;
import dev.simplix.protocolize.api.item.ItemStack;
import dev.simplix.protocolize.api.player.ProtocolizePlayer;

//@CommandInfo(name = "navigator", permission = "essentials.command.navigator", requiresPlayer = true)
//TODO make Proxy Inventories editable
public class NavigatorCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        ProtocolizePlayer protPlayer = Protocolize.playerProvider().player(player.getUniqueId());
        NavigatorConfig navigatorConfig = Configurations.getConfig(NavigatorConfig.class);
        if(args.length == 0) {
            protPlayer.openInventory(navigatorConfig.getNavigatorInv());
        }else if(args.length == 2) {
            Inventory inventory = navigatorConfig.getNavigatorInv();
            switch (args[0].toLowerCase()) {
                case "set" -> {
                    if(!NumberUtils.isInteger(args[1])) {
                        player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>Argument 1 has to be an Integer")));
                        return;
                    }
                    BaseItemStack baseitemStack = protPlayer.proxyInventory().item(protPlayer.proxyInventory().heldItem());
                    ItemStack itemStack = new ItemStack(baseitemStack.itemType());
                    itemStack.displayName(baseitemStack.displayName());
                    itemStack.lore(baseitemStack.lore(),false);
                    inventory.item(Integer.parseInt(args[1]),itemStack);
                    player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully added the Item in your Mainhand to the Navigator.")));
                }
                case "fill" -> {
                    BaseItemStack baseitemStack = protPlayer.proxyInventory().item(protPlayer.proxyInventory().heldItem());
                    ItemStack itemStack = new ItemStack(baseitemStack.itemType());
                    itemStack.displayName(baseitemStack.displayName());
                    itemStack.lore(baseitemStack.lore(),false);
                    for(int i = 0; i < navigatorConfig.getNavigatorInv().type().getTypicalSize(protPlayer.protocolVersion()); i++) {
                        inventory.item(i, itemStack);
                    }
                    player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully filled the Navigator with the Item in your Mainhand.")));
                }
                case "remove" -> {
                    if(!NumberUtils.isInteger(args[1])) {
                        player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>Argument 1 has to be an Integer")));
                        return;
                    }
                    inventory.removeItem(Integer.parseInt(args[1]));
                    player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully removed the Item in slot <blue>" + args[1] + " <gray>from the Navigator")));
                }
            }
        }

    }

    @Override
    public TabCompleter complete(String[] args) {
        TabCompleter tabCompleter = new TabCompleter(0, "set", "fill", "remove");
        if(args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("remove")) {
            tabCompleter.at(1,"0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        }
        return tabCompleter;
    }
}
