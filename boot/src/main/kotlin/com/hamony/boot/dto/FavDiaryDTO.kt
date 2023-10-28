package com.hamony.boot.dto


data class FavDiaryDTO(

    var id: Long? = null,

    var diary: DiaryDTO? = null,

    var member: MemberDTO? = null
){
    override fun toString(): String {
        return "FavDiary(id=$id, diary=$diary, member=$member)"
    }
}
