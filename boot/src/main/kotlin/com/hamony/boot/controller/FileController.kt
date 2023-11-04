package com.hamony.boot.controller

import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.dto.response.ResponseDTO
import com.hamony.boot.exception.InvalidFile
import com.hamony.boot.file.*
import com.hamony.boot.service.MemberService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File


@RestController
@RequestMapping("/api/file")
class FileController(
    val fileProvider: FileProvider,
    val memberService: MemberService,
    val resizeImg: ResizeImage,
) {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun extValidation(ext: String, fileType: FileType ) {
        when (fileType) {
            FileType.IMAGE -> {
                if (!fileProvider.FILE_IMAGES.contains(ext))
                    throw InvalidFile("허용되지 않은 요청입니다.")
            }
            FileType.VIDEO -> {
                if (!fileProvider.FILE_VIDEOS.contains(ext))
                    throw InvalidFile("허용되지 않은 요청입니다.")
            }
            FileType.FILE -> {
                if (!fileProvider.FILE_APPS.contains(ext))
                    throw InvalidFile("허용되지 않은 요청입니다.")
            }
            else -> throw InvalidFile("허용되지 않은 요청입니다.")
        }
    }

    @GetMapping("/osdir")
    fun getOSDIR(): ResponseEntity<ResponseDTO<String>> {


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

    @PostMapping("upload")
    fun uploadFile(
        @RequestPart("file") multipartFile: List<MultipartFile>,
        @AuthenticationPrincipal memberDTO: MemberDTO,
    ): ResponseEntity<ResponseDTO<String>> {

        val userKey = memberService.loadByMemberId(memberDTO.userId).id!!.toInt()

        multipartFile.forEach{
            fileProvider.writeFile(it.bytes, it.originalFilename!!, userKey)
        }


        return ResponseEntity.ok(ResponseDTO(HttpStatus.OK.value(), "파일 업로드 성공"))
    }

    @GetMapping("/media/image/{id}/{fileName}")
    fun getMediaFile(
        @PathVariable id: Int,
        @PathVariable fileName: String,
        @RequestParam(required = true, defaultValue = "true") thumnail: Boolean,
        @RequestParam(required = false) size: Int?,
    ): ResponseEntity<ByteArray> {

        val ext: String = fileName.substring(fileName.lastIndexOf(".") + 1)

        extValidation(ext, FileType.IMAGE)

        val file = File(fileProvider.getOsDir() + "/${id}/images/" + fileName)
        var oFile = file.readBytes()

        if(thumnail) {
            oFile = resizeImg
                .read(file)
                .scale(size ?: 500, ResizeCriteria.HEIGHT)
                .readThumnail(ext)
        }

        val headers = HttpHeaders()

        headers.accessControlMaxAge = 60 * 60 * 24
        headers.cacheControl = "public, max-age=31536000"
        headers.contentType = MediaType("image", ext)

        return ResponseEntity(oFile, headers, HttpStatus.OK)
    }

    @GetMapping("/media/video/{id}/{fileName}")
    fun getVideoFile(
        @PathVariable id: Int,
        @PathVariable fileName: String
    ): ResponseEntity<ByteArray> {

        val ext: String = fileName.substring(fileName.lastIndexOf(".") + 1)

        extValidation(ext, FileType.VIDEO)

        val file = File(fileProvider.getOsDir() + "/${id}/video/" + fileName)

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

        headers.contentType = MediaType("video",ext)

        return ResponseEntity(file.readBytes(), headers, HttpStatus.OK)
    }

}