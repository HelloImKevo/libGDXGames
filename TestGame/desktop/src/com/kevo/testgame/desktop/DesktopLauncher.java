package com.kevo.testgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kevo.testgame.GameMain;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        // Basic portrait phone dimensions.
        config.width = 480;
        config.height = 800;

        new LwjglApplication(new GameMain(), config);
    }
}
