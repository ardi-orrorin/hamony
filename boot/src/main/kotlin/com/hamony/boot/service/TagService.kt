package com.hamony.boot.service

import com.hamony.boot.dto.TagDTO
import com.hamony.boot.entity.Tag
import com.hamony.boot.repository.TagRepository
import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class TagService(
    val tagRepository: TagRepository,
    val modelMapper: ModelMapper,
) {

    @Transactional
    fun save(tagDTO: TagDTO): Unit {

        if(tagRepository.findByTagExists(tagDTO.tag)) {
            tagRepository.save(
                modelMapper.map(tagDTO, Tag::class.java)
            )
        }
    }

    @Transactional
    fun saveAll(tagDTOs: List<TagDTO>): Unit {
        val tags: List<Tag> = tagDTOs.filter { !tagRepository.findByTagExists(it.tag) }
            .map { modelMapper.map(it, Tag::class.java) }

        tagRepository.saveAll(tags)
    }
}