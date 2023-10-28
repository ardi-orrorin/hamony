package com.hamony.boot.service

import com.hamony.boot.dto.TagDTO
import com.hamony.boot.entity.Tag
import com.hamony.boot.repository.TagRepository
import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TagService(
    val tagRepository: TagRepository,
    val modelMapper: ModelMapper,
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)
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

        val tags: List<Tag> = tagDTOs.filter { !tagRepository.existsByTagEqualsIgnoreCase(it.tag) }
            .map { modelMapper.map(it, Tag::class.java) }

        tagRepository.saveAll(tags)
    }
}