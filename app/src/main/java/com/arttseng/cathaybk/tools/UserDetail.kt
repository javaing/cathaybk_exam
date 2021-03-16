package com.arttseng.cathaybk.tools

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetail (
    val id : Int,
    val name : String?,
    val login : String,
    val avatar_url : String,
    val site_admin : Boolean,
    val bio : String?=null,
    val location : String?,
    val blog : String?,
)
