package com.hamony.boot.repository

import com.hamony.boot.entity.FavDiary
import com.hamony.boot.entity.Member
import org.springframework.data.jpa.repository.JpaRepository


interface FavoriteRepository: JpaRepository<FavDiary, Long> {
    fun findAllByMember(member: Member): MutableList<FavDiary>
}