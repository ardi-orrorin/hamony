package com.hamony.boot.dto

import java.time.LocalDateTime


data class AlertTemplateDTO(

    var id: Long? = null,

    var body: String = "",

    var createAT: LocalDateTime = LocalDateTime.now()
)
