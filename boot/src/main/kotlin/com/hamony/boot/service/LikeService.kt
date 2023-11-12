package com.hamony.boot.service

import com.hamony.boot.entity.Like
import com.hamony.boot.exception.NotFoundException
import com.hamony.boot.repository.LikeRepository
import com.hamony.boot.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class LikeService(
    val memberRepository: MemberRepository,
    val likeRepository: LikeRepository,
) {


    @Transactional
    fun save(diaryId: Long, userId: String): Boolean {

        val memberId: Long = memberRepository.findByUserId(userId).orElseThrow {
            NotFoundException("회원을 찾을 수 없습니다.")
        }.id!!

        likeRepository.findByRefDiaryIdAndRefMemberId(diaryId, memberId).let {
            if(it != null) {
                likeRepository.delete(it)
                return false
            }
        }

        val likeEntity: Like = Like(refDiaryId = diaryId, refMemberId = memberId)
        likeRepository.save(likeEntity)
        return true
    }

    fun read(diaryId: Long, userId: String): Boolean {

        val memberId: Long = memberRepository.findByUserId(userId).orElseThrow {
            NotFoundException("회원을 찾을 수 없습니다.")
        }.id!!

        return likeRepository.existsByRefDiaryIdAndRefMemberId(diaryId, memberId)
    }


}