package com.hamony.boot.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Entity
@Table(name = "TBL_FILE")
@AllArgsConstructor
@NoArgsConstructor
data class File(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null,

    @Column(name = "PATH")
    var path: String = "",

    @Column(name = "NAME")
    var name: String = "",

    @Column(name = "EXT")
    var ext: String = "",

    @Column(name = "CREATEAT")
    var createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "DELETEAT")
    var deleteAt: LocalDateTime? = null,

    @Column(name = "AVAIL")
    var avail: Boolean? = true,

    @ManyToOne
    @JoinColumn(name = "REF_DIARY_ID")
    var diary: Diary?
){
    override fun toString(): String {
        return "File(id=$id, path='$path', name='$name', ext='$ext', createAt=$createAt, deleteAt=$deleteAt, avail=$avail, diary=$diary)"
    }
}
