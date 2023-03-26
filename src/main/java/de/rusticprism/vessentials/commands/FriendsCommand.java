package de.rusticprism.vessentials.commands;

import com.velocitypowered.api.proxy.Player;
import de.rusticprism.vessentials.commands.util.CommandInfo;
import de.rusticprism.vessentials.commands.util.PluginCommand;
import de.rusticprism.vessentials.commands.util.TabCompleter;

@CommandInfo(name = "friends",permission = "essentials.command.friends",requiresPlayer = true, aliases = {"friends","f"})
public class FriendsCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
      /**  FriendsConfig config = VEssentials.PLUGIN.setup.configs.getConfig(FriendsConfig.class);
        ProtocolizePlayer protPlayer = Protocolize.playerProvider().player(player.getUniqueId());
        if(args.length == 0) {
            protPlayer.openInventory(getPage(1, player));
        }
        if(args.length > 0) {
            switch (args[0]) {
                case "page" -> {
                    int i;
                    try {
                      i = Integer.parseInt(args[1]);
                    }catch (NumberFormatException e) {
                        player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>Argument 2 has to be a Number")));
                        return;
                    }
                    protPlayer.openInventory(getPage(i, player));
                }
                case "add" -> {
                    if(args[1].equalsIgnoreCase(player.getUsername())) {
                        player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>You cannot add yourself as a Friend!")));
                        return;
                    }
                    if(VEssentials.PLUGIN.server.getPlayer(args[1]).isEmpty()) {
                        player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>This Player isn´t online!")));
                        return;
                    }
                    Player target = VEssentials.PLUGIN.server.getPlayer(args[1]).get();
                    if(config.isFriend(player, target.getUniqueId().toString())) {
                        player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>You already have this Player as Friend!")));
                        return;
                    }
                    if(!config.sendRequest(player, target)) {
       player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>You´ve already sent this Player a Friend request")));
       return;
       }
       player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully sent <purple>" + target.getUsername() + " <gray>a Friend Request.")));
       }
                case "remove" -> {
                    if(args[1].equalsIgnoreCase(player.getUsername())) {
                        player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>You cannot remove yourself as a Friend!")));
                        return;
                    }
                    String uuid = UUIdUtils.getUUId(args[1]);
                    if(!config.isFriend(player, uuid)) {
                        player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>You don´t have that Player as a Friend!")));
                        return;
                    }
       config.removeFriend(player,uuid);
       player.sendMessage(PlaceHolders.replaceAsComponent("Removed"));
       }
                case "accept"-> {
                    String uuid = UUIdUtils.getUUId(args[1]);
                    if(!config.acceptRequest(player, uuid)) {
       player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>This Player hasn't sent you a Friend Request.")));
       return;
       }
       player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully accepted <purple>"  + UUIdUtils.getName(uuid) + "´s <gray>Friend Request.")));
       }
                case "decline" -> {
                    String uuid = UUIdUtils.getUUId(args[1]);
                    if(!config.declineRequest(player, uuid)) {
       player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<red>This Player hasn't sent you a Friend Request.")));
       return;
       }
       player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully declined <purple>"  + UUIdUtils.getName(uuid) + "´s <gray>Friend Request.")));
       }
                case "acceptAll" -> {
                    for (String uuid : config.getRequests(player)) {
                        config.acceptRequest(player, uuid);
       }
       player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully accepted <purple>all <gray>Friend Requests.")));
       }
                case "declineAll" -> {
       config.setRequests(player, Collections.emptyList());
       player.sendMessage(Messages.prefix.append(PlaceHolders.replaceAsComponent("<gray>Successfully declined <purple>all <gray>Friend Requests.")));
       }
                default -> player.sendMessage(Messages.args);
            }
        }
    }

    @Override
    public TabCompleter complete(String[] args) {
        return new TabCompleter(0,"add","remove","list","page","accept","decline","acceptAll","declineAll")
                .at(1,args[0].equalsIgnoreCase("page") ? new String[]{"1","2","3","4","5"} : TabCompleter.EMPTY.getCompletion());
    }
    public List<Inventory> getInv(Player player) {
        List<Inventory> inventories = new ArrayList<>();
        FriendsConfig config = VEssentials.PLUGIN.setup.configs.getConfig(FriendsConfig.class);
        config.addFriend(player,"f6f3a530-6c39-4098-96a0-6bdf4f3afc70");
        Inventory friendsInv = InventoryUtils.FRIENDS_INV();
        if(config.getFriends(player) == null || config.getFriends(player).isEmpty()) {
            ItemStack noFriends = new ItemStack(ItemType.RED_CONCRETE)
                    .displayName(PlaceHolders.replaceAsComponent("<dark_red><bold>You don´t have any Friends yet!"));
            noFriends.lore(InventoryUtils.EMPTY_LORE(3),true);
            noFriends.lore(1,PlaceHolders.replaceAsComponent("<gray>Use /friends add <player> to add your first Friend"));
            friendsInv.item(10,noFriends);
            inventories.add(friendsInv);
            return inventories;
        }
        if(config.getFriends(player).size() < 3*9) {
            friendsInv.item(44, new ItemStack(ItemType.BLUE_STAINED_GLASS_PANE).displayName(" "));
            List<String> friends = config.getFriends(player);
            for(int j = 1; j < 4; j++) {
                for (int i = 1 + j * 9; i < 8 + j * 9; i++) {
                    String friendId;
                    try {
                        friendId = friends.get(0);
                    }catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                    if(VEssentials.PLUGIN.server.getPlayer(UUID.fromString(friendId)).isPresent()) {
                        Player friend = VEssentials.PLUGIN.server.getPlayer(UUID.fromString(friendId)).get();
                        ItemStack friendsItem = new ItemStack(ItemType.PLAYER_HEAD);
                        CompoundTag tag = new CompoundTag();
                        tag.putString("SkullOwner",friend.getUsername());
                        friendsItem.nbtData(tag);
                        friendsItem.displayName(PlaceHolders.replaceAsComponent("<green>" + friend.getUsername()));
                        friendsItem.lore(InventoryUtils.EMPTY_LORE(3),true);
                        friendsItem.lore(1,PlaceHolders.replaceAsComponent("<green>Online <gray>on Server <yellow>%player_server%",player));
                        friendsInv.item(i, friendsItem);
                        friends.remove(0);
                    }else {
                        ItemStack offlineFriend = new ItemStack(ItemType.SKELETON_SKULL);
                        offlineFriend.displayName("%player_name%");
                        offlineFriend.lore(InventoryUtils.EMPTY_LORE(3),true);
                        offlineFriend.lore(1,PlaceHolders.replaceAsComponent("<red>Offline <gray>since <yellow>%offline_days% <gray>Days"));
                        friendsInv.item(i, offlineFriend);
                        friends.remove(0);
                    }
                }
            }
        }
        inventories.add(friendsInv);
        return inventories;
    }
    public Inventory getPage(int site, Player player) {
        if(site == 0) {
            return InventoryUtils.ERROR_INV();
        }
        if(site > getInv(player).size()) {
            return InventoryUtils.ERROR_INV();
        }
        return getInv(player).get(site -1);
    }
    **/
}

    @Override
    public TabCompleter complete(String[] args) {
        return TabCompleter.EMPTY;
    }
}
