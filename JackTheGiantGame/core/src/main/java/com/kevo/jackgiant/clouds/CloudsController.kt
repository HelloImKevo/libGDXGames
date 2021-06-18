package com.kevo.jackgiant.clouds

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.World
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameInfo
import com.kevo.jackgiant.player.Player
import java.util.Random
import kotlin.collections.ArrayList

class CloudsController(private val world: World) {

    private val clouds: ArrayList<Cloud> = arrayListOf()

    private var minX: Float = 0f
    private var maxX: Float = 0f
    private var lastCloudPositionY: Float = 0f

    private var cameraY: Float = 0f

    private val random = Random()

    init {
        minX = (GameInfo.WIDTH / 2f) - 120f
        maxX = (GameInfo.WIDTH / 2f) + 120f

        createClouds()
        positionClouds(firstTimeArranging = true)
    }

    private fun createClouds() {
        while (countCloudType(clouds, Cloud.Type.DARK) < 2) {
            clouds.add(Cloud(world, AssetInfo("Clouds", "Dark Cloud.png"), Cloud.Type.DARK))
        }

        var index = 1

        while (countCloudType(clouds, Cloud.Type.STANDARD) < 6) {
            clouds.add(Cloud(world, AssetInfo("Clouds", "Cloud $index.png"), Cloud.Type.STANDARD))
            index++
            // We only have 3 different standard cloud textures.
            if (index > 3) {
                index = 1
            }
        }

        clouds.shuffle()
    }

    private fun countCloudType(clouds: ArrayList<Cloud>, type: Cloud.Type): Int {
        return clouds.sumBy { if (it.type == type) 1 else 0 }
    }

    /**
     * Arranges the clouds to make sure the first cloud is never a Dark Cloud,
     * and there are never two Dark Clouds in a row.
     */
    private fun enforceCloudDifficulty() {
        // Make sure the first cloud is never a "Dark Cloud" (which hurts the Player).
        while (Cloud.Type.DARK == clouds[0].type) {
            clouds.shuffle()
        }
        // TODO: Make sure there are never two Dark Clouds in a row.
    }

    private fun positionClouds(firstTimeArranging: Boolean) {
        enforceCloudDifficulty()

        // Determine whether the cloud should be on the right-side or left-side
        // of the screen. 0 = Right, 1 = Left.
        var controlX = 0

        var positionY: Float =
                if (firstTimeArranging) GameInfo.HEIGHT / 2f else lastCloudPositionY

        for (c in clouds) {
            // Check whether this is a brand new cloud instance (which has not been
            // assigned a position).
            if (c.x == 0f && c.y == 0f) {
                var randomXPos = 0f

                if (0 == controlX) {
                    randomXPos = randomBetweenNumbers(maxX - 50, maxX)
                    controlX = 1
                } else if (1 == controlX) {
                    randomXPos = randomBetweenNumbers(minX + 50, minX)
                    controlX = 0
                }

                c.setSpritePosition(randomXPos, positionY)
                positionY -= DISTANCE_BETWEEN_CLOUDS
                lastCloudPositionY = positionY
            }
        }
    }

    fun drawClouds(batch: SpriteBatch) {
        for (c in clouds) {
            val centerPoints: Pair<Float, Float> = c.getCenterPoints()
            batch.draw(c, centerPoints.first, centerPoints.second)
        }
    }

    fun createAndArrangeNewClouds() {
        val iterator = clouds.iterator()
        while (iterator.hasNext()) {
            val cloud = iterator.next()
            // Check whether our cloud is fully out of view of the camera
            // (ie, it has scrolled past the top of the screen).
            val adjustedYPos = (cloud.y - (GameInfo.HEIGHT / 2f)) - 20f
            if (adjustedYPos > cameraY) {
                cloud.texture.dispose()
                iterator.remove()
            }
        }

        // When we get down to 4 clouds remaining, create additional clouds.
        if (clouds.size <= 4) {
            createClouds()
            positionClouds(firstTimeArranging = false)
        }
    }

    fun setCameraY(cameraY: Float) {
        this.cameraY = cameraY
    }

    fun positionThePlayer(player: Player) {
        player.setSpritePosition(clouds[0].x, clouds[0].y + 100)
    }

    private fun randomBetweenNumbers(min: Float, max: Float): Float {
        // The nextFloat() function returns [0.0f - 1.0f]
        // So we need to multiply it by the difference and add the minimum.
        // Ex: 0.999 * (500 - 100) + 100 = 499.6
        // Ex: 0.501 * (500 - 100) + 100 = 300.4
        return (random.nextFloat() * (max - min)) + min
    }

    companion object {

        private const val DISTANCE_BETWEEN_CLOUDS: Float = 250f
    }
}
