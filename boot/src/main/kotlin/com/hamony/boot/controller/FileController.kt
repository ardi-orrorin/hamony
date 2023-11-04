package com.hamony.boot.controller

import com.hamony.boot.dto.response.ResponseDTO
import com.hamony.boot.exception.InvalidFile
import com.hamony.boot.file.FileProvider
import org.apache.http.entity.ContentType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.web.header.Header
import org.springframework.util.MimeType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.awt.Image
import java.io.File
import java.util.UUID
import javax.imageio.ImageIO


@RestController
@RequestMapping("/api/file")
class FileController(
    val fileProvider: FileProvider
) {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/osdir")
    fun getOSDIR(): ResponseEntity<ResponseDTO<String>> {
        log.info("[{}]({}) {} : {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "macos", fileProvider.macosFileDir
        )

        log.info("[{}]({}) {} : {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "windows", fileProvider.windowsFileDir
        )

        log.info("[{}]({}) {} : {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "linux", fileProvider.linuxFileDir
        )

        return ResponseEntity.ok(
            ResponseDTO(
                status = HttpStatus.OK.value(),
                data = fileProvider.getOsDir()
            )
        )
    }

    @GetMapping("/media/image/{fileName}")
    fun getMediaFile(@PathVariable fileName: String): ResponseEntity<ByteArray> {

        val allowedExt = listOf("jpg", "jpeg", "png", "gif", "bmp")

        if (!allowedExt.contains(fileName.substring(fileName.lastIndexOf(".") + 1)))
            throw InvalidFile("허용되지 않은 요청입니다.")

        val file = File(fileProvider.getOsDir() + fileName)

        log.info("[{}]({}) {} : {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "file", file
        )

        val headers = HttpHeaders()

        when (fileName.substring(fileName.lastIndexOf(".") + 1)) {
            "jpg" -> headers.contentType = MediaType.IMAGE_JPEG
            "png" -> headers.contentType = MediaType.IMAGE_PNG
            "gif" -> headers.contentType = MediaType.IMAGE_GIF
        }

        return ResponseEntity(file.readBytes(), headers, HttpStatus.OK)
    }

    @GetMapping("/media/video/{fileName}")
    fun getVideoFile(@PathVariable fileName: String): ResponseEntity<ByteArray> {

        val allowedExt = listOf("mp4", "avi", "mov", "wmv", "flv", "mkv", "webm")

        if (!allowedExt.contains(fileName.substring(fileName.lastIndexOf(".") + 1)))
            throw InvalidFile("허용되지 않은 요청입니다.")

        val file = File(fileProvider.getOsDir() + fileName)

        log.info("[{}]({}) {} : {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "file", file
        )

        val headers = HttpHeaders()
        headers.accessControlMaxAge = 60 * 60 * 24
        headers.cacheControl = "public, max-age=31536000"
        headers["Accept-Ranges"] = "bytes"
        headers["contentRange"] = "bytes 0-${file.length() - 1}/${file.length()}"
        headers.contentLength = file.length()

        when (fileName.substring(fileName.lastIndexOf(".") + 1)) {
            "mp4" -> headers.contentType = MediaType("video","mp4")
            "avi" -> headers.contentType = MediaType("video","avi")
            "mov" -> headers.contentType = MediaType("video","mov")
            "wmv" -> headers.contentType = MediaType("video","wmv")
            "flv" -> headers.contentType = MediaType("video","flv")
            "mkv" -> headers.contentType = MediaType("video","mkv")
            "webm" -> headers.contentType = MediaType("video","webm")
        }

        return ResponseEntity(file.readBytes(), headers, HttpStatus.OK)
    }

    @PostMapping("/media/upload")
    fun uploadMediaFile(
        @RequestPart("test") multipartFile: List<MultipartFile>,
        @RequestPart("test123") test123: Map<String, String>
    ) {

        multipartFile.forEach {
            log.info("[{}]({}) {} : {}",
                object{}.javaClass.enclosingClass.name,
                object{}.javaClass.enclosingMethod.name,
                "multipartFile", it.originalFilename
            )

            val ext = it.originalFilename!!.substring(it.originalFilename!!.lastIndexOf("."))
            val filename = UUID.randomUUID().toString() + ext
            File(fileProvider.getOsDir() + filename).createNewFile()

        }

        log.info("[{}]({}) {} : {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "test123", test123
        )

    }
}