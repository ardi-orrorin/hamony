package com.hamony.boot.repository

import com.hamony.boot.entity.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository: JpaRepository<Like, Long> {

    fun findByRefDiaryIdAndRefMemberId(diaryId: Long, memberId: Long): Like?
    fun existsByRefDiaryIdAndRefMemberId(diaryId: Long, memberId: Long): Boolean

}