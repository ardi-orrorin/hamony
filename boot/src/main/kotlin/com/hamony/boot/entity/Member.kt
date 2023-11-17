package com.hamony.boot.entity

import com.hamony.boot.dto.MemberDTO
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

    @JoinColumn(name = "REF_MEMBER_ID")
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var diaryList: MutableList<Diary> = mutableListOf(),

    ){
    fun update(memberDTO: MemberDTO) {
        this.userId = memberDTO.userId
        this.email = memberDTO.email
        this.userPwd = memberDTO.userPwd
    }

    fun delete(){
        this.deleteAt = LocalDateTime.now()
    }
    override fun toString(): String {
        return "Member(id=$id, userId='$userId', userPwd='$userPwd', email='$email', createAt=$createAt, deleteAt=$deleteAt)"
    }
}
