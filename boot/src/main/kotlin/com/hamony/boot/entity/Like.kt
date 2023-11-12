package com.hamony.boot.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.Date


@Entity
@Table(name = "TBL_LIKE")
data class Like(

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "CREATEAT")
    val createAt: LocalDateTime = LocalDateTime.now() ,

    @Column(name = "REF_DIARY_ID")
    val refDiaryId: Long,

    @Column(name = "REF_MEMBER_ID")
    val refMemberId: Long,

)
