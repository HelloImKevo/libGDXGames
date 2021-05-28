package com.kevo.testgame.player

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.kevo.testgame.GameSprite
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
) : GameSprite(world, textureFileName) {

    init {
        setPosition((x - width) / 2, (y - height) / 2)
        createPhysicsBody()
    }

    override fun getFixtureUserData() = "Player"

    override fun getBodyType() = BodyDef.BodyType.DynamicBody

    override fun getPhysicsBodyWidth(): Float =
            (this.width / 2) / GameInfo.PPM.toFloat()

    fun updatePlayer() {
        body.let {
            setPosition(
                    it.position.x * GameInfo.PPM.toFloat(),
                    it.position.y * GameInfo.PPM.toFloat())
        }
    }
}
