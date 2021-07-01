package com.kevo.jackgiant

import java.io.File

/**
 * Represents information for a game asset (like a texture file), including
 * the asset [directory] and its [fileName].
 */
class AssetInfo(

        /**
         * The directory path within the `assets` folder, ex: "Buttons/Gameplay".
         * If no directory path is specified, the asset is expected to be located
         * in the root `assets` folder.
         */
        val directory: String? = null,

        /**
         * The name of the texture file, ex: "Player.png".
         */
        val fileName: String,

        /**
         * Optional directory name for the asset animations, ex: "Animations".
         */
        private val animationDir: String? = null,

        /**
         * Optional file name for the animation sprite sheet atlas,
         * ex: "PlayerAnimation.atlas".
         */
        private val atlasFileName: String? = null
) {

    fun getFilePath(): String {
        directory ?: return fileName

        // Note: File.pathSeparator is a Platform Type: String!
        val separator: String? = File.separator
        return directory + (separator ?: "/") + fileName
    }

    fun getAtlasFilePath(): String {
        animationDir ?: return atlasFileName ?: ""

        val separator: String? = File.separator
        return animationDir + (separator ?: "/") + atlasFileName
    }
}
