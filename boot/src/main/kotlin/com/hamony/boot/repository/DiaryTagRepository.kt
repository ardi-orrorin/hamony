package com.hamony.boot.repository

import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.DiaryTag
import com.hamony.boot.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface DiaryTagRepository: JpaRepository<DiaryTag, Long> {

    fun findAllByDiary(diary: Diary): MutableList<DiaryTag>
    fun findAllByTag(diaryTag: Tag): MutableList<DiaryTag>
}