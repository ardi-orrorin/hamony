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

){
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "tag")
    var diaryList: MutableList<DiaryTag> = mutableListOf();

    override fun toString(): String {
        return "Tag(id=$id, tag='$tag', diaryList=${diaryList.forEach(::println)})"
    }
}
