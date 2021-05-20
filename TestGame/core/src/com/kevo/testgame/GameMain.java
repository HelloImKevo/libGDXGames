package com.kevo.testgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import kotlin.Pair;

public class GameMain extends ApplicationAdapter {

    // Do not create more than onc instance of a Sprite Batch.
    SpriteBatch batch;
    Texture img;
    Texture img2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("Game BG.png");
        img2 = new Texture("badlogic.jpg");
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        // The coordinate system in libGDX starts at the bottom-left corner (0, 0).
        batch.draw(img, 0, 0);

        // Draw the "Bad Logic" logo in the center of the world.
        Pair<Float, Float> worldCenter = PointHelper.getCenter(480.0f, 800.0f);
        Pair<Float, Float> imageCenter = PointHelper.getCenter(img2);
        batch.draw(img2,
                worldCenter.getFirst() - imageCenter.getFirst(),
                worldCenter.getSecond() - imageCenter.getSecond());
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
