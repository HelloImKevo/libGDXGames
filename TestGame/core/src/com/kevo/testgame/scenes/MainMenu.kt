package com.kevo.testgame.scenes

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.Manifold
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.ScreenUtils
import com.kevo.testgame.GameMain
import com.kevo.testgame.clouds.Cloud
import com.kevo.testgame.helpers.GameInfo
import com.kevo.testgame.player.Player

class MainMenu(private val game: GameMain) : Screen, ContactListener {

    private val background = Texture("Game BG.png")

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
            textureFileName = "Player 1.png",
            x = GameInfo.WIDTH.toFloat(),
            y = GameInfo.HEIGHT.toFloat() + 250)

    private val cloud = Cloud(
            world = world,
            textureFileName = "Cloud 1.png")

    /**
     * A 2D orthographic camera instance. Used to render outlines of the
     * physics bodies.
     */
    private val box2DCamera: OrthographicCamera = OrthographicCamera()

    /**
     * Responsible for rendering the contents of the orthographic camera
     * to the screen.
     */
    private val debugRenderer: Box2DDebugRenderer = Box2DDebugRenderer()

    init {
        box2DCamera.setToOrtho(false,
                GameInfo.WIDTH.toFloat() / GameInfo.PPM,
                GameInfo.HEIGHT.toFloat() / GameInfo.PPM)
        box2DCamera.position.set(
                GameInfo.WIDTH / 2f,
                GameInfo.HEIGHT / 2f, 0f)

        world.setContactListener(this)
        /*
        // Calculate center coordinates.
        val (worldCenterX, worldCenterY) =
                getCenter(GameInfo.WIDTH.toFloat(), GameInfo.HEIGHT.toFloat())
        val (playerCenterX, playerCenterY) = getCenter(player)

        // Set position to the center of the world.
        player.setPosition(
                worldCenterX - playerCenterX,
                worldCenterY - playerCenterY)
         */
    }

    /**
     * Update game world using [dt] "Delta Time".
     */
    private fun update(dt: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            // Linear Impulse: Moves the player immediately.
            // player.getBody().applyLinearImpulse(
            //         Vector2(-0.1f, 0f),
            //         player.getBody().worldCenter,
            //         // Wake up the body.
            //         true)

            // Force: Speed over time.
            player.body.let {
                it.applyForce(Vector2(-2f, 0f), it.worldCenter, true)
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            // player.getBody().applyLinearImpulse(
            //         Vector2(0.1f, 0f),
            //         player.getBody().worldCenter,
            //         // Wake up the body.
            //         true)

            player.body.let {
                it.applyForce(Vector2(2f, 0f), it.worldCenter, true)
            }
        }
    }

    //region Screen Implementation

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
        update(delta)

        player.updatePlayer()

        ScreenUtils.clear(1f, 0f, 0f, 1f)

        game.batch.begin()
        // The coordinate system in libGDX starts at the bottom-left corner (0, 0).
        game.batch.draw(background, 0f, 0f)

        // Draw the Player sprite in the center of the world.
        game.batch.draw(player,
                player.x - (player.width / 2),
                player.y - (player.height / 2))

        // Draw the cloud platform.
        game.batch.draw(cloud,
                cloud.x - (cloud.width / 2),
                cloud.y - (cloud.height / 2))

        game.batch.end()

        debugRenderer.render(world, box2DCamera.combined)

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
        player.texture.dispose()
    }

    //endregion

    //region ContactListener Implementation

    /** Called when two fixtures begin to touch. */
    override fun beginContact(contact: Contact?) {
        contact ?: return

        contact.fixtureA?.let { println("FixtureA Contact : " + it.userData) }
        contact.fixtureB?.let { println("FixtureB Contact : " + it.userData) }

        val playerBody: Fixture
        val secondBody: Fixture
        contact.fixtureA?.let {
            if (it.userData == player.getFixtureUserData()) {
                playerBody = it
                secondBody = contact.fixtureB
            } else {
                playerBody = contact.fixtureB
                secondBody = it
            }
        }
    }

    /** Called when two fixtures cease to touch. */
    override fun endContact(contact: Contact?) {
        // TODO("Not yet implemented")
    }

    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
        // TODO("Not yet implemented")
    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
        // TODO("Not yet implemented")
    }

    //endregion
}
