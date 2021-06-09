package com.kevo.jackgiant.scenes

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.GameMain
import com.kevo.jackgiant.clouds.Cloud
import com.kevo.jackgiant.player.Player
import kotlin.math.abs

class Gameplay(private val game: GameMain) : Screen {

    private var backgrounds: ArrayList<Sprite> = arrayListOf()
    private var backgroundLastYPosition: Float = 0f

    /**
     * Create a world in which physics bodies are allowed to sleep (this
     * makes the game engine more efficient, so calculations do not need
     * to be performed for sleeping bodies.
     */
    private val world: World = World(Vector2(0f, -2.0f), true)

    /**
     * The *Player* character in our game world.
     */
    private val player = Player(
            world = world,
            textureFileName = "Player/Player 1.png",
            x = GameInfo.WIDTH.toFloat(),
            y = GameInfo.HEIGHT.toFloat() + 250)

    private val cloud = Cloud(
            world = world,
            textureFileName = "Clouds/Cloud 1.png")

    /**
     * The 2D orthographic main game camera that works with the viewport to
     * show the correct world contents to the user.
     */
    private val mainCamera: OrthographicCamera = OrthographicCamera(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat())

    /**
     * Manages a [Camera] and determines how world coordinates are mapped
     * to and from the screen. This is a scaling viewport that uses the
     * `Stretch` scaling so it does not keep the aspect ratio, and the world
     * is scaled to take the whole screen.
     */
    private val gameViewport: Viewport = StretchViewport(
            GameInfo.WIDTH.toFloat(),
            GameInfo.HEIGHT.toFloat(),
            mainCamera)

    /**
     * A 2D orthographic camera instance. Used to render outlines of the
     * physics bodies.
     */
    private val box2DCamera: OrthographicCamera = OrthographicCamera()

    /**
     * Responsible for rendering the contents of the orthographic camera
     * to the screen. Can be used to render outlines of the physics bodies.
     */
    private val debugRenderer: Box2DDebugRenderer = Box2DDebugRenderer()

    init {
        createBackgrounds()

        mainCamera.position.set(
                GameInfo.WIDTH / 2f,
                GameInfo.HEIGHT / 2f, 0f)

        box2DCamera.setToOrtho(false,
                GameInfo.WIDTH.toFloat() / GameInfo.PPM,
                GameInfo.HEIGHT.toFloat() / GameInfo.PPM)
        box2DCamera.position.set(
                GameInfo.WIDTH / 2f,
                GameInfo.HEIGHT / 2f, 0f)
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
            backgroundLastYPosition = abs(background.y)
            backgrounds.add(background)
        }
    }

    private fun drawBackgrounds() {
        for (bg in backgrounds) game.batch.draw(bg, bg.x, bg.y)
    }

    /**
     * Update game world using [dt] "Delta Time" (the time between each frame,
     * usually a very small number).
     */
    private fun update(dt: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.body.let {
                // Force: Speed over time.
                it.applyForce(Vector2(-2f, 0f), it.worldCenter, true)
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.body.let {
                it.applyForce(Vector2(2f, 0f), it.worldCenter, true)
            }
        }

        moveCamera()
        checkBackgroundsOutOfBounds()
    }

    private fun moveCamera() {
        // TODO: The two cameras can probably be combined, may need to call mainCamera.setToOrtho.
        mainCamera.position.y -= 1
    }

    private fun checkBackgroundsOutOfBounds() {
        for (bg in backgrounds) {
            /*
            Check whether the background has scrolled up and out of the view of
            the camera. If it's no longer visible, move it to the bottom the stack:

            +------+
            |      |
            |  BG  |
            |      |
            +------+
            +------+
            |      |
            |  BG  |
            |      |
            +------+
            +------+
            |      |
            |  BG  |
            |      |
            +------+

            We need to add some extra 5 pixel padding to keep up with the movement
            speed of the camera, to achieve the "Infinite Scrolling Background" effect.
             */
            if (bg.y - (bg.height / 2f) - 5f > mainCamera.position.y) {
                val newPosition = bg.height + backgroundLastYPosition
                bg.setPosition(0f, -newPosition)
                backgroundLastYPosition = abs(newPosition)
            }
        }
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
        update(delta)

        player.updatePlayer()

        ScreenUtils.clear(1f, 0f, 0f, 1f)

        game.batch.begin()

        drawBackgrounds()

        // Draw the Player sprite in the center of the world.
        game.batch.draw(player,
                player.x - (player.width / 2),
                player.y - (player.height / 2))

        // Draw the cloud platform.
        game.batch.draw(cloud,
                cloud.x - (cloud.width / 2),
                cloud.y - (cloud.height / 2))

        game.batch.end()

        // Specify what we "see" with our camera.
        game.batch.projectionMatrix = mainCamera.combined
        mainCamera.update()

        // debugRenderer.render(world, box2DCamera.combined)

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
