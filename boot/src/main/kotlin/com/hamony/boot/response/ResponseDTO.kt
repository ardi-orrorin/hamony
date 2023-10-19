package com.hamony.boot.response

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@AllArgsConstructor
@NoArgsConstructor
data class ResponseDTO<T>(
    var status: Int,

    var data: T,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val responseTime: LocalDateTime = LocalDateTime.now()

)
