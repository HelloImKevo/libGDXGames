package com.kevo.jackgiant.clouds

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.World
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameInfo

class CloudsController(private val world: World) {

    private val clouds: ArrayList<Cloud> = arrayListOf()

    init {
        createClouds()
    }

    private fun createClouds() {
        for (i in 0..1) {
            clouds.add(Cloud(world, AssetInfo("Clouds", "Dark Cloud.png"), Cloud.Type.DARK))
        }

        var index = 1

        for (i in 0..5) {
            clouds.add(Cloud(world, AssetInfo("Clouds", "Cloud $index.png"), Cloud.Type.STANDARD))
            index++
            if (index > 3) {
                index = 1
            }
        }

        clouds.shuffle()
    }

    fun positionClouds() {
        // Make sure the first cloud is never a "Dark Cloud" (which hurts the Player).
        while (Cloud.Type.DARK == clouds[0].type) {
            clouds.shuffle()
        }

        val tempX = GameInfo.WIDTH / 2f
        var positionY = GameInfo.HEIGHT / 2f

        for (c in clouds) {
            c.setSpritePosition(tempX, positionY)
            positionY -= DISTANCE_BETWEEN_CLOUDS
        }
    }

    fun drawClouds(batch: SpriteBatch) {
        for (c in clouds) {
            val centerPoints: Pair<Float, Float> = c.getCenterPoints()
            batch.draw(c, centerPoints.first, centerPoints.second)
        }
    }

    companion object {

        private const val DISTANCE_BETWEEN_CLOUDS: Float = 250f
    }
}
