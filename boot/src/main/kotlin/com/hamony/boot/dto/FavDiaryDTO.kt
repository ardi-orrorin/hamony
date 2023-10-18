package com.hamony.boot.dto


data class FavDiaryDTO(

    var id: Long? = null,

    var diary: DiaryDTO?,

    var member: MemberDTO?
){
    override fun toString(): String {
        return "FavDiary(id=$id, diary=$diary, member=$member)"
    }
}
