package com.hamony.boot.controller

import com.hamony.boot.dto.*
import com.hamony.boot.dto.request.ReqDiaryDTO
import com.hamony.boot.dto.response.ResponseDTO
import com.hamony.boot.dto.response.ResponseDairyDTO
import com.hamony.boot.service.*
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File


@RestController
@RequestMapping("/diary")
class DiaryController(
    val diaryService: DiaryService,
    val urlService: UrlService,
    val tagService: TagService,
    val fileService: FileService,
) {
    val log = LoggerFactory.getLogger(this.javaClass)!!

    @PostMapping("write")
    fun write(
        @RequestPart(name = "diary") reqDiaryDTO: ReqDiaryDTO,
        @RequestPart(name = "file", required = false) file: MultipartFile?,
        @AuthenticationPrincipal memberDTO: MemberDTO
    ): ResponseEntity<ResponseDTO<Long>> {

        log.info("[DiaryController](write) diaryTagDTO : {}", reqDiaryDTO)
        log.info("[DiaryController](write) memberDTO : {}", memberDTO)
        log.info("[DiaryController](write) file : {}", file)

        return ResponseEntity.ok(
            ResponseDTO(HttpStatus.CREATED.value(),
                diaryService.save(reqDiaryDTO, memberDTO, file)
            )
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
    ): ResponseEntity<ResponseDairyDTO> {

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

        val diaryDTO: DiaryDTO = diaryService.findByIdDTO(id.toLong())

        val urlDTO: MutableList<UrlDTO> = urlService.findByDiaryId(id.toLong())

        val tagDTO: MutableList<TagDTO> = tagService.findByDairyId(id.toLong())

        val file: String = fileService.findByDiaryId(id.toLong())




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

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "tagDTO", tagDTO
        )

        return ResponseEntity.ok(
                ResponseDairyDTO(
                    HttpStatus.OK.value(),
                    diaryDTO, urlDTO, tagDTO, file
                )
        )
    }
}