package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Entity
@Table(name = "TBL_URL")
@AllArgsConstructor
@NoArgsConstructor
data class Url(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null,

    @Column(name = "URL")
    var url: String = "",

    @Column(name = "CREATEAT")
    var createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "DELETEAT")
    var deleteAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "AVAIL")
    var avail: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "REF_DIARY_ID")
    var diary: Diary?
){
    override fun toString(): String {
        return "Url(id=$id, url='$url', createAt=$createAt, deleteAt=$deleteAt, avail=$avail, diary=$diary)"
    }
}
