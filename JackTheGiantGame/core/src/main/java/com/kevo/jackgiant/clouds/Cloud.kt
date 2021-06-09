package com.kevo.jackgiant.clouds

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameSprite
import com.kevo.jackgiant.GameInfo

class Cloud(
        world: World,
        assetInfo: AssetInfo
) : GameSprite(world, assetInfo) {

    init {
        setPosition(
                GameInfo.WIDTH / 2f,
                (GameInfo.HEIGHT / 2f) - 130)
        createPhysicsBody()
    }

    override fun getFixtureUserData() = "Cloud"

    override fun getBodyType() = BodyDef.BodyType.StaticBody

    override fun getPhysicsBodyWidth(): Float =
            this.width / GameInfo.PPM.toFloat()
}
