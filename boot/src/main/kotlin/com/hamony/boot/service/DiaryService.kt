package com.hamony.boot.service

import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.DiaryTagDTO
import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.DiaryTag
import com.hamony.boot.entity.Member
import com.hamony.boot.exception.NotFoundException
import com.hamony.boot.repository.DiaryRepository
import com.hamony.boot.repository.MemberRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DiaryService(
    val modelMapper: ModelMapper,
    val diaryRepository: DiaryRepository,
    val memberRepository: MemberRepository

) {

    fun findById(diaryId: Long): DiaryDTO{
        val diary: Diary = diaryRepository.findById(diaryId).orElseThrow{
            NotFoundException("데이터를 찾을 수 업습니다.")
        }

        return modelMapper.map(diary, DiaryDTO::class.java)
    }

    fun save(diaryTagDTO: DiaryTagDTO, memberDTO: MemberDTO): Unit {

        val member: Member = memberRepository.findByUserId(memberDTO.userId).get()
        val diary: Diary = modelMapper.map(diaryTagDTO.diary, Diary::class.java)
        diary.member = member

        // tag 처리
        diaryRepository.save(diary)
    }
}