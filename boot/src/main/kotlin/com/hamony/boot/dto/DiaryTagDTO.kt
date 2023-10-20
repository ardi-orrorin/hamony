package com.hamony.boot.dto


data class DiaryTagDTO(

    var id: Long?,

    var diary: DiaryDTO?,

    var tag: MutableList<TagDTO>?

){
    override fun toString(): String {
        return "DiaryTag(id=$id, diary=$diary, tag=$tag)"
    }
}
