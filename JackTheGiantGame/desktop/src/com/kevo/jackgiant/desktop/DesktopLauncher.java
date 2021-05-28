package com.kevo.jackgiant.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kevo.jackgiant.GameInfo;
import com.kevo.jackgiant.GameMain;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        // Basic portrait phone dimensions.
        config.width = GameInfo.WIDTH;
        config.height = GameInfo.HEIGHT;

        new LwjglApplication(new GameMain(), config);
    }
}
