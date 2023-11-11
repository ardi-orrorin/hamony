package com.hamony.boot.service

import com.hamony.boot.dto.TagDTO
import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.DiaryTag
import com.hamony.boot.entity.Tag
import com.hamony.boot.exception.NotFoundException
import com.hamony.boot.repository.DiaryRepository
import com.hamony.boot.repository.DiaryTagRepository
import com.hamony.boot.repository.TagRepository
import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TagService(
    val tagRepository: TagRepository,
    val diaryTagRepository: DiaryTagRepository,
    val diaryRepository: DiaryRepository,
    val modelMapper: ModelMapper,
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun findByDairyId(diaryId: Long): MutableList<TagDTO> {
        val diary: Diary = diaryRepository.findById(diaryId).orElseThrow {
            NotFoundException("데이터를 찾을 수 없습니다.")
        }

        val diaryTags: MutableList<DiaryTag> = diaryTagRepository.findAllByDiary(diary)

        return diaryTags.map {
            modelMapper.map(it.tag, TagDTO::class.java)
        }.toMutableList()
    }

    fun findByTag(tag: String): Tag? {
        return tagRepository.findByTagEqualsIgnoreCase(tag)
    }

    @Transactional
    fun save(tagDTO: TagDTO): Unit {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "tagDTO", tagDTO
        )

        if(tagRepository.existsByTagEqualsIgnoreCase(tagDTO.tag)) {
            tagRepository.save(
                modelMapper.map(tagDTO, Tag::class.java)
            )
        }
    }

    @Transactional
    fun saveAll(tagDTOs: List<TagDTO>): Unit {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "tagDTOs", tagDTOs
        )

        tagDTOs.forEach {
            tagRepository.existsByTagEqualsIgnoreCase(it.tag).let { exist ->
                if(!exist) {
                    modelMapper.map(it, Tag::class.java).let {
                        tagRepository.save(it)
                    }
                }
            }
        }
    }
}