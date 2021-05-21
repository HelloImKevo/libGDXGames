package com.kevo.testgame

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

class PointHelper {

    companion object {

        @JvmStatic
        fun getCenter(width: Float, height: Float): Pair<Float, Float> {
            return Pair(width / 2.0f, height / 2.0f)
        }

        @JvmStatic
        fun getCenter(sprite: Sprite): Pair<Float, Float> {
            return Pair(sprite.width / 2.0f, sprite.height / 2.0f)
        }

        @JvmStatic
        fun getCenter(texture: Texture): Pair<Float, Float> {
            return Pair(texture.width / 2.0f, texture.height / 2.0f)
        }
    }
}
