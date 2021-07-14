package com.kevo.jackgiant.hud

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.GameMain
import com.kevo.jackgiant.scenes.MainMenu
import java.io.File

class HighScoreButtons(game: GameMain) : BaseButtons(game) {

    private val gameViewport: Viewport = FitViewport(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat(),
            OrthographicCamera())

    val stage: Stage = Stage(gameViewport, game.batch)

    private lateinit var lblScore: Label
    private lateinit var lblCoin: Label
    private lateinit var btnBack: ImageButton

    init {
        // Set our Stage as the input receiver.
        Gdx.input.inputProcessor = stage

        createAndPositionButtons()

        addAllListeners()

        stage.addActor(lblScore)
        stage.addActor(lblCoin)
        stage.addActor(btnBack)
    }

    private fun createAndPositionButtons() {
        val sep: String = File.separator
        btnBack = getImageButton(AssetInfo("Buttons${sep}OptionsMenu", "Back.png"))

        val generator = FreeTypeFontGenerator(Gdx.files.internal("Fonts${sep}blow.ttf"))
        val param = FreeTypeFontGenerator.FreeTypeFontParameter()
        param.size = 40

        val scoreFont: BitmapFont = generator.generateFont(param)
        val coinFont: BitmapFont = generator.generateFont(param)

        lblScore = Label("100", Label.LabelStyle(scoreFont, Color.WHITE))
        lblCoin = Label("100", Label.LabelStyle(coinFont, Color.WHITE))

        // Positioning.
        btnBack.setPosition(btnBack.width - 10, btnBack.height - 10, Align.bottomLeft)
        lblScore.setPosition(GameInfo.WIDTH / 2f - 40, GameInfo.HEIGHT / 2f - 120, Align.center)
        lblCoin.setPosition(GameInfo.WIDTH / 2f - 40, GameInfo.HEIGHT / 2f - 220, Align.center)
    }

    private fun addAllListeners() {
        btnBack.addChangeListener {
            game.screen = MainMenu(game)
        }
    }
}
