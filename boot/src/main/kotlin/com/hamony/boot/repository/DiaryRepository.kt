package com.hamony.boot.repository

import com.hamony.boot.entity.Diary
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DiaryRepository: JpaRepository<Diary, Long> {

    fun findByIdAndDeleteAtIsNull(id: Long): Diary?

    @Query(value = "SELECT d FROM Diary d " +
            "WHERE d.deleteAt IS NULL AND d.subject LIKE concat('%' ,:subject, '%') " +
            "Or d.deleteAt IS NULL AND d.content LIKE concat('%' ,:content, '%')"
    )
    fun findSearch(subject: String, content: String, pageable: Pageable): List<Diary>

    fun findAllByDeleteAtIsNullAndAndSubjectContainsIgnoreCaseOrDeleteAtIsNullAndContentContainsIgnoreCase(subject: String, content: String, pageable: Pageable): List<Diary>
    fun findAllByMemberId(id: Long): MutableList<Diary>

    fun findAllByDeleteAtIsNull(pageable: Pageable): Page<Diary>

}