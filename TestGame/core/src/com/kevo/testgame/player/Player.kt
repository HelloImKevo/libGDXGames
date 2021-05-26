package com.kevo.testgame.player

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

/**
 * Represents the *Player* character. The new Player instance will be created at
 * the specified [x] and [y] coordinates in the game world, with a texture using
 * the [textureFileName].
 *
 * @constructor Creates a new Player instance at the specified location.
 *
 * @param textureFileName The name of the texture file, ex: "Player.png".
 * @param x The X-coordinate where the `Player` should be placed.
 * @param y The Y-coordinate where the `Player` should be placed.
 */
class Player(
        textureFileName: String,
        x: Float,
        y: Float
) : Sprite(Texture(textureFileName)) {

    init {
        setPosition((x - width) / 2, (y - height) / 2)
    }
}
