package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.ArrayList

@Entity
@Table(name = "TBL_TAG")
@AllArgsConstructor
@NoArgsConstructor
data class Tag(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null,

    @Column(name = "TAG")
    var tag: String = "",

    @JoinColumn(name = "REF_TAG_ID")
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var diaryTagList: MutableList<DiaryTag> = mutableListOf(),

){

    override fun toString(): String {
        return "Tag(id=$id, tag='$tag', diaryTagList=$diaryTagList)"
    }
}
