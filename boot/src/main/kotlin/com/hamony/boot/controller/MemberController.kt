package com.hamony.boot.controller

import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.dto.TokenDTO
import com.hamony.boot.dto.response.LoginDTO
import com.hamony.boot.dto.response.ResponseDTO
import com.hamony.boot.service.MemberService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
class MemberController(
    val memberService: MemberService,
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("idchk")
    fun idDuplicateChk(@RequestBody userId: String): ResponseEntity<ResponseDTO<Int>> {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "userId", userId
        )
        return ResponseEntity.ok(
            ResponseDTO(HttpStatus.OK.value(), memberService.idDuplicateChk(userId))
        )
    }

    @PostMapping("signin")
    fun signIn(@RequestBody memberDTO: MemberDTO): ResponseEntity<ResponseDTO<Boolean>> {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )

        memberService.signIn(memberDTO)

        return ResponseEntity.ok(
            ResponseDTO<Boolean>(HttpStatus.CREATED.value(),true)
        );
    }

    @PostMapping("login")
    fun login(@RequestBody loginDTO: LoginDTO): ResponseEntity<ResponseDTO<TokenDTO>> {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "loginDTO", loginDTO
        )
        return ResponseEntity.ok()
            .body(
                ResponseDTO(HttpStatus.OK.value(), memberService.login(loginDTO))
            )
    }

    @PutMapping("edit")
    fun editProfile(@RequestBody memberNew: MemberDTO,
                    @AuthenticationPrincipal memberDTO: MemberDTO
    ): ResponseEntity<ResponseDTO<Boolean>> {

        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberNew", memberNew
        )
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )

        return ResponseEntity.ok(
            ResponseDTO(
                HttpStatus.OK.value(),
                memberService.editProfile(memberNew, memberDTO)
            )
        )
    }

    @DeleteMapping("delete")
    fun deleteMember(
        @AuthenticationPrincipal memberDTO: MemberDTO
    ): ResponseEntity<Boolean> {
        log.info("[{}]({}) : {}: {}",
            object{}.javaClass.enclosingClass.name,
            object{}.javaClass.enclosingMethod.name,
            "memberDTO", memberDTO
        )

        return ResponseEntity.ok(
            memberService.deleteMember(memberDTO)
        )
    }

}