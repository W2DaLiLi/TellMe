package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.Util.ActivitysUtil
import com.example.hzxr.tellme.databinding.ActivityMainBinding
import com.example.hzxr.tellme.net.RetrofitManager
import com.example.hzxr.tellme.net.ApiService
import com.example.hzxr.tellme.net.model.Users
import io.objectbox.android.AndroidScheduler
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hzxr on 2018/1/20.
 */
class MainViewModel(activity: Activity, binding: ActivityMainBinding) : BaseViewModel<ActivityMainBinding>(activity, binding) {

    val testLoginActivityOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            Log.d("TAG", "test click button")
            ActivitysUtil.startActivityToLogin(activity)
        }

    val testRegisterActivityOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            ActivitysUtil.startActivityToRegister(activity)
        }

    val testDebugOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
//            val box = (activity.application as TellMeApp).boxStore
//            val account = AccountDatehelper.queryAccountByUsername(box, "000")
//            Log.d("TAG", account.toString())
            val retrofit = RetrofitManager.getInstance()?: return@OnClickListener
            val service = retrofit.create(ApiService::class.java)
            val observable = service.getAllUser()
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object :Observer<Users> {
                        override fun onComplete() {

                        }

                        override fun onNext(users: Users) {
                            for (item in users.userList)
                                Log.d("TAG", "Username: " + item.username)
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }

                        override fun onSubscribe(d: Disposable) {
                        }
                    })
//            val result = retrofit?.create(ApiService::class.java)
//            val allUsers = result?.getAllUser()?: return@OnClickListener
//            allUsers.enqueue(object :Callback<Users>{
//                override fun onResponse(call: Call<Users>?, response: Response<Users>?) {
//                    Log.d("TAG", "success: " )
//                    val users = response?.body()?.getUserList()?: return
//                    for (item in users)
//                        Log.d("TAG", item.getUsername())
//                }
//
//                override fun onFailure(call: Call<Users>?, t: Throwable?) {
//                    Log.d("TAG", "false: " + t.toString())
//                    t?.printStackTrace()
//                }
//            })
        }

    val testHomeOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            ActivitysUtil.startActivityToHome(activity)
        }

    val testAddFriendOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            ActivitysUtil.startActivityToAddFriend(activity)
        }
}