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
import com.hamony.boot.request.DiarySearchDTO
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
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

    fun recent(memberDTO: MemberDTO): List<DiaryDTO?> {

        // TODO: 최근 그 표시 알고리즘 구현

        return mutableListOf(null)
    }

    fun search(diarySearchDTO: DiarySearchDTO, pageable: Pageable): List<DiaryDTO> {

        // TODO: 검색 알고리즘 추가

        val diaryList: MutableList<Diary> = diaryRepository.findAllBySubjectContainsOrContentContains(diarySearchDTO.subject, diarySearchDTO.content, pageable)

        return diaryList.map { modelMapper.map(it, DiaryDTO::class.java) }

    }


}