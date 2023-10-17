package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@Table(name = "TBL_DIARY_TAG")
@AllArgsConstructor
@NoArgsConstructor
data class DiaryTag(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//    @ManyToOne
    @JoinColumn(name = "REF_DIARY_ID")
    var diary: Diary?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], targetEntity = Tag::class)
    @JoinColumn(name = "REF_TAG_ID")
    var tag: Tag? = null

){
    override fun toString(): String {
        return "DiaryTag(id=$id, diary=$diary, tag=$tag)"
    }
}
