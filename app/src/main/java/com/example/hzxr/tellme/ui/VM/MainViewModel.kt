package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.util.Log
import android.view.View
import com.example.hzxr.tellme.Util.ActivitysUtil
import com.example.hzxr.tellme.databinding.ActivityMainBinding
import com.example.hzxr.tellme.net.RetrofitManager
import com.example.hzxr.tellme.net.ApiService
import com.example.hzxr.tellme.net.ConnectManager
import com.example.hzxr.tellme.net.model.Users
import io.objectbox.android.AndroidScheduler
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jivesoftware.smack.roster.Roster
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
            Thread {
                val roster = Roster.getInstanceFor(ConnectManager.getConnect())
                val entries = roster.entries
                for (item in entries) {
                    Log.d("TAG", "name:" + item.name + "groups" + item.groups + "type:" + item.type)
                }
            }.start()
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