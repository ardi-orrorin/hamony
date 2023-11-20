package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.proxy.HibernateProxy

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
    @JoinColumn(name = "REF_DIARY_ID")
    var diary: Diary? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "REF_TAG_ID")
    var tag: Tag? = null

){
    override fun toString(): String {
        return "DiaryTag(id=$id, diary=$diary, tag=$tag)"
    }
}
