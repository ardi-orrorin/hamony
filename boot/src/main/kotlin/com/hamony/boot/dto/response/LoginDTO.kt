package com.hamony.boot.dto.response

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
data class LoginDTO(
    var userId: String,
    var userPwd: String,
)
