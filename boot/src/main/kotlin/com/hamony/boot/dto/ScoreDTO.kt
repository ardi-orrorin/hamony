package com.hamony.boot.dto

data class ScoreDTO(

    var id: Long?,

    var score: Int = 0,

    var tag: TagDTO?,

    var member: MemberDTO?
)
