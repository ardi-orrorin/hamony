package com.hamony.boot.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.TagDTO
import com.hamony.boot.dto.UrlDTO
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.time.LocalDateTime

data class ResponseDairyDTO(
    val status: Int,

    val diary: DiaryDTO,

    val url: MutableList<UrlDTO> = mutableListOf(),

    val tag: MutableList<TagDTO> = mutableListOf(),

    val file: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val responseTime: LocalDateTime = LocalDateTime.now()
)
