package com.kevo.jackgiant;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kevo.jackgiant.scenes.MainMenu;

import org.jetbrains.annotations.NotNull;

public class GameMain extends Game {

    // Do not create more than one instance of a Sprite Batch.
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @NotNull
    public SpriteBatch getBatch() {
        return batch;
    }
}
