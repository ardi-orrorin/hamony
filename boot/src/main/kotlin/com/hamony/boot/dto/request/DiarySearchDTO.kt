package com.hamony.boot.dto.request

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

data class DiarySearchDTO (
    var subject: String?,
    var content: String?,
) {
    override fun toString(): String {
        return "DiarySearchDTO(subject='$subject', content='$content')"
    }
}
