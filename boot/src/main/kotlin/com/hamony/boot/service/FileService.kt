package com.hamony.boot.service

import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.File
import com.hamony.boot.exception.NotFoundException
import com.hamony.boot.file.ResizeCriteria
import com.hamony.boot.file.ResizeImage
import com.hamony.boot.repository.DiaryRepository
import com.hamony.boot.repository.FileRepository
import org.springframework.stereotype.Service

@Service
class FileService (
    val fileRepository: FileRepository,
    val diaryRepository: DiaryRepository,
    val resizeImage: ResizeImage,
) {

    fun findByDiaryId(diaryId: Long): String {
        val diary: Diary = diaryRepository.findById(diaryId).orElseThrow {
            NotFoundException("데이터를 찾을 수 없습니다.")
        }

        val file: File? = fileRepository.findByDiary(diary)

        if(file == null) {
            return ""
        } else {
            return "/api/file/media/image/${diary.member!!.id}/${file.name}.${file.ext}"
        }


    }
}