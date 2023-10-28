package com.hamony.boot.dto

data class ScoreDTO(

    var id: Long? = null,

    var score: Int = 0,

    var tag: TagDTO? = null,

    var member: MemberDTO? = null
)
