package com.hamony.boot.service

import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.FavDiaryDTO
import com.hamony.boot.entity.FavDiary
import com.hamony.boot.entity.Member
import com.hamony.boot.exception.NotFoundException
import com.hamony.boot.repository.FavoriteRepository
import com.hamony.boot.repository.MemberRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service


@Service
class FavoriteService(
    val favoriteRepository: FavoriteRepository,
    val modelMapper: ModelMapper,
    val memberRepository: MemberRepository,
) {
    fun findByFavDiaryList(id: Long): List<FavDiaryDTO> {

        val member: Member = memberRepository.findById(id).orElseThrow {
            NotFoundException("계정을 찾을 수 없습니다.")
        }

        val favDiary: MutableList<FavDiary> = favoriteRepository.findAllByMember(member)

        return favDiary.map { modelMapper.map(it, FavDiaryDTO::class.java) }

    }


}