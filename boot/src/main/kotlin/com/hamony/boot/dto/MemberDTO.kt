package com.hamony.boot.dto

import java.time.LocalDateTime

data class MemberDTO(

    private var id: Long?,

    private var userId: String = "",

    private var userPwd: String = "",

    private var email: String = "",

    private var createAt: LocalDateTime = LocalDateTime.now(),

    private var deleteAt: LocalDateTime?,

){

    override fun toString(): String {
        return "Member(id=$id, userId='$userId', userPwd='$userPwd', email='$email', createAt=$createAt, deleteAt=$deleteAt)"
    }
}
