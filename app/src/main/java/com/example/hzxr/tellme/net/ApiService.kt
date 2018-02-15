package com.example.hzxr.tellme.net

import com.example.hzxr.tellme.db.model.Account
import com.example.hzxr.tellme.net.model.Users
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Hzxr on 2018/2/15.
 */
interface ApiService {
    //获取服务器上所有用户
    @GET("users")
    fun getAllUser(): Call<Users>
}