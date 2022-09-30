package de.rusticprism.vessentials.friends;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.messages.LegacyChannelIdentifier;
import de.rusticprism.vessentials.VEssentials;
import de.rusticprism.vessentials.util.Messages;
import net.kyori.adventure.text.Component;

public class PluginMessage{
    public static void openInv(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("friends");
        OnlinePlayer onlinePlayer = Players.getPlayer(player);
        StringBuilder builder = new StringBuilder();
        builder.append(player.getUniqueId().toString()).append(",");
        for(Friend friend : onlinePlayer.getFriends()) {
           builder.append(friend.getUUID().toString()).append(",");
        }
        out.writeUTF(builder.toString());
        if(player.getCurrentServer().isPresent()) {
            player.getCurrentServer().get().sendPluginMessage(new LegacyChannelIdentifier("BungeeCord"), out.toByteArray());
        }else player.sendMessage(Component.text(Messages.prefix + "§4Couldn't find the Server your on!"));
    }
    public static void send(Player player, String subchannel,String message) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(subchannel);
        output.writeUTF(message);
        if(player.getCurrentServer().isPresent()) {
            player.getCurrentServer().get().sendPluginMessage(new LegacyChannelIdentifier("BungeeCord"), output.toByteArray());
        }else player.sendMessage(Component.text(Messages.prefix + "§4Couldn't find the Server your on!"));
    }
}
