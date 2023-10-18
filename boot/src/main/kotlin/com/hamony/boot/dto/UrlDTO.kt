package com.hamony.boot.dto

import java.time.LocalDateTime

data class UrlDTO(

    var id: Long?,

    var url: String = "",

    var createAt: LocalDateTime = LocalDateTime.now(),

    var deleteAt: LocalDateTime?,

    var avail: Boolean = true,

    var diary: DiaryDTO?
){
    override fun toString(): String {
        return "Url(id=$id, url='$url', createAt=$createAt, deleteAt=$deleteAt, avail=$avail, diary=$diary)"
    }
}
