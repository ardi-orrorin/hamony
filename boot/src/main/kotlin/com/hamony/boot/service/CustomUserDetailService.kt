package com.hamony.boot.service

import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.entity.Member
import com.hamony.boot.exception.NotFoundException
import com.hamony.boot.repository.MemberRepository
import org.modelmapper.ModelMapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    val modelMapper: ModelMapper,
    val memberRepository: MemberRepository

): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val member: Member = memberRepository.findByUserId(username!!).orElseThrow {
            NotFoundException("계정을 찾을 수 없습니다.")
        }

        return MemberDTO( // modelMapper 기본생성자 에러 발생으로 임시 방편 코드
            member.id,
            member.userId,
            "",
            member.email,
            member.createAt,
            member.deleteAt
        )
    }
}