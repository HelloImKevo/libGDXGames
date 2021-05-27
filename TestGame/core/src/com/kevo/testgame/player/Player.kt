package com.kevo.testgame.player

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import com.kevo.testgame.helpers.GameInfo

/**
 * Represents the *Player* character. The new Player instance will be created at
 * the specified [x] and [y] coordinates in the game world, with a texture using
 * the [textureFileName].
 *
 * @constructor Creates a new Player instance at the specified location.
 *
 * @property world The world in which the `Player` exists.
 *
 * @param textureFileName The name of the texture file, ex: "Player.png".
 * @param x The X-coordinate where the `Player` should be placed.
 * @param y The Y-coordinate where the `Player` should be placed.
 */
class Player(
        /**
         * Create the world in the scene where we need physics. For example, we
         * don't need physics in the main menu, or the options menu.
         */
        private val world: World,
        textureFileName: String,
        x: Float,
        y: Float
) : Sprite(Texture(textureFileName)) {

    /**
     * The actual body of the player. We will want to move the body and manipulate
     * the player. The body can have shape, friction, mass, gravity, rotation, and
     * so on.
     */
    private var body: Body? = null

    init {
        setPosition((x - width) / 2, (y - height) / 2)
        createPhysicsBody()
    }

    private fun createPhysicsBody() {
        // Define whether the body is dynamic, static, or kinematic.
        val bodyDef = BodyDef()

        /*
        Body Definition Types:
        - A static body is not affected by gravity or other forces.
        - A kinematic body is not affected by gravity, but is affected by other forces.
        - A dynamic body is affected by gravity and other forces.
         */
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(
                this.x / GameInfo.PPM,
                this.y / GameInfo.PPM)

        body = world.createBody(bodyDef)

        body?.let {
            // Shape of the physics body.
            val shape = PolygonShape()
            shape.setAsBox(
                    (this.width / 2) / GameInfo.PPM.toFloat(),
                    (this.height / 2) / GameInfo.PPM.toFloat())

            val fixtureDef = FixtureDef()
            fixtureDef.shape = shape
            fixtureDef.density = 1f

            val fixture: Fixture = it.createFixture(fixtureDef)

            // Free up memory (system resources).
            shape.dispose()
        }
    }

    fun updatePlayer() {
        body?.let {
            setPosition(
                    it.position.x * GameInfo.PPM.toFloat(),
                    it.position.y * GameInfo.PPM.toFloat())
        }
    }

    // TODO: Improve nullability of the Body member.
    fun getBody(): Body = this.body!!
}
