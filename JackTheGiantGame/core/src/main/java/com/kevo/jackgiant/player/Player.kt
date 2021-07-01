package com.kevo.jackgiant.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array
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
        world: World,
        assetInfo: AssetInfo
) : GameSprite(world, assetInfo) {

    var isWalking: Boolean = false

    /**
     * The texture atlas for the animation sprite sheet (located in the `assets` dir).
     * Ex: "PlayerAnimation/PlayerSpriteSheet.atlas".
     */
    private var atlas = TextureAtlas(assetInfo.getAtlasFilePath())
    private var animation: Animation<*>? = null
    private var elapsedTime: Float = 0f
    private var isAirborne: Boolean = false

    override fun getFixtureUserData() = "Player"

    /**
     * The player is affected by gravity and other forces.
     */
    override fun getBodyType() = BodyDef.BodyType.DynamicBody

    override fun getPhysicsBodyWidth(): Float =
            // Use 80% of the texture's width.
            ((this.width / 2) / GameInfo.PPM.toFloat()) * 0.8f

    override fun getDensity(): Float = PLAYER_DENSITY

    override fun getFriction(): Float = PLAYER_FRICTION

    fun drawPlayerIdle(batch: SpriteBatch) {
        if (!isWalking) {
            // Draw the player stationary sprite image.
            val centerPoints: Pair<Float, Float> = getCenterPoints()
            batch.draw(this, centerPoints.first, centerPoints.second)
        }
    }

    fun drawPlayerAnimation(batch: SpriteBatch) {
        if (isWalking) {
            elapsedTime += Gdx.graphics.deltaTime

            // Check whether we need to flip the sprites to make the player
            // face left.
            val frames: Array<TextureAtlas.AtlasRegion> = atlas.regions
            for (frame in frames) {
                if (body.linearVelocity.x < 0 && !frame.isFlipX) {
                    // Flip it to face left.
                    frame.flip(true, false)
                } else if (body.linearVelocity.x > 0 && frame.isFlipX) {
                    // Flip it back to its original state (facing right).
                    frame.flip(true, false)
                }
            }
            animation = Animation<TextureRegion>(1f / 10f, atlas.regions)

            animation?.let {
                val centerPoints: Pair<Float, Float> = getCenterPoints()
                batch.draw(
                        it.getKeyFrame(elapsedTime, true) as TextureRegion,
                        centerPoints.first, centerPoints.second)
            }
        }
    }

    fun updatePlayer() {
        body.let {
            setPosition(
                    it.position.x * GameInfo.PPM.toFloat(),
                    it.position.y * GameInfo.PPM.toFloat())
        }

        isAirborne = body.linearVelocity.y > 0.1f || body.linearVelocity.y < -0.1f
    }

    fun movePlayer(movementForce: Float) {
        // Handle sprite flipping.
        if (movementForce < 0 && !isFlipX) {
            // Flip it to face left.
            flip(true, false)
        } else if (movementForce > 0 && isFlipX) {
            // Flip it back to its original state (facing right).
            flip(true, false)
        }

        isWalking = true
        body.setLinearVelocity(movementForce, body.linearVelocity.y)
    }

    fun jump(jumpForce: Float) {
        // The player does not have the "Double-Jump" ability.
        if (isAirborne) return

        isWalking = true
        // Linear Impulse: Moves the player immediately.
        body.applyLinearImpulse(Vector2(0f, jumpForce), body.worldCenter, true)
    }
}
