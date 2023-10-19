package com.hamony.boot.dto

import java.time.LocalDateTime

data class MemberDTO(

    var id: Long?,

    var userId: String = "",

    var userPwd: String = "",

    var email: String = "",

    var createAt: LocalDateTime = LocalDateTime.now(),

    var deleteAt: LocalDateTime?,

){

    override fun toString(): String {
        return "Member(id=$id, userId='$userId', userPwd='$userPwd', email='$email', createAt=$createAt, deleteAt=$deleteAt)"
    }
}
