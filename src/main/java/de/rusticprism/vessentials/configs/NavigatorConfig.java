package de.rusticprism.vessentials.configs;

import de.rusticprism.vessentials.util.InventoryUtils;
import dev.simplix.protocolize.api.inventory.Inventory;
import dev.simplix.protocolize.api.item.BaseItemStack;
import dev.simplix.protocolize.api.item.ItemStack;
import dev.simplix.protocolize.data.ItemType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NavigatorConfig extends Config{
    public NavigatorConfig() {
        super("navigator", true);
    }
    public Inventory getNavigatorInv() {
        Inventory inventory = new Inventory(InventoryUtils.rows.get(config.getInt("Rows") > 5 ? 5 : config.getInt("Rows")));
        inventory.title(MiniMessage.miniMessage().deserialize(config.getString("Title")));
        for(String str : config.getConfigurationSection("Slots").getKeys(false)) {
            //ItemStack
            System.out.println(str);
            System.out.println(config.getString("Slots." + str + ".type"));
            ItemStack stack = new ItemStack(ItemType.valueOf(config.getString("Slots." + str + ".type").toUpperCase()));
            stack.displayName(MiniMessage.miniMessage().deserialize(config.getString("Slots." + str + ".name")));
            stack.lore(config.getList("Slots." + str + ".lore"), true);
            inventory.item(Integer.parseInt(str),stack);
            inventory.onClick(click -> {
               if(click.clickedItem().itemType().name().equalsIgnoreCase(config.getString("Slots." + str + ".type"))) {
                   System.out.println("Test");
                   click.cancelled(true);
               }
            });
        }

        return inventory;
    }
    public Inventory getNavigatorEditInv() {
        Inventory inventory = new Inventory(InventoryUtils.rows.get(config.getInt("Rows") > 5 ? 5 : config.getInt("Rows")));
        inventory.title(MiniMessage.miniMessage().deserialize(config.getString("Title") + " <reset><bold>(EDIT)"));
        for(String str : config.getConfigurationSection("Slots").getKeys(false)) {
            //ItemStack
            ItemStack stack = new ItemStack(ItemType.valueOf(config.getString("Slots." + str + ".type").toUpperCase()));
            stack.displayName(MiniMessage.miniMessage().deserialize(config.getString("Slots." + str + ".name")));
            stack.lore(config.getList("Slots." + str + ".lore"), true);
            inventory.item(Integer.parseInt(str),stack);
        }

        return inventory;
    }
    public void setNavigatorInv(Inventory inventory) {
        for (Map.Entry<Integer, BaseItemStack> entry : inventory.items().entrySet()) {
            List<String> lore = new ArrayList<>();
            entry.getValue().lore().forEach(o -> {
                lore.add(MiniMessage.miniMessage().serialize((Component) o));
            });
            config.set("Slots." + entry.getKey() + ".type", entry.getValue().itemType().name());
            config.set("Slots." + entry.getKey() + ".name", MiniMessage.miniMessage().serialize(entry.getValue().displayName()));
            config.set("Slots." + entry.getKey() + ".lore", lore);
        }
        saveConfig();
    }

    @Override
    public void createDefault() {
        config.set("Rows", 3);
        config.set("Title", "<blue>Navigator Menu");
        config.set("Slots.13.type", ItemType.GRASS_BLOCK.name());
        config.set("Slots.13.name", "<green>Survival");
        config.set("Slots.13.lore", List.of("line1", "line2"));
        saveConfig();
    }
}
