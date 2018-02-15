package com.example.hzxr.tellme.net.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by Hzxr on 2018/2/15.
 */
@Root(name = "users", strict = false)
data class Users (
    @ElementList(required = true, inline = true, entry = "user")
    val users: List<User>
)