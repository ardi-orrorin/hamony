package com.hamony.boot.controller

import com.hamony.boot.dto.DiaryDTO
import com.hamony.boot.dto.DiaryTagDTO
import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.dto.request.ReqDiaryDTO
import com.hamony.boot.dto.response.ResponseDTO
import com.hamony.boot.service.DiaryService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/diary")
class DiaryController(
    val diaryService: DiaryService,
) {
    val log = LoggerFactory.getLogger(this.javaClass)!!

    @PostMapping("write")
    fun write(
        @RequestPart(name = "diary") reqDiaryDTO: ReqDiaryDTO,
        @RequestPart(name = "file", required = false) file: MultipartFile?,
        @AuthenticationPrincipal memberDTO: MemberDTO
    ): ResponseEntity<ResponseDTO<Boolean>> {

        log.info("[DiaryController](write) diaryTagDTO : {}", reqDiaryDTO)
        log.info("[DiaryController](write) memberDTO : {}", memberDTO)
        log.info("[DiaryController](write) file : {}", file)


        diaryService.save(reqDiaryDTO, memberDTO, file)

        return ResponseEntity.ok(
            ResponseDTO(HttpStatus.CREATED.value(), true)
        )
    }

    @GetMapping("recent")
    fun recent(@AuthenticationPrincipal memberDTO: MemberDTO,
               pageable: Pageable
    ): ResponseEntity<ResponseDTO<List<DiaryDTO?>>> {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "pageable", pageable
        )

        var pageNumber: Int = pageable.pageNumber
        if (pageable.pageNumber <= 0 ) pageNumber = 0

        val pageInfo = PageRequest.of(pageNumber, pageable.pageSize, pageable.sort)


        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "pageInfo", pageInfo
        )

        return ResponseEntity.ok(
            ResponseDTO(HttpStatus.OK.value(), diaryService.recent(memberDTO, pageInfo))
        )
    }

    @GetMapping("search/{keyword}")
    fun searchDiary(
        @AuthenticationPrincipal memberDTO: MemberDTO,
        @PathVariable keyword: String,
        pageable: Pageable
    ): ResponseEntity<ResponseDTO<List<DiaryDTO>>> {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "keyword", keyword
        )

        var pageNumber: Int

        if(pageable.pageNumber <= 0) pageNumber = 0
        else pageNumber = pageable.pageNumber

        val pageableVar: Pageable = PageRequest.of(pageNumber, pageable.pageSize, pageable.sort)

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "pageableVar", pageableVar
        )

        return ResponseEntity.ok(
            ResponseDTO(
                HttpStatus.OK.value(),
                diaryService.search(keyword, pageableVar)
            )
        )
    }

    @GetMapping("{id}")
    fun findById(
        @PathVariable id:Int,
        @AuthenticationPrincipal memberDTO: MemberDTO
    ): ResponseEntity<ResponseDTO<DiaryDTO>> {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
            )

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "id", id
        )

        return ResponseEntity.ok(
            ResponseDTO(
                HttpStatus.OK.value(),
                diaryService.findByIdDTO(id.toLong())
            )
        )
    }
}