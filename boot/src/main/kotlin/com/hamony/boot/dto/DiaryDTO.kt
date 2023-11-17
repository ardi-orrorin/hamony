package com.hamony.boot.dto

import com.hamony.boot.entity.DiaryTag
import com.hamony.boot.entity.Member
import jakarta.persistence.*
import java.time.LocalDateTime


data class DiaryDTO(

    var id: Long? = null,

    var subject: String = "",

    var content: String = "",

    var createAt: LocalDateTime = LocalDateTime.now(),

    var updateAt: LocalDateTime? = null,

    var deleteAt: LocalDateTime? = null,

    var member: Member? = null,

    var diaryTag: MutableList<DiaryTag> = mutableListOf()

){
    override fun toString(): String {
        return "DiaryDTO(id=$id, subject='$subject', content='$content', createAt=$createAt, updateAt=$updateAt, deleteAt=$deleteAt, member=$member, diaryTag=$diaryTag)"
    }
}
