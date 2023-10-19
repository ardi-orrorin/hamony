package com.hamony.boot.controller

import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.dto.TokenDTO
import com.hamony.boot.response.LoginDTO
import com.hamony.boot.response.ResponseDTO
import com.hamony.boot.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
class MemberController(
    val memberService: MemberService,
) {

    @PostMapping("signin")
    fun signIn(@RequestBody memberDTO: MemberDTO): ResponseEntity<ResponseDTO<Boolean>> {
        memberService.signIn(memberDTO)

        return ResponseEntity.ok(
            ResponseDTO<Boolean>(HttpStatus.CREATED.value(),true)
        );
    }

    @PostMapping("login")
    fun login(@RequestBody loginDTO: LoginDTO): ResponseEntity<ResponseDTO<TokenDTO>> {
        return ResponseEntity.ok()
            .body(
                ResponseDTO(HttpStatus.OK.value(), memberService.login(loginDTO))
            )
    }
}