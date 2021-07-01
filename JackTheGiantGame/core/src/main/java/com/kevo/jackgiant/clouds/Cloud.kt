package com.kevo.jackgiant.clouds

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.CLOUD_DENSITY
import com.kevo.jackgiant.CLOUD_FRICTION
import com.kevo.jackgiant.GameSprite
import com.kevo.jackgiant.GameInfo

class Cloud(
        world: World,
        assetInfo: AssetInfo,
        val type: Type
) : GameSprite(world, assetInfo) {

    enum class Type {

        /**
         * A standard cloud platform.
         */
        STANDARD,

        /**
         * A harmful cloud that hurts the player.
         */
        DARK
    }

    override fun getFixtureUserData() = "Cloud"

    /**
     * Clouds are not affected by gravity or other forces.
     */
    override fun getBodyType() = BodyDef.BodyType.StaticBody

    override fun getPhysicsBodyWidth(): Float =
            // Use 90% of the texture's width.
            ((this.width / 2) / GameInfo.PPM.toFloat()) * 0.9f

    override fun getPhysicsBodyHeight(): Float =
            // Use 80% of the texture's height.
            ((this.height / 2) / GameInfo.PPM.toFloat()) * 0.8f

    override fun getDensity(): Float = CLOUD_DENSITY

    override fun getFriction(): Float = CLOUD_FRICTION
}
