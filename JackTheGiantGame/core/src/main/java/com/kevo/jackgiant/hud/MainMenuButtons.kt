package com.kevo.jackgiant.hud

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.GameMain
import com.kevo.jackgiant.scenes.Gameplay
import com.kevo.jackgiant.scenes.HighScore
import java.io.File

class MainMenuButtons(game: GameMain) : BaseButtons(game) {

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
        // Set our Stage as the input receiver.
        Gdx.input.inputProcessor = stage

        createAndPositionButtons()

        addAllListeners()

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

    private fun addAllListeners() {
        // Note: The generic Actor.addListener(EventListener) function will emit an event
        // for all events (including mouse hover events). We need to explicitly specify
        // a 'ChangeListener' to receive only 'On Click' events.

        // Standard listener registration using verbose syntax.
        btnPlay.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                game.screen = Gameplay(game)
            }
        })

        // Listener registration using extension function with lambda expression.
        btnHighScore.addChangeListener {
            game.screen = HighScore(game)
        }
        btnOptions.addChangeListener {
            println("Clicked Options")
        }
        btnQuit.addChangeListener {
            println("Clicked Quit")
        }
        btnMusic.addChangeListener {
            println("Clicked Music")
        }
    }
}
