package com.hamony.boot.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.ArrayList

@Entity
@Table(name = "TBL_DIARY")
data class Diary(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null,

    @Column(name = "SUBJECT", nullable = false)
    var subject: String = "",

    @Column(name = "CONTENT", nullable = false)
    var content: String = "",

    @Column(name = "CREATEAT", nullable = false)
    var createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "UPDATEAT", nullable = true)
    var updateAt: LocalDateTime? = null,

    @Column(name = "DELETEAT", nullable = true)
    var deleteAt: LocalDateTime? = null,

    @JoinColumn(name = "REF_MEMBER_ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var member: Member?,

    @OneToMany(mappedBy = "diary", cascade = [CascadeType.ALL], targetEntity = DiaryTag::class)
//    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
//    @JoinColumn(name = "id")
    var diaryTag: MutableList<DiaryTag> = ArrayList()


){
    override fun toString(): String {
        return "Diary(id=$id, subject='$subject', content='$content', createAt=$createAt, updateAt=$updateAt, deleteAt=$deleteAt, member=$member, diaryTag=$diaryTag)"
    }
}
