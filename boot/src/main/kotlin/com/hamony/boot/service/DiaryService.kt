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
import com.hamony.boot.dto.request.DiarySearchDTO
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import java.time.LocalDateTime

@Service
class DiaryService(
    val modelMapper: ModelMapper,
    val diaryRepository: DiaryRepository,
    val memberRepository: MemberRepository,

) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun findById(diaryId: Long): DiaryDTO{

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "diaryId", diaryId
        )

        val diary: Diary = diaryRepository.findById(diaryId).orElseThrow{
            NotFoundException("데이터를 찾을 수 업습니다.")
        }

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "diary", diary
        )

        return modelMapper.map(diary, DiaryDTO::class.java)
    }

    fun save(diaryTagDTO: DiaryTagDTO, memberDTO: MemberDTO): Unit {

        val member: Member = memberRepository.findByUserId(memberDTO.userId).get()

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "member", member
        )

        val diary: Diary = modelMapper.map(diaryTagDTO.diary, Diary::class.java)

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "diary", diary
        )

        diary.member = member

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "diaryMember", diary
        )

        // tag 처리
        diaryRepository.save(diary)
    }

    fun recent(memberDTO: MemberDTO, pageable: Pageable): List<DiaryDTO> {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "pageable", pageable
        )

        // TODO: 최근 그 표시 알고리즘 구현

        // 임시
        val diaryList: Page<Diary> = diaryRepository.findAll(pageable)

        return diaryList.map { DiaryDTO(it.id, it.subject, it.content, it.createAt, it.updateAt, it.deleteAt, null, mutableListOf()) }.toList()
    }

    fun search(keyword: String, pageable: Pageable): List<DiaryDTO> {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "pageable", pageable
        )

        // TODO: 검색 알고리즘 추가

        val diaryList: MutableList<Diary> = diaryRepository.findAllBySubjectContainsOrContentContains(keyword, keyword, pageable)

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "diaryList", diaryList
        )

        return diaryList.map { modelMapper.map(it, DiaryDTO::class.java) }

    }

    fun findByMemberId(id: Long): List<DiaryDTO> {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "id", id
        )

        val diaryList: MutableList<Diary> = diaryRepository.findAllByMemberId(id)

        return diaryList.map { modelMapper.map(it, DiaryDTO::class.java) }
    }

    fun orderDiary(memberDTO: MemberDTO): List<DiaryDTO> {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )

        val diaryList: MutableList<Diary> = diaryRepository.findAll()
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "diaryList", diaryList
        )

        return diaryList.map { modelMapper.map(it, DiaryDTO::class.java) }
    }


}