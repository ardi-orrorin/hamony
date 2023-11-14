package com.hamony.boot.service

import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.dto.TokenDTO
import com.hamony.boot.entity.Member
import com.hamony.boot.exception.ExistUserException
import com.hamony.boot.exception.NotFoundException
import com.hamony.boot.exception.TokenException
import com.hamony.boot.exception.VerifyPasswordException
import com.hamony.boot.jwt.TokenProvider
import com.hamony.boot.repository.MemberRepository
import com.hamony.boot.dto.response.LoginDTO
import com.hamony.boot.repository.DiaryRepository
import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class MemberService(
    val memberRepository: MemberRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
    val tokenProvider: TokenProvider,
    val modelMapper: ModelMapper,
    val diaryRepository: DiaryRepository,
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun idDuplicateChk(userId: String): Int {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "userId", userId
        )

        if (memberRepository.findByUserIdAndDeleteAtIsNull(userId).isPresent)
            return 2
        return 3
    }

    @Transactional
    fun signIn(memberDTO: MemberDTO): Unit {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )

        if(memberRepository.findByUserId(memberDTO.userId).isPresent())
            throw ExistUserException("이미 계정이 존재합니다.")

        memberDTO.userPwd = bCryptPasswordEncoder.encode(memberDTO.userPwd)
        memberDTO.createAt = LocalDateTime.now()

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )

        val member: Member = modelMapper.map(memberDTO, Member::class.java)

        memberRepository.save(member)
    }

    fun login(loginDTO: LoginDTO): TokenDTO  {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "loginDTO", loginDTO
        )

        val member: Member = memberRepository.findByUserIdAndDeleteAtIsNull(loginDTO.userId).orElseThrow {
            TokenException("계정을 찾을 수 없습니다.")
        }

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "member", member
        )


        if(!bCryptPasswordEncoder.matches(loginDTO.userPwd, member.userPwd))
            throw VerifyPasswordException("비밀번호가 일치 하지 않습니다.")

        return tokenProvider.generateTokenDTO(member)
    }

    fun editProfile(memberNew: MemberDTO, memberDTO: MemberDTO): Boolean {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberNew", memberNew
        )

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )

        val member: Member = memberRepository.findByUserId(memberDTO.userId).orElseThrow {
            NotFoundException("계정을 찾을 수 없습니다.")
        }

        member.update(memberNew)

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "member", member
        )

        return true
    }

    fun loadByMemberId(userId: String): MemberDTO {
        memberRepository.findByUserId(userId).orElseThrow {
            NotFoundException("계정을 찾을 수 없습니다.")
        }.let {
            return modelMapper.map(it, MemberDTO::class.java)
        }
    }

    fun deleteMember(memberDTO: MemberDTO): Boolean {
        memberRepository.findByUserId(memberDTO.userId).orElseThrow {
            NotFoundException("계정을 찾을 수 없습니다.")
        }.let {
            it.deleteAt = LocalDateTime.now()
            memberRepository.save(it)
            it
        }.let {
            diaryRepository.findAllByMemberId(it.id!!).map {
                it.deleteAt = LocalDateTime.now()
                it
            }.let {
                diaryRepository.saveAll(it)
            }
        }

        return true
    }
}