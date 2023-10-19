package com.hamony.boot.service

import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.dto.TokenDTO
import com.hamony.boot.entity.Member
import com.hamony.boot.exception.ExistUserException
import com.hamony.boot.exception.TokenException
import com.hamony.boot.exception.VerifyPasswordException
import com.hamony.boot.jwt.TokenProvider
import com.hamony.boot.repository.MemberRepository
import com.hamony.boot.response.LoginDTO
import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class MemberService(
    val memberRepository: MemberRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
    val tokenProvider: TokenProvider,
    val modelMapper: ModelMapper,
) {


    fun idDuplicateChk(userId: String): Int {
        if (memberRepository.findByUserId(userId).isPresent)
            return 2
        return 3
    }

    @Transactional
    fun signIn(memberDTO: MemberDTO): Unit {

        if(memberRepository.findByUserId(memberDTO.userId).isPresent())
            throw ExistUserException("이미 계정이 존재합니다.")

        memberDTO.userPwd = bCryptPasswordEncoder.encode(memberDTO.userPwd)
        memberDTO.createAt = LocalDateTime.now()

        val member: Member = modelMapper.map(memberDTO, Member::class.java)

        memberRepository.save(member)
    }

    fun login(loginDTO: LoginDTO): TokenDTO  {
        val member: Member = memberRepository.findByUserId(loginDTO.userId).orElseThrow {
            TokenException("계정을 찾을 수 없습니다.")
        }

        if(!bCryptPasswordEncoder.matches(loginDTO.userPwd, member.userPwd))
            throw VerifyPasswordException("비밀번호가 일치 하지 않습니다.")


        return tokenProvider.generateTokenDTO(member)
    }
}