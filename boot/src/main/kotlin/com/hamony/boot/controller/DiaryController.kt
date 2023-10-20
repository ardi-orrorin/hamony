package com.hamony.boot.controller

import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.DiaryTagDTO
import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.entity.DiaryTag
import com.hamony.boot.response.ResponseDTO
import com.hamony.boot.service.DiaryService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/diary")
class DiaryController(
    val diaryService: DiaryService,
) {
    val log = LoggerFactory.getLogger(this.javaClass)!!
    @PostMapping("write")
    fun write(@RequestBody diaryTagDTO: DiaryTagDTO,
              @AuthenticationPrincipal memberDTO: MemberDTO
              ): ResponseEntity<ResponseDTO<Boolean>> {

        log.info("[DiaryController](write) diaryTagDTO : {}", diaryTagDTO)
        log.info("[DiaryController](write) memberDTO : {}", memberDTO)

        diaryService.save(diaryTagDTO, memberDTO)

        return ResponseEntity.ok(
            ResponseDTO(HttpStatus.OK.value(), true)
        )
    }
}