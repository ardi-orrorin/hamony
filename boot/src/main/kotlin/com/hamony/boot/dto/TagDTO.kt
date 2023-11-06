package com.hamony.boot.dto

data class TagDTO(

    var id: Long? = null,

    var tag: String = "",

){
    override fun toString(): String {
        return "Tag(id=$id, tag='$tag')"
    }
}
