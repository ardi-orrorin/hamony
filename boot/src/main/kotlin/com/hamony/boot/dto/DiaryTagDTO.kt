package com.hamony.boot.dto


data class DiaryTagDTO(

    var id: Long? = null,

    var diary: DiaryDTO? = null,

    var tag: MutableList<TagDTO>? = null

){
    override fun toString(): String {
        return "DiaryTag(id=$id, diary=$diary, tag=$tag)"
    }
}
