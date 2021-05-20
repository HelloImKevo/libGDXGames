package com.kevo.testgame

import com.badlogic.gdx.graphics.Texture

class PointHelper {

    companion object {

        @JvmStatic
        fun getCenter(width: Float, height: Float): Pair<Float, Float> {
            return Pair(width / 2.0f, height / 2.0f)
        }

        @JvmStatic
        fun getCenter(texture: Texture): Pair<Float, Float> {
            return Pair(texture.width / 2.0f, texture.height / 2.0f)
        }
    }
}
