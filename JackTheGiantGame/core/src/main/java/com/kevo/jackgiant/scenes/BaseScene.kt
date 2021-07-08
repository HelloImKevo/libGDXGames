package com.kevo.jackgiant.scenes

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.GameMain

abstract class BaseScene(protected val game: GameMain) : Screen {

    protected val mainCamera: OrthographicCamera = OrthographicCamera(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat())

    protected val gameViewport: Viewport = StretchViewport(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat(),
            mainCamera)

    /**
     * Called when this screen becomes the current screen for a [Game].
     */
    override fun show() {
        // TODO("Not yet implemented")
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
}
