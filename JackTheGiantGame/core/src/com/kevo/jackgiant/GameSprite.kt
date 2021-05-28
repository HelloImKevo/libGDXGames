package com.kevo.jackgiant

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World

abstract class GameSprite(
        private val world: World,
        textureFileName: String
) : Sprite(Texture(textureFileName)) {

    /**
     * The actual body of the sprite. We will want to move the body and manipulate
     * the sprite. The body can have shape, friction, mass, gravity, rotation, and
     * so on.
     */
    lateinit var body: Body

    abstract fun getFixtureUserData(): String

    abstract fun getBodyType(): BodyDef.BodyType

    abstract fun getPhysicsBodyWidth(): Float

    protected fun createPhysicsBody() {
        // Define whether the body is dynamic, static, or kinematic.
        val bodyDef = BodyDef()

        /*
        Body Definition Types:
        - A static body is not affected by gravity or other forces.
        - A kinematic body is not affected by gravity, but is affected by other forces.
        - A dynamic body is affected by gravity and other forces.
         */
        bodyDef.type = getBodyType()
        bodyDef.position.set(
                this.x / GameInfo.PPM,
                this.y / GameInfo.PPM)

        body = world.createBody(bodyDef)

        body.let {
            // Shape of the physics body.
            val shape = PolygonShape()
            shape.setAsBox(
                    getPhysicsBodyWidth(),
                    (this.height / 2) / GameInfo.PPM.toFloat())

            val fixtureDef = FixtureDef()
            fixtureDef.shape = shape
            fixtureDef.density = 1f

            val fixture: Fixture = it.createFixture(fixtureDef)
            fixture.userData = getFixtureUserData()

            // Free up memory (system resources).
            shape.dispose()
        }
    }
}
