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

        /**
         * Create the world in the scene where we need physics. For example, we
         * don't need physics in the main menu, or the options menu.
         */
        protected val world: World,

        /**
         * Information about the asset resources.
         */
        private val assetInfo: AssetInfo
) : Sprite(Texture(assetInfo.getFilePath())) {

    /**
     * The actual body of the sprite. We will want to move the body and manipulate
     * the sprite. The body can have shape, friction, mass, gravity, rotation, and
     * so on.
     */
    lateinit var body: Body

    abstract fun getFixtureUserData(): String

    abstract fun getBodyType(): BodyDef.BodyType

    abstract fun getDensity(): Float

    abstract fun getFriction(): Float

    open fun getPhysicsBodyWidth(): Float =
            (this.width / 2) / GameInfo.PPM.toFloat()

    open fun getPhysicsBodyHeight(): Float =
            (this.height / 2) / GameInfo.PPM.toFloat()

    private fun createPhysicsBody() {
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
        // Do not allow our sprites to be rotated by the physics engine.
        body.isFixedRotation = true

        // Shape of the physics body.
        val shape = PolygonShape()
        shape.setAsBox(getPhysicsBodyWidth(), getPhysicsBodyHeight())

        val fixtureDef = FixtureDef()
        fixtureDef.shape = shape
        // The mass of the body.
        fixtureDef.density = getDensity()
        // Make our player stick to the surface (rather than slide).
        fixtureDef.friction = getFriction()

        val fixture: Fixture = body.createFixture(fixtureDef)
        fixture.userData = getFixtureUserData()

        // Free up memory (system resources).
        shape.dispose()
    }

    fun setSpritePosition(x: Float, y: Float) {
        setPosition(x, y)
        createPhysicsBody()
    }

    /**
     * Returns the calculated center points
     */
    fun getCenterPoints(): Pair<Float, Float> = Pair(x - (width / 2), y - (height / 2))

    /**
     * Returns the asset's name (without the file extension), ex: "Dark Cloud"
     */
    fun getAssetName(): String = assetInfo.fileName.substringBefore(".")
}
