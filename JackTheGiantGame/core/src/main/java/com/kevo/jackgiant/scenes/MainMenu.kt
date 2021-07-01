package com.kevo.jackgiant.scenes

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.GameMain
import com.kevo.jackgiant.hud.MainMenuButtons

class MainMenu(private val game: GameMain) : Screen {

    private var background: Texture = Texture("Backgrounds/Menu BG.png")

    private val mainCamera: OrthographicCamera = OrthographicCamera(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat())

    private val gameViewport: Viewport = StretchViewport(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat(),
            mainCamera)

    private var buttons: MainMenuButtons

    init {
        mainCamera.setToOrtho(false,
                GameInfo.WIDTH.toFloat(),
                GameInfo.HEIGHT.toFloat())

        mainCamera.position.set(
                GameInfo.WIDTH / 2f,
                GameInfo.HEIGHT / 2f, 0f)

        buttons = MainMenuButtons(game)
    }

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

        game.batch.draw(background, 0f, 0f)

        game.batch.end()

        game.batch.projectionMatrix = buttons.stage.camera.combined
        buttons.stage.draw()
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

    /** Called when this screen should release all resources.  */
    override fun dispose() {
        background.dispose()
        buttons.stage.dispose()
    }
}
