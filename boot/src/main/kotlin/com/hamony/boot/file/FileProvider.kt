package com.hamony.boot.file

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class FileProvider {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Value("\${files.file-dir.macos}")
    val macosFileDir: String = ""

    @Value("\${files.file-dir.windows}")
    val windowsFileDir: String = ""

    @Value("\${files.file-dir.linux}")
    val linuxFileDir: String = ""

    fun getOsDir() =
        when (System.getProperty("os.name").lowercase()) {
            "mac os x" -> macosFileDir
            "windows" -> windowsFileDir
            else -> linuxFileDir
        }

}