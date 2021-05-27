package com.kevo.testgame.scenes

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.ScreenUtils
import com.kevo.testgame.GameMain
import com.kevo.testgame.helpers.GameInfo
import com.kevo.testgame.player.Player

class MainMenu(private val game: GameMain) : Screen {

    private val background = Texture("Game BG.png")

    /**
     * Create a world in which physics bodies are allowed to sleep (this
     * makes the game engine more efficient, so calculations do not need
     * to be performed for sleeping bodies.
     */
    private val world: World = World(Vector2(0f, -9.8f), true)

    private val player = Player(
            world = world,
            textureFileName = "Player 1.png",
            x = GameInfo.WIDTH.toFloat(),
            y = GameInfo.HEIGHT.toFloat())

    init {
        /*
        // Calculate center coordinates.
        val (worldCenterX, worldCenterY) =
                getCenter(GameInfo.WIDTH.toFloat(), GameInfo.HEIGHT.toFloat())
        val (playerCenterX, playerCenterY) = getCenter(player)

        // Set position to the center of the world.
        player.setPosition(
                worldCenterX - playerCenterX,
                worldCenterY - playerCenterY)
         */
    }

    /**
     * Called when this screen becomes the current screen for a [Game].
     */
    override fun show() {
        // TODO("Not yet implemented")
    }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    override fun render(delta: Float) {
        player.updatePlayer()

        ScreenUtils.clear(1f, 0f, 0f, 1f)

        game.batch.begin()
        // The coordinate system in libGDX starts at the bottom-left corner (0, 0).
        game.batch.draw(background, 0f, 0f)

        // Draw the Player sprite in the center of the world.
        game.batch.draw(player, player.x, player.y)
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

    /**
     * Called when this screen is no longer the current screen for a [Game].
     */
    override fun hide() {
        // TODO("Not yet implemented")
    }

    /**
     * Called when this screen should release all resources.
     */
    override fun dispose() {
        background.dispose()
        player.texture.dispose()
    }
}
