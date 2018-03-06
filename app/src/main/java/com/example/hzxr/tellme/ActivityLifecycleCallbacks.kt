package com.example.hzxr.tellme

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import java.util.*

/**
 * Created by Hzxr on 2018/3/6.
 */
class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    private val activityList = Collections.synchronizedList(mutableListOf<Activity>())

    override fun onActivityCreated(activity: Activity?, p1: Bundle?) {
        Log.d("TAG", "LifecycleListener: activity onCreate")
        activityList.add(activity)
    }

    override fun onActivityStarted(p0: Activity?) {
    }

    override fun onActivityResumed(p0: Activity?) {
    }

    override fun onActivityPaused(p0: Activity?) {
    }

    override fun onActivityStopped(p0: Activity?) {
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
        Log.d("TAG", "LifecycleListener: activity onDestroy")
        activityList.remove(activity)
    }

    fun currentActivity(): Activity? {
        return if (activityList.isNotEmpty()) activityList[0] else null
    }

}