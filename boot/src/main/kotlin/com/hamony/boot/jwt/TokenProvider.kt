package com.hamony.boot.jwt


import com.hamony.boot.dto.TokenDTO
import com.hamony.boot.entity.Member
import com.hamony.boot.exception.TokenException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.security.Key
import java.security.PrivateKey
import java.util.*
import javax.crypto.SecretKey
import kotlin.math.log

@Component
class TokenProvider(
    @Lazy private val userDetailsService: UserDetailsService
) {

    @Value("\${jwt.grantType}")
    private val GRANT_TYPE: String = ""

//    @Value(value = "\${jwt.secretKey}")
//    private val secretKey: String = ""

//    private val key: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    private val key: SecretKey = Jwts.SIG.HS512.key().build()

    private final val EXPIRE_IN: Long = 1000 * 60 * 60 // 60분

    fun generateTokenDTO(member: Member): TokenDTO {

        val expiration: Date = Date(System.currentTimeMillis() + EXPIRE_IN)

        val accessToken: String = Jwts.builder()
            .subject(member.userId)
            .expiration(expiration)
            .signWith(key)
            .compact()

        return TokenDTO(member.userId, GRANT_TYPE, accessToken, expiration.time);
    }

    fun getUserId(token: String): String? = Jwts.parser()
        .verifyWith(key).build()
        .parseSignedClaims(token)
        .payload.subject

    fun validateToken(token: String): Boolean {
        println(token)
        try {
            Jwts.parser().verifyWith(key).build().parse(token)
            return true
        } catch (e: Exception){
            when (e) {
                is SecurityException -> throw TokenException("잘못된 JWT서명")
                is ExpiredJwtException -> throw TokenException("만료된 JWT토큰")
                is UnsupportedJwtException -> throw TokenException("지원되지 않는 JWT토큰")
                is IllegalArgumentException -> throw TokenException("잘못된 JWT")
                else -> throw TokenException("사용할 수 없는 JWT")
            }
        }
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(getUserId(token))

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }
}