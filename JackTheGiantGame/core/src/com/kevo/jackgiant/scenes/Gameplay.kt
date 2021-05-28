package com.kevo.jackgiant.scenes

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.ScreenUtils
import com.kevo.jackgiant.GameMain

class Gameplay(private val game: GameMain) : Screen {

    private var backgrounds: ArrayList<Sprite> = arrayListOf()

    /**
     * Create a world in which physics bodies are allowed to sleep (this
     * makes the game engine more efficient, so calculations do not need
     * to be performed for sleeping bodies.
     */
    private val world: World = World(Vector2(0f, -2.0f), true)

    init {
        createBackgrounds()
    }

    @Synchronized
    private fun createBackgrounds() {
        backgrounds = arrayListOf()

        for (i in 0..2) {
            val background = Sprite(Texture("Backgrounds/Game BG.png"))
            // The coordinate system in libGDX starts at the bottom-left corner (0, 0).
            // Position each background below the last background (in a parallax
            // scrolling fashion).
            background.setPosition(0f, -(i * background.height))
            backgrounds.add(background)
        }
    }

    private fun drawBackgrounds() {
        for (bg in backgrounds) game.batch.draw(bg, bg.x, bg.y)
    }

    //region Screen Implementation

    /** Called when this screen becomes the current screen for a [Game]. */
    override fun show() {
        // TODO("Not yet implemented")
    }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    override fun render(delta: Float) {
        ScreenUtils.clear(1f, 0f, 0f, 1f)

        game.batch.begin()

        drawBackgrounds()

        game.batch.end()

        // Time step, velocity iterations, and position iterations. Higher numbers
        // result in better precision, but worse performance.
        world.step(Gdx.graphics.deltaTime, 6, 2)
    }

    /**
     * @see ApplicationListener.resize
     */
    override fun resize(width: Int, height: Int) {
        // TODO("Not yet implemented")
    }

    /**
     * @see ApplicationListener.pause
     */
    override fun pause() {
        // TODO("Not yet implemented")
    }

    /**
     * @see ApplicationListener.resume
     */
    override fun resume() {
        // TODO("Not yet implemented")
    }

    /** Called when this screen is no longer the current screen for a [Game]. */
    override fun hide() {
        // TODO("Not yet implemented")
    }

    /** Called when this screen should release all resources. */
    override fun dispose() {
        // TODO("Not yet implemented")
    }

    //endregion
}
