package com.hamony.boot.controller

import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.dto.response.ResponseDTO
import com.hamony.boot.service.LikeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/like")
class LikeController(
    val likeService: LikeService,

) {
    @PostMapping("/{diaryId}")
    fun modifyLike(
        @PathVariable diaryId: Long,
        @AuthenticationPrincipal memberDTO: MemberDTO
    ): ResponseEntity<Boolean> {

        return ResponseEntity.ok(
            likeService.save(diaryId, memberDTO.userId)
        )
    }

    @GetMapping("/{diaryId}")
    fun getLike(
        @PathVariable diaryId: Long,
        @AuthenticationPrincipal memberDTO: MemberDTO
    ): ResponseEntity<Boolean> {

        return ResponseEntity.ok(
            likeService.read(diaryId, memberDTO.userId)
        )
    }


}