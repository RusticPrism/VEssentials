package de.rusticprism.vessentials.util;

import com.velocitypowered.proxy.protocol.ProtocolUtils;
import dev.simplix.protocolize.api.inventory.Inventory;
import dev.simplix.protocolize.api.item.ItemStack;
import dev.simplix.protocolize.api.util.ProtocolUtil;
import dev.simplix.protocolize.data.ItemType;
import dev.simplix.protocolize.data.inventory.InventoryType;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {
    public static Inventory ERROR_INV() {
        Inventory inventory = new Inventory(InventoryType.CRAFTING);
        inventory.title(PlaceHolders.replaceAsComponent("<dark_red>ERROR"));
        inventory.onClick(click -> click.cancelled(true));
        for (int i = 0; i < 10; i++) {
            ItemStack stack = new ItemStack(ItemType.BARRIER)
                    .displayName(PlaceHolders.replaceAsComponent("<dark_red><bold>ERROR"));
            stack.addToLore(PlaceHolders.replaceAsComponent("<red>An Error occurred when trying to open that Inventory"));
            inventory.item(i, stack);
        }
        return inventory;
    }

    public static Inventory FRIENDS_INV() {
        Inventory inventory = new Inventory(InventoryType.GENERIC_9X5).title(PlaceHolders.replaceAsComponent("<blue>Your Friends"));
        //Top Row
        inventory.item(0, new ItemStack(ItemType.BLUE_STAINED_GLASS_PANE).displayName(" "));
        for (int i = 1; i < 8; i++) {
            inventory.item(i, new ItemStack(ItemType.GRAY_STAINED_GLASS_PANE).displayName(" "));
        }
        inventory.item(8, new ItemStack(ItemType.BLUE_STAINED_GLASS_PANE).displayName(" "));

        //Bottom Row
        ItemStack close = new ItemStack(ItemType.BARRIER).displayName(PlaceHolders.replaceAsComponent("<dark_red><bold>CLOSE"));
        close.addToLore(PlaceHolders.replaceAsComponent("<red>Click here to close the Friends Menu"));
        inventory.item(36, close);
        for (int i = 37; i < 44; i++) {
            inventory.item(i, new ItemStack(ItemType.GRAY_STAINED_GLASS_PANE).displayName(" "));
        }
        //Only appears when the item isn´t set correctly in {@link FriendsCommand}
        inventory.item(44, new ItemStack(ItemType.BARRIER).displayName(PlaceHolders.replaceAsComponent("<dark_red><bold>ERROR")));

        //Left Side
        inventory.item(17, new ItemStack(ItemType.GRAY_STAINED_GLASS_PANE).displayName(" "));
        inventory.item(26, new ItemStack(ItemType.GRAY_STAINED_GLASS_PANE).displayName(" "));
        inventory.item(35, new ItemStack(ItemType.GRAY_STAINED_GLASS_PANE).displayName(" "));

        //Right Side
        inventory.item(9, new ItemStack(ItemType.GRAY_STAINED_GLASS_PANE).displayName(" "));
        inventory.item(18, new ItemStack(ItemType.GRAY_STAINED_GLASS_PANE).displayName(" "));
        inventory.item(27, new ItemStack(ItemType.GRAY_STAINED_GLASS_PANE).displayName(" "));

        //Check for clicks
        inventory.onClick(click -> {
            if (click.clickedItem() == null) {
                click.cancelled(true);
                return;
            }
            if (click.clickedItem().itemType().equals(ItemType.GRAY_STAINED_GLASS_PANE) || click.clickedItem().itemType().equals(ItemType.LIGHT_BLUE_STAINED_GLASS)) {
                click.cancelled(true);
                return;
            }
            if (click.clickedItem().itemType().equals(ItemType.BARRIER)) {
                click.player().closeInventory();
            }
        });
        return inventory;
    }
    public static List<String> EMPTY_LORE(int amount) {
        List<String> out = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            out.add(" ");
        }
        return out;
    }
}
