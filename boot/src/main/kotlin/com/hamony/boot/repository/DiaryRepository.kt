package com.hamony.boot.repository

import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface DiaryRepository: JpaRepository<Diary, Long> {

    fun findByIdAndDeleteAtIsNull(id: Long): Diary?

    fun findAllBySubjectContainsOrContentContainsAndDeleteAtIsNull(subject: String?, content: String?, pageable: Pageable): MutableList<Diary>
    fun findAllByMemberId(id: Long): MutableList<Diary>

    fun findAllByDeleteAtIsNull(pageable: Pageable): Page<Diary>

}