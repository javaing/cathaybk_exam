package com.arttseng.cathaybk.tools

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
    @GET(Const.GetAllUsers) fun getUserList(): Deferred<Response<List<UserInfo>>>

    @GET(Const.GetUserDetail)
    fun getUserDetail(
            @Path("name") name: String,
    ): Deferred<Response<UserDetail>>

}