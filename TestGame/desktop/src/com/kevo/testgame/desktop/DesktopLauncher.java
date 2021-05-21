package com.kevo.testgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kevo.testgame.GameMain;
import com.kevo.testgame.helpers.GameInfo;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        // Basic portrait phone dimensions.
        config.width = GameInfo.WIDTH;
        config.height = GameInfo.HEIGHT;

        new LwjglApplication(new GameMain(), config);
    }
}
