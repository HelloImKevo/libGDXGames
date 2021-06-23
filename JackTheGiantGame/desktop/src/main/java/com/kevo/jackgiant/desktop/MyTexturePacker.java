package com.kevo.jackgiant.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class MyTexturePacker {

    public static void main(String[] args) {
        TexturePacker.process(
                getAssetDir("Player"),
                getAssetDir("PlayerAnimation"),
                "PlayerSpriteSheet");
    }

    /**
     * @param targetAssetDirectoryPath Example: "Player/Animations"
     */
    @NotNull
    private static String getAssetDir(@NotNull String targetAssetDirectoryPath) {
        File currentWorkingDir = new File(System.getProperty("user.dir"));
        String path = currentWorkingDir.toString();
        String assetsPath = path + File.separator + "android" + File.separator +
                "assets" + File.separator;
        return assetsPath + targetAssetDirectoryPath;
    }
}
