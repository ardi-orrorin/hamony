package com.hamony.boot.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

data class MemberDTO (

    var id: Long?,

    var userId: String = "",

    var userPwd: String = "",

    var email: String = "",

    var createAt: LocalDateTime = LocalDateTime.now(),

    var deleteAt: LocalDateTime?,

): UserDetails {
    override fun getUsername(): String = this.userId
    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = false

    override fun toString(): String {
        return "Member(id=$id, userId='$userId', userPwd='$userPwd', email='$email', createAt=$createAt, deleteAt=$deleteAt)"
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getPassword(): String = this.userPwd
}
