package com.example.hzxr.tellme.UI.VM

import android.app.Activity
import android.databinding.BaseObservable

/**
 * Created by Hzxr on 2018/1/20.
 */

open class BaseViewModel<T>(protected var activity: Activity, protected var binding: T) : BaseObservable()
