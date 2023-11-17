package com.hamony.boot.controller

import com.hamony.boot.dto.TagDTO
import com.hamony.boot.dto.response.ResponseDTO
import com.hamony.boot.service.DiaryTagService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/tag")
@RestController
class TagControllor(
    val diaryTagService: DiaryTagService,
) {

    private val log = LoggerFactory.getLogger(this.javaClass)!!
    @GetMapping("{keyword}")
    fun dairyFindAllByTag(
        @PathVariable keyword: String,
    ): ResponseEntity<ResponseDTO<TagDTO>> {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "keyword", keyword
        )

        return ResponseEntity.ok(
            ResponseDTO(HttpStatus.OK.value(), diaryTagService.findAllByTag(keyword))
        )
    }
}