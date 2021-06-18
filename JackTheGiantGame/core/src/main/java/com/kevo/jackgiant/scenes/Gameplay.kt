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
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.CAMERA_DEFAULT_SPEED
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.GameMain
import com.kevo.jackgiant.GameSprite
import com.kevo.jackgiant.PLAYER_JUMP_FORCE
import com.kevo.jackgiant.PLAYER_MOVEMENT_FORCE
import com.kevo.jackgiant.WORLD_GRAVITY
import com.kevo.jackgiant.clouds.CloudsController
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
    private val world: World = World(Vector2(0f, -WORLD_GRAVITY), true)

    /**
     * The *Player* character in our game world.
     */
    private val player = Player(
            world = world,
            assetInfo = AssetInfo("Player", "Player 1.png"))

    private val cloudsController = CloudsController(world)

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

        // Create the player below the top screen bounds.
        cloudsController.positionThePlayer(player)
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

    private fun drawSprite(sprite: GameSprite) {
        val centerPoints: Pair<Float, Float> = sprite.getCenterPoints()
        game.batch.draw(sprite, centerPoints.first, centerPoints.second)
    }

    private fun handleInput(dt: Float) {
        when {
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> {
                player.movePlayer(-PLAYER_MOVEMENT_FORCE)
            }
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> {
                player.movePlayer(PLAYER_MOVEMENT_FORCE)
            }
            Gdx.input.isKeyJustPressed(Input.Keys.SPACE) -> {
                player.jump(PLAYER_JUMP_FORCE)
            }
        }
    }

    /**
     * Update game world using [dt] "Delta Time" (the time between each frame,
     * usually a very small number).
     */
    private fun update(dt: Float) {
        handleInput(dt)

        moveCamera()
        checkBackgroundsOutOfBounds()
        cloudsController.setCameraY(mainCamera.position.y)
        // TODO: Optimize this logic - it does not need to be called every render pass.
        cloudsController.createAndArrangeNewClouds()
    }

    private fun moveCamera() {
        // TODO: The two cameras can probably be combined, may need to call mainCamera.setToOrtho.
        mainCamera.position.y -= CAMERA_DEFAULT_SPEED
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
            |      |
            |  BG  |
            |      |
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

        ScreenUtils.clear(1f, 0f, 0f, 1f)

        game.batch.begin()

        drawBackgrounds()

        // Draw the Player sprite in the center of the world.
        drawSprite(player)

        // Draw the cloud platform.
        cloudsController.drawClouds(game.batch)

        game.batch.end()

        // The debug renderer must be triggered before updating the main camera.
        debugRenderer.render(world, box2DCamera.combined)

        // Specify what we "see" with our camera.
        game.batch.projectionMatrix = mainCamera.combined
        mainCamera.update()

        player.updatePlayer()

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
