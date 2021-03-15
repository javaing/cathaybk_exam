package com.arttseng.cathaybk.tools

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfo (
    val id : Int,
    val login : String,
    val avatar_url : String,
    var site_admin : Boolean,
    //var MobileNumber : String?=null,
    //val Status : Int
)
