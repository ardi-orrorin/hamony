package com.hamony.boot.dto

import co.elastic.clients.elasticsearch.security.get_token.AccessTokenGrantType

data class TokenDTO(
    var userId: String,
    var tokenType: String,
    var token: String,
    var expiresIn: Long,
){
    override fun toString(): String {
        return "TokenDTO(userId='$userId', expiresIn=$expiresIn)"
    }
}
