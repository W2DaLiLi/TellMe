package com.example.hzxr.tellme.net.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

/**
 * Created by Hzxr on 2018/2/15.
 */
@Root(name = "user", strict = false)
data class User(
        @Attribute(name = "username", required = true)
        val username: String
)