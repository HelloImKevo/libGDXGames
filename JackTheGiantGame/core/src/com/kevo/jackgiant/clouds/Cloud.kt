package com.kevo.jackgiant.clouds

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.kevo.jackgiant.GameSprite
import com.kevo.jackgiant.GameInfo

class Cloud(
        world: World,
        textureFileName: String
) : GameSprite(world, textureFileName) {

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
