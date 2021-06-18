package com.kevo.jackgiant.player

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameSprite
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.PLAYER_DENSITY
import com.kevo.jackgiant.PLAYER_FRICTION

/**
 * Represents the *Player* character. The new Player instance will be created at
 * the specified [x] and [y] coordinates in the game world, with a texture using
 * the asset `fileName`.
 *
 * @constructor Creates a new Player instance at the specified location.
 *
 * @property world The world in which the `Player` exists.
 */
class Player(
        /**
         * Create the world in the scene where we need physics. For example, we
         * don't need physics in the main menu, or the options menu.
         */
        private val world: World,
        assetInfo: AssetInfo
) : GameSprite(world, assetInfo) {

    override fun getFixtureUserData() = "Player"

    /**
     * The player is affected by gravity and other forces.
     */
    override fun getBodyType() = BodyDef.BodyType.DynamicBody

    override fun getPhysicsBodyWidth(): Float =
            (this.width / 2) / GameInfo.PPM.toFloat()

    override fun getDensity(): Float = PLAYER_DENSITY

    override fun getFriction(): Float = PLAYER_FRICTION

    fun updatePlayer() {
        body.let {
            setPosition(
                    it.position.x * GameInfo.PPM.toFloat(),
                    it.position.y * GameInfo.PPM.toFloat())
        }
    }

    fun movePlayer(movementForce: Float) {
        // Force: Speed over time.
        body.applyForce(Vector2(movementForce, 0f), body.worldCenter, true)
    }

    fun jump(jumpForce: Float) {
        // Linear Impulse: Moves the player immediately.
        body.applyLinearImpulse(Vector2(0f, jumpForce), body.worldCenter, true)
    }
}
