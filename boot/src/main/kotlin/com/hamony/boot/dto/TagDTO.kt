package com.hamony.boot.dto

data class TagDTO(

    var id: Long? = null,

    var tag: String = "",

    var diaryList: MutableList<DiaryTagDTO> = mutableListOf()
){
    override fun toString(): String {
        return "Tag(id=$id, tag='$tag', diaryList=${diaryList.forEach(::println)})"
    }
}
