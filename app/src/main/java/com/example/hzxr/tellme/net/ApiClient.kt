package com.example.hzxr.tellme.net

import android.util.Log
import com.example.hzxr.tellme.net.model.User
import com.example.hzxr.tellme.net.model.Users
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.simpleframework.xml.core.Validate

/**
 * Created by Hzxr on 2018/2/16.
 */
object ApiClient {
    private val retrofit = RetrofitManager.getInstance()
    private val service = retrofit?.create(ApiService::class.java)

    fun getAllUserInServer() : Observable<Users>?{
        return service?.getAllUser()?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }


}