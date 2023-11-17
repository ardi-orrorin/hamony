package com.hamony.boot.dto

data class TagDTO(

    var id: Long? = null,

    var tag: String = "",

    var diaryTagList: MutableList<DiaryTagDTO> = mutableListOf(),

){
    override fun toString(): String {
        return "TagDTO(id=$id, tag='$tag', diaryTagList=$diaryTagList)"
    }
}
