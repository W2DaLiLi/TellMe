package com.example.hzxr.tellme

import android.app.Application
import com.example.hzxr.tellme.db.model.MyObjectBox
import com.facebook.drawee.backends.pipeline.Fresco
import io.objectbox.BoxStore

/**
 * Created by Hzxr on 2018/1/19.
 */
class TellMeApp : Application() {

    lateinit var boxStore: BoxStore
        private set

    override fun onCreate() {
        super.onCreate()
        boxStore = MyObjectBox.builder().androidContext(this).build()
        Fresco.initialize(this)
    }

}