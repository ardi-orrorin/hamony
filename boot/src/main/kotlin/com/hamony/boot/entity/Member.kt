package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
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
    var id: Long?,

    @Column(name = "USER_ID")
    var userId: String = "",

    @Column(name = "USER_PWD")
    var userPwd: String = "",

    @Column(name = "EMAIL")
    var email: String = "",

    @Column(name = "CREATEAT")
    var createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "DELETEAT")
    var deleteAt: LocalDateTime? = null,

){

    override fun toString(): String {
        return "Member(id=$id, userId='$userId', userPwd='$userPwd', email='$email', createAt=$createAt, deleteAt=$deleteAt)"
    }
}
