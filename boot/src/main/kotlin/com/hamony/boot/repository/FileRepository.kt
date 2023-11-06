package com.hamony.boot.repository

import com.hamony.boot.entity.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository: JpaRepository<File, Long> {
}