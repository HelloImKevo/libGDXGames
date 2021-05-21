package com.kevo.testgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kevo.testgame.helpers.GameInfo;

import kotlin.Pair;

public class GameMain extends ApplicationAdapter {

    // Do not create more than onc instance of a Sprite Batch.
    SpriteBatch batch;
    Texture img;
    Sprite player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("Game BG.png");

        // Initialize the Player sprite.
        player = new Sprite(new Texture("Player 1.png"));
        Pair<Float, Float> worldCenter = PointHelper.getCenter(GameInfo.WIDTH, GameInfo.HEIGHT);
        Pair<Float, Float> playerCenter = PointHelper.getCenter(player);

        // Set position to the center of the world.
        player.setPosition(
                worldCenter.getFirst() - playerCenter.getFirst(),
                worldCenter.getSecond() - playerCenter.getSecond());
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        // The coordinate system in libGDX starts at the bottom-left corner (0, 0).
        batch.draw(img, 0, 0);

        player.setPosition(player.getX() - 1, player.getY());

        // Draw the Player sprite in the center of the world.
        batch.draw(player, player.getX(), player.getY());
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
