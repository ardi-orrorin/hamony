package com.hamony.boot.dto

import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.Tag


data class DiaryTagDTO(

    var id: Long? = null,

    var diary: Diary? = null,

    var tag: Tag? = null

){
    override fun toString(): String {
        return "DiaryTagDTO(id=$id, diary=$diary, tag=$tag)"
    }
}
