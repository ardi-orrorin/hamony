package com.hamony.boot.repository

import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.DiaryTag
import org.springframework.data.jpa.repository.JpaRepository

interface DiaryTagRepository: JpaRepository<DiaryTag, Long> {

    fun findAllByDiary(diary: Diary): MutableList<DiaryTag>
}