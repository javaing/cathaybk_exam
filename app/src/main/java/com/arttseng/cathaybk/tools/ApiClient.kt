package com.arttseng.screenrecorder.tools

import com.arttseng.cathaybk.tools.Const
import com.arttseng.cathaybk.tools.UserDetail
import com.arttseng.cathaybk.tools.UserInfo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {
    @GET(Const.GetAllUsers) fun getUserList(): Deferred<Response<List<UserInfo>>>

    @GET(Const.GetUserDetail)
    fun getUserDetail(
            @Path("name") name: String,
    ): Deferred<Response<UserDetail>>

}