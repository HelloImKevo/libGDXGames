package com.kevo.jackgiant.hud

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.GameMain
import com.kevo.jackgiant.scenes.MainMenu
import java.io.File

class OptionsButtons(game: GameMain) : BaseButtons(game) {

    private val gameViewport: Viewport = FitViewport(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat(),
            OrthographicCamera())

    val stage: Stage = Stage(gameViewport, game.batch)

    private lateinit var imgCheckMark: Image

    private lateinit var btnEasy: ImageButton
    private lateinit var btnMedium: ImageButton
    private lateinit var btnHard: ImageButton
    private lateinit var btnBack: ImageButton

    init {
        // Set our Stage as the input receiver.
        Gdx.input.inputProcessor = stage

        createAndPositionButtons()

        addAllListeners()

        stage.addActor(btnEasy)
        stage.addActor(btnMedium)
        stage.addActor(btnHard)
        stage.addActor(imgCheckMark)
        stage.addActor(btnBack)
    }

    private fun createAndPositionButtons() {
        val sep: String = File.separator

        btnEasy = getImageButton(AssetInfo("Buttons${sep}OptionsMenu", "Easy.png"))
        btnMedium = getImageButton(AssetInfo("Buttons${sep}OptionsMenu", "Medium.png"))
        btnHard = getImageButton(AssetInfo("Buttons${sep}OptionsMenu", "Hard.png"))

        btnBack = getImageButton(AssetInfo("Buttons${sep}OptionsMenu", "Back.png"))

        imgCheckMark = getImage(AssetInfo("Buttons${sep}OptionsMenu", "Check Sign.png"))

        // Positioning.
        btnEasy.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, Align.center)
        btnMedium.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 80, Align.center)
        btnHard.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 160, Align.center)

        btnBack.setPosition(btnBack.width - 10, btnBack.height - 10, Align.bottomLeft)

        // TODO: Remove this once 'Game Difficulty' is implemented.
        imgCheckMark.setPosition(GameInfo.WIDTH / 2f + 76, btnMedium.y + 13, Align.bottomLeft)
    }

    private fun addAllListeners() {
        btnEasy.addChangeListener {
            imgCheckMark.y = btnEasy.y + 13
        }
        btnMedium.addChangeListener {
            imgCheckMark.y = btnMedium.y + 13
        }
        btnHard.addChangeListener {
            imgCheckMark.y = btnHard.y + 13
        }
        btnBack.addChangeListener {
            game.screen = MainMenu(game)
        }
    }
}
