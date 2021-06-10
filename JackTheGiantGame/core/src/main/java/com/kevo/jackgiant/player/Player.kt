package com.kevo.jackgiant.player

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameSprite
import com.kevo.jackgiant.GameInfo

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

    fun updatePlayer() {
        body.let {
            setPosition(
                    it.position.x * GameInfo.PPM.toFloat(),
                    it.position.y * GameInfo.PPM.toFloat())
        }
    }
}
