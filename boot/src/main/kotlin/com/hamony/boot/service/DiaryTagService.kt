package com.hamony.boot.service

import com.hamony.boot.repository.DiaryTagRepository
import org.springframework.stereotype.Service


@Service
class DiaryTagService(
    val diaryTagRepository: DiaryTagRepository
) {

}
