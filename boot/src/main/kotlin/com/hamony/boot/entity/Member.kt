package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Entity
@Table(name = "TBL_MEMBER")
@AllArgsConstructor
@NoArgsConstructor
data class Member(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private var id: Long?,

    @Column(name = "USER_ID")
    private var userId: String = "",

    @Column(name = "USER_PWD")
    private var userPwd: String = "",

    @Column(name = "EMAIL")
    private var email: String = "",

    @Column(name = "CREATEAT")
    private var createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "DELETEAT")
    private var deleteAt: LocalDateTime? = null,

){

    override fun toString(): String {
        return "Member(id=$id, userId='$userId', userPwd='$userPwd', email='$email', createAt=$createAt, deleteAt=$deleteAt)"
    }
}
