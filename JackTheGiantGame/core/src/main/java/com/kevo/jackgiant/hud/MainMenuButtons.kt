package com.kevo.jackgiant.hud

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.GameMain
import java.io.File

class MainMenuButtons(game: GameMain) {

    private val gameViewport: Viewport = FitViewport(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat(),
            OrthographicCamera())

    val stage: Stage = Stage(gameViewport, game.batch)

    private lateinit var btnPlay: ImageButton
    private lateinit var btnHighScore: ImageButton
    private lateinit var btnOptions: ImageButton
    private lateinit var btnQuit: ImageButton
    private lateinit var btnMusic: ImageButton

    init {
        createAndPositionButtons()

        stage.addActor(btnPlay)
        stage.addActor(btnHighScore)
        stage.addActor(btnOptions)
        stage.addActor(btnQuit)
        stage.addActor(btnMusic)
    }

    private fun createAndPositionButtons() {
        val sep: String = File.separator
        btnPlay = getImageButton(AssetInfo("Buttons${sep}MainMenu", "Start Game.png"))
        btnHighScore = getImageButton(AssetInfo("Buttons${sep}MainMenu", "High Score.png"))
        btnOptions = getImageButton(AssetInfo("Buttons${sep}MainMenu", "Options.png"))
        btnQuit = getImageButton(AssetInfo("Buttons${sep}MainMenu", "Quit.png"))
        btnMusic = getImageButton(AssetInfo("Buttons${sep}MainMenu", "Music Off.png"))

        btnPlay.setPosition(GameInfo.WIDTH / 2f - 80, GameInfo.HEIGHT / 2f + 50, Align.center)
        btnHighScore.setPosition(GameInfo.WIDTH / 2f - 60, GameInfo.HEIGHT / 2f - 20, Align.center)
        btnOptions.setPosition(GameInfo.WIDTH / 2f - 40, GameInfo.HEIGHT / 2f - 90, Align.center)
        btnQuit.setPosition(GameInfo.WIDTH / 2f - 20, GameInfo.HEIGHT / 2f - 160, Align.center)
        btnMusic.setPosition(GameInfo.WIDTH - 13f, 13f, Align.bottomRight)
    }

    private fun getImageButton(assetInfo: AssetInfo): ImageButton =
            ImageButton(SpriteDrawable(Sprite(Texture(assetInfo.getFilePath()))))

}
