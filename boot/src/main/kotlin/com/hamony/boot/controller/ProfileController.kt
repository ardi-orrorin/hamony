package com.hamony.boot.controller

import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.FavDiaryDTO
import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.dto.response.ResponseDTO
import com.hamony.boot.service.DiaryService
import com.hamony.boot.service.FavoriteService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/profile")
class ProfileController(
    val diaryService: DiaryService,
    val favoriteService: FavoriteService,

) {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("list")
    fun getDiaryList(
        @AuthenticationPrincipal memberDTO: MemberDTO
    ): ResponseEntity<ResponseDTO<List<DiaryDTO>>> {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )

        return ResponseEntity.ok(
            ResponseDTO(
                HttpStatus.OK.value(),
                diaryService.findByMemberId(memberDTO.id!!)
            )
        )
    }

    @GetMapping("fav")
    fun getFavoriteDiary(
        @AuthenticationPrincipal memberDTO: MemberDTO
    ): ResponseEntity<ResponseDTO<List<FavDiaryDTO>>> {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )
        return ResponseEntity.ok(
            ResponseDTO(
                HttpStatus.OK.value(),
                favoriteService.findByFavDiaryList(memberDTO.id!!)
            )
        )
    }

    @GetMapping("order")
    fun getOrderDiary(
        @AuthenticationPrincipal memberDTO: MemberDTO,
    ): ResponseEntity<ResponseDTO<List<DiaryDTO>>> {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )
        return ResponseEntity.ok(
            ResponseDTO(
                HttpStatus.OK.value(),
                diaryService.orderDiary(memberDTO)
            )
        )
    }
}