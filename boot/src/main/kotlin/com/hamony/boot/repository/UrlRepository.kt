package com.hamony.boot.repository

import com.hamony.boot.entity.Url
import org.springframework.data.jpa.repository.JpaRepository

interface UrlRepository: JpaRepository<Url, Long> {
}