package com.kevo.testgame.scenes

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.ScreenUtils
import com.kevo.testgame.GameMain
import com.kevo.testgame.PointHelper.Companion.getCenter
import com.kevo.testgame.helpers.GameInfo

class MainMenu(private val game: GameMain) : Screen {

    private val background = Texture("Game BG.png")
    private val player = Sprite(Texture("Player 1.png"))

    init {
        // Calculate center coordinates.
        val (worldCenterX, worldCenterY) =
                getCenter(GameInfo.WIDTH.toFloat(), GameInfo.HEIGHT.toFloat())
        val (playerCenterX, playerCenterY) = getCenter(player)

        // Set position to the center of the world.
        player.setPosition(
                worldCenterX - playerCenterX,
                worldCenterY - playerCenterY)
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
        ScreenUtils.clear(1f, 0f, 0f, 1f)

        game.batch.begin()
        // The coordinate system in libGDX starts at the bottom-left corner (0, 0).
        game.batch.draw(background, 0f, 0f)

        // Nudge the player sprite on each frame.
        player.setPosition(player.x - 1, player.y)

        // Draw the Player sprite in the center of the world.
        game.batch.draw(player, player.x, player.y)
        game.batch.end()
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
    }
}
