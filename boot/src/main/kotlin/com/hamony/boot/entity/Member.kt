package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDateTime

@Entity
@Table(name = "TBL_MEMBER")
data class Member(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    val id: Long?,

    @Column(name = "USER_ID")
    val userId: String = "",

    @Column(name = "USER_PWD")
    val userPwd: String = "",

    @Column(name = "EMAIL")
    val email: String = "",

    @Column(name = "CREATEAT")
    val createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "DELETEAT")
    val deleteAt: LocalDateTime? = null,

){

    override fun toString(): String {
        return "Member(id=$id, userId='$userId', userPwd='$userPwd', email='$email', createAt=$createAt, deleteAt=$deleteAt)"
    }
}
