package com.arttseng.screenrecorder.tools

import com.arttseng.cathaybk.Const
import com.arttseng.cathaybk.tools.UserInfo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {
    @GET(Const.GetAllUsers) fun getUserList(): Deferred<Response<List<UserInfo>>>

    //UpdateStatus?id=3&MobileNumber=4341231
    //@FormUrlEncoded
    @PUT(Const.UpdateStatusAPI)
    fun updateStatus(
        @Query("id") id: Int,
        @Query("MobileNumber") MobileNumber: String,
        @Query("device") device: String,
        @Query("version") version: String
    ): Deferred<Response<Void>>


}