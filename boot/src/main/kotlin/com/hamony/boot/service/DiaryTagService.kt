package com.hamony.boot.service

import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.TagDTO
import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.DiaryTag
import com.hamony.boot.entity.Tag
import com.hamony.boot.repository.DiaryRepository
import com.hamony.boot.repository.DiaryTagRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service


@Service
class DiaryTagService(
    val diaryTagRepository: DiaryTagRepository,
    val tagService: TagService,
    val modelMapper: ModelMapper,
    val diaryService: DiaryService,
) {

    fun saveAll(diaryDTO: DiaryDTO, tags: List<TagDTO>){
        diaryService.findByIdEntity(diaryDTO.id!!).let {
            saveAll(it, tags)
        }
    }

    fun saveAll(diary: Diary, tags: List<TagDTO>): Unit {
        tags.forEach {
            val diaryTag = DiaryTag(
                diary = diary,
                tag = tagService.findByTag(it.tag)!!,
            )
            diaryTagRepository.save(diaryTag)
        }

    }
}
