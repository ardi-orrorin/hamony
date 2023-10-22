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
import org.springframework.stereotype.Service

@Service
class UrlService(
    val urlRepository: UrlRepository,
    val diaryRepository: DiaryRepository,
    val modelMapper: ModelMapper,
) {

    @Transactional
    fun save(diaryDTO: DiaryDTO, urlDTO: UrlDTO): Unit {

        diaryRepository.findById(diaryDTO.id!!).orElseThrow {
            NotFoundException("게시글을 찾을 수 없습니다.")
        }

        val diary: Diary = modelMapper.map(diaryDTO, Diary::class.java)
        val url: Url = modelMapper.map(urlDTO, Url::class.java)

        url.diary = diary
        urlRepository.save(url)
    }


}