package com.kevo.testgame.clouds

import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.kevo.testgame.GameSprite
import com.kevo.testgame.helpers.GameInfo

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

    override fun getBodyType() = BodyDef.BodyType.StaticBody

    override fun getPhysicsBodyWidth(): Float =
            this.width / GameInfo.PPM.toFloat()
}
