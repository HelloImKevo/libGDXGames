package com.kevo.jackgiant

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class AssetInfoTest {

    @Test
    fun `getFilePath returns full file path with extension`() {
        val assetInfo = AssetInfo("Buttons/Gameplay", "Menu.png")
        val filePath: String = assetInfo.getFilePath()
        assertNotNull(filePath)
        // TODO: Add support for more OS environments (I think this will fail on Windows)
        assertEquals("Buttons/Gameplay/Menu.png", filePath)
    }

    @Test
    fun `getFilePath returns file name when directory is null`() {
        val assetInfo = AssetInfo(fileName = "Player.png")
        val filePath: String = assetInfo.getFilePath()
        assertNotNull(filePath)
        assertEquals("Player.png", filePath)
    }
}
