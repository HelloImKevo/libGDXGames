package com.kevo.jackgiant.scenes

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.GameMain

class HighScore(game: GameMain) : BaseScene(game) {

    private var background: Texture = Texture("Backgrounds/High Score BG.png")

    init {
        mainCamera.setToOrtho(false,
                GameInfo.WIDTH.toFloat(),
                GameInfo.HEIGHT.toFloat())

        mainCamera.position.set(
                GameInfo.WIDTH / 2f,
                GameInfo.HEIGHT / 2f, 0f)
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
    }

    /**
     * Called when this screen should release all resources.
     */
    override fun dispose() {
        background.dispose()
    }
}
