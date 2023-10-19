package com.hamony.boot.response

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
data class LoginDTO(
    var userId: String,
    var userPwd: String,
)
