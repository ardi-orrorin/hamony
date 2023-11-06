package com.hamony.boot.repository

import com.hamony.boot.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TagRepository: JpaRepository<Tag, Long> {

    fun findByTagEqualsIgnoreCase(tag: String): Tag?


    fun existsByTagEqualsIgnoreCase(tag: String): Boolean
}