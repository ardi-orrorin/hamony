package com.hamony.boot.file

import com.hamony.boot.exception.InvalidFile
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.util.*

@Component
class FileProvider {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Value("\${files.file-dir.macos}")
    val macosFileDir: String = ""

    @Value("\${files.file-dir.windows}")
    val windowsFileDir: String = ""

    @Value("\${files.file-dir.linux}")
    val linuxFileDir: String = ""

    val FILE_IMAGES = listOf("jpg", "jpeg", "png", "gif", "bmp")

    val FILE_VIDEOS = listOf("mp4", "avi", "mkv", "mov", "wmv", "flv", "webm")

    val FILE_APPS = listOf("exe", "dmg", "pkg", "deb", "rpm")

    fun getOsDir() =
        when (System.getProperty("os.name").lowercase()) {
            "mac os x" -> macosFileDir
            "windows" -> windowsFileDir
            else -> linuxFileDir
        }

    fun writeFile(file: ByteArray, originalFilename: String, userKey: Int) {

        val ext: String = originalFilename.substring(originalFilename.lastIndexOf(".") + 1)

        if(File(getOsDir() + "/" + userKey).isDirectory)
            File(getOsDir() + userKey).mkdirs()

        fun write(type: String) {
            val pathName = getOsDir() + "/" + userKey + "/" + type

            if (!File(pathName).isDirectory)
                File(pathName).mkdirs()

            File(pathName+ "/" + UUID.randomUUID() + "." + ext).writeBytes(file)
        }

        if(FILE_IMAGES.contains(ext)) {
            write("images")
        }else if (FILE_VIDEOS.contains(ext)) {
            write("videos")

        } else if (FILE_APPS.contains(ext)) {
            write("apps")
        } else {
            throw InvalidFile("허용되지 않은 파일입니다.")
        }


    }

}