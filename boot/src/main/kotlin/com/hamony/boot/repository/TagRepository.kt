package com.hamony.boot.repository

import com.hamony.boot.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository: JpaRepository<Tag, Long> {

    fun findByTagExists(tag: String): Boolean
}