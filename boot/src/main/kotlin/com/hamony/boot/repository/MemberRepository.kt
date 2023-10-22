package com.hamony.boot.repository

import com.hamony.boot.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberRepository: JpaRepository<Member, Long> {

    fun findByUserId(userId: String): Optional<Member>

}