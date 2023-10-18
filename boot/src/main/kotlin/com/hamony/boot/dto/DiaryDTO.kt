package com.hamony.boot.dto

import java.time.LocalDateTime


data class DiaryDTO(

    var id: Long?,

    var subject: String = "",

    var content: String = "",

    var createAt: LocalDateTime = LocalDateTime.now(),

    var updateAt: LocalDateTime?,

    var deleteAt: LocalDateTime?,

    var member: MemberDTO?,

    var diaryTag: MutableList<DiaryTagDTO> = ArrayList()


){
    override fun toString(): String {
        return "Diary(id=$id, subject='$subject', content='$content', createAt=$createAt, updateAt=$updateAt, deleteAt=$deleteAt, member=$member, diaryTag=$diaryTag)"
    }
}
