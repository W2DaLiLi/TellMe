package com.example.hzxr.tellme.Util

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by Hzxr on 2018/2/1.
 */
open class TextWatcherHelper : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {}

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}