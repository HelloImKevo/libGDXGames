package com.kevo.jackgiant.hud

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable
import com.kevo.jackgiant.AssetInfo
import com.kevo.jackgiant.GameMain

abstract class BaseButtons(protected val game: GameMain) {

    protected fun getImageButton(assetInfo: AssetInfo): ImageButton =
            ImageButton(SpriteDrawable(Sprite(Texture(assetInfo.getFilePath()))))

    /**
     * [ImageButton] extension function for Java interoperability (uses an explicit
     * [ChangeListener] type, instead of the generic [EventListener] interface).
     */
    protected inline fun ImageButton.addChangeListener(crossinline onClick: () -> Unit) {
        addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                onClick()
            }
        })
    }
}
