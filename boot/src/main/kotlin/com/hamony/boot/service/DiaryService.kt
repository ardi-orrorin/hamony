package com.hamony.boot.service

import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.DiaryTagDTO
import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.exception.NotFoundException
import com.hamony.boot.dto.request.DiarySearchDTO
import com.hamony.boot.entity.*
import com.hamony.boot.file.FileProvider
import com.hamony.boot.repository.*
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.UUID

@Service
class DiaryService(
    val diaryRepository: DiaryRepository,
    val memberRepository: MemberRepository,
    val fileRepository: FileRepository,
    val tagService: TagService,
    val diaryTagService: DiaryTagService,

    val fileProvider: FileProvider,
    val modelMapper: ModelMapper,
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun findByIdEntity(diaryId: Long): Diary {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "diaryId", diaryId
        )

        val diary: Diary = diaryRepository.findById(diaryId).orElseThrow{
            NotFoundException("데이터를 찾을 수 업습니다.")
        }

        return diary
    }

    fun findByIdDTO(diaryId: Long): DiaryDTO{
        return findByIdEntity(diaryId).let {
            modelMapper.map(it, DiaryDTO::class.java)
        }
    }

    fun save(diaryTagDTO: DiaryTagDTO, memberDTO: MemberDTO, file: MultipartFile?): Unit {

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


        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "save after diary", diary
        )

        if(file != null){
            file.originalFilename?.let {
                val fileInfo: Map<String, String> = fileProvider.writeFile(file.bytes, it, member.id!!.toInt())
                val fileEntity = File(
                    name = fileInfo["name"] as String,
                    path = fileInfo["path"] as String,
                    ext = it.substring(it.lastIndexOf(".") + 1),
                    createAt = LocalDateTime.now(),
                    avail = true,
                    diary = diary
                )
                fileRepository.save(fileEntity)
            }
        }

        if(!diaryTagDTO.tag.isNullOrEmpty()){
            log.info("[{}]({}) : {}: {}",
                object{}.javaClass.enclosingClass.name,
                object{}.javaClass.enclosingMethod.name,
                "tag", "null or empty"
            )

            tagService.saveAll(diaryTagDTO.tag!!)

            diaryTagService.saveAll(diary, diaryTagDTO.tag!!)
        }
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