package com.example.hzxr.tellme.Util

import android.databinding.BindingAdapter
import android.support.annotation.MenuRes
import android.support.v7.widget.Toolbar

/**
 * Created by Hzxr on 2018/2/7.
 */

@BindingAdapter("menu")
fun setMenuId(toolbar: Toolbar, @MenuRes oldMenuId: Int, @MenuRes newMenuId: Int) {
    if (oldMenuId != newMenuId) {
        toolbar.menu.clear()
        toolbar.inflateMenu(newMenuId)
    }
}
