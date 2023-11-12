package com.hamony.boot.service

import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.dto.UrlDTO
import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.Member
import com.hamony.boot.entity.Url
import com.hamony.boot.exception.NotFoundException
import com.hamony.boot.repository.DiaryRepository
import com.hamony.boot.repository.UrlRepository
import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UrlService(
    val urlRepository: UrlRepository,
    val diaryRepository: DiaryRepository,
    val modelMapper: ModelMapper,
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)


    fun findByDiaryId(id: Long): MutableList<UrlDTO> {
        return urlRepository.findByDiaryId(id).map {
            modelMapper.map(it, UrlDTO::class.java).let {urlDTO ->
                urlDTO.diary = null
                urlDTO
            }
        }.toMutableList()
    }

    @Transactional
    fun save(diaryDTO: DiaryDTO, urlDTO: UrlDTO): Unit {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "diaryDTO", diaryDTO
        )

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "urlDTO", urlDTO
        )


        diaryRepository.findById(diaryDTO.id!!).orElseThrow {
            NotFoundException("게시글을 찾을 수 없습니다.")
        }

        val diary: Diary = modelMapper.map(diaryDTO, Diary::class.java)
        val url: Url = modelMapper.map(urlDTO, Url::class.java)

        url.diary = diary

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "url", url
        )
        urlRepository.save(url)
    }

    fun saveAll(diary: Diary, urls: MutableList<UrlDTO>) {
        val urlsEntity: MutableList<Url> = urls.map { urlDTO ->
            modelMapper.map(urlDTO, Url::class.java)
                .let {
                    it.url = it.url.replace("http://", "").replace("https://", "").replace("www.", "")
                    it.diary = diary
                    it
                }
            }.toMutableList()
        urlRepository.saveAll(urlsEntity)
    }
}