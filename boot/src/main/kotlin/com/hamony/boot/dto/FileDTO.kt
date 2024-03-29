package com.hamony.boot.dto

import java.time.LocalDateTime


data class FileDTO(


    var id: Long? = null,

    var path: String = "",

    var name: String = "",

    var ext: String = "",

    var createAt: LocalDateTime = LocalDateTime.now(),

    var deleteAt: LocalDateTime? = null,

    var avail: Boolean = true,

    var diary: DiaryDTO? = null
){
    override fun toString(): String {
        return "File(id=$id, path='$path', name='$name', ext='$ext', createAt=$createAt, deleteAt=$deleteAt, avail=$avail, diary=$diary)"
    }
}
