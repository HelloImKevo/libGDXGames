package com.kevo.jackgiant.desktop

import org.junit.Test
import java.io.File

class ProjectDirectoryStructureTest {

    @Test
    fun `log project working directory structure`() {
        // Ex: /Users/john/GitProjects/JackTheGiantGame/desktop
        val currentWorkingDir = File(System.getProperty("user.dir"))
        println("Current Working Dir: " + currentWorkingDir.absolutePath)

        // This is a helpful alternative approach.
        // val root = Paths.get(".").normalize().toAbsolutePath()

        val rootDir: File = if ("desktop" == currentWorkingDir.name) {
            currentWorkingDir.parentFile
        } else {
            // Assume that we are in the top-level Project dir, ex: JackTheGiantGame
            currentWorkingDir
        }
        println("Root Dir: $rootDir")

        val path = rootDir.toString()
        val assetsPath = "$path${File.separator}android${File.separator}assets${File.separator}"
        println("Assets Path: $assetsPath")

        val playerDirPath = assetsPath + "Player"
        val playerDir = File(playerDirPath)
        println("PlayerDir: " + playerDir.absolutePath)
    }
}
