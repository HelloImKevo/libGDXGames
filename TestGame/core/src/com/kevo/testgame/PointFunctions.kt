@file:JvmName("PointFunctions")

package com.kevo.testgame

import com.badlogic.gdx.graphics.g2d.Sprite

fun getCenter(width: Float, height: Float): Pair<Float, Float> {
    return Pair(width / 2f, height / 2f)
}

fun getCenter(sprite: Sprite): Pair<Float, Float> {
    return Pair(sprite.width / 2f, sprite.height / 2f)
}
