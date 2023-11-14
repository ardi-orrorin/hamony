package com.hamony.boot.repository

import com.hamony.boot.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberRepository: JpaRepository<Member, Long> {

    fun findByUserIdAndDeleteAtIsNull(userId: String): Optional<Member>
    fun findByUserId(userId: String): Optional<Member>


}