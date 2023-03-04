package de.rusticprism.vessentials.util;

import com.velocitypowered.api.proxy.player.ResourcePackInfo;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.nio.charset.StandardCharsets;

public class KreiscraftResourcePack implements ResourcePackInfo {
    @Override
    public String getUrl() {
        return "https://download.mc-packs.net/pack/14b94091cad9bafa0422490f48dcfa8c4e6ad730.zip";
    }

    @Override
    public @Nullable Component getPrompt() {
        return Component.text("""
                §8§m------------------------§r| §1TexturePack §8|§8§m------------------------\s
                \s
                 §8Bitte klicke auf akzeptieren um das Kreiscraft TexturePack herunterzuladen\s
                 Das Texturepack ist ein  Teil des neuen Plugins Slimefun\s
                \s
                 §8§m------------------------§r| §1TexturePack §8|§8§m------------------------""");
    }

    @Override
    public boolean getShouldForce() {
        return false;
    }

    @Override
    public byte @Nullable [] getHash() {
        return "d41d8cd98f00b204e".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Origin getOrigin() {
        return Origin.PLUGIN_ON_PROXY;
    }

    @Override
    public Origin getOriginalOrigin() {
        return getOrigin();
    }

    @Override
    public Builder asBuilder() {
        return null;
    }

    @Override
    public Builder asBuilder(String newUrl) {
        return null;
    }
}
