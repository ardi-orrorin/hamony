package com.hamony.boot.service

import com.hamony.boot.dto.DiaryTagDTO
import com.hamony.boot.dto.TagDTO
import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.DiaryTag
import com.hamony.boot.entity.Tag
import com.hamony.boot.repository.DiaryTagRepository
import com.hamony.boot.repository.TagRepository
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class DiaryTagService(
    val diaryTagRepository: DiaryTagRepository,
    val tagService: TagService,
    val tagRepository: TagRepository,
    val modelMapper: ModelMapper,
//    val diaryService: DiaryService,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun saveAll(diary: Diary, tags: List<TagDTO>): Unit {
        tags.forEach {
            val diaryTag = DiaryTag(
                diary = diary,
                tag = tagService.findByTag(it.tag)!!,
            )
            diaryTagRepository.save(diaryTag)
        }

    }

    fun findAllByTag(tag: String): TagDTO {
        val addTagChar = "#$tag"

        val tag: Tag = tagRepository.findByTagEqualsIgnoreCase(addTagChar) ?:   return TagDTO()

        if (tag == null) return TagDTO()

        val tagDTO = modelMapper.map(tag, TagDTO::class.java)

        return tagDTO.diaryTagList.map {
            it.diary!!.diaryTag = mutableListOf()
            it.diary!!.member = null
            it.tag = null
            it
        }.let {
            tagDTO.diaryTagList = it.toMutableList()
            tagDTO
        }
    }
}
