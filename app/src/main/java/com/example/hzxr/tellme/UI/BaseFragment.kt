package com.example.hzxr.tellme.UI

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by Hzxr on 2018/2/5.
 */
open class BaseFragment : Fragment() {
    protected lateinit var activity: Activity

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        this.activity = activity ?: return
    }
}