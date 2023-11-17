package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@Table(name = "TBL_FAV_DIARY")
@AllArgsConstructor
@NoArgsConstructor
data class FavDiary(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "REF_DIARY_ID")
    var diary: Diary? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "REF_MEMBER_ID")
    var member: Member? = null
){
    override fun toString(): String {
        return "FavDiary(id=$id, diary=$diary, member=$member)"
    }
}
