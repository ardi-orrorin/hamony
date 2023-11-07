package com.hamony.boot.dto.request

import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.TagDTO
import com.hamony.boot.dto.UrlDTO


data class ReqDiaryDTO(

    var id: Long? = null,

    var diary: DiaryDTO? = null,

    var tags: MutableList<TagDTO>? = mutableListOf(),

    var urls: MutableList<UrlDTO>? = mutableListOf(),

){
    override fun toString(): String {
        return "DiaryTagDTO(id=$id, diary=$diary, tags=$tags, urls=$urls)"
    }
}
