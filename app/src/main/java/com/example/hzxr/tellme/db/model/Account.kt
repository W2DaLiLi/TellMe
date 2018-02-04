package com.example.hzxr.tellme.db.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

/**
 * Created by Hzxr on 2018/1/20.
 */
@Entity
data class Account(
        @Id
        var id: Long = 0,
        var username: String,
        var nickname: String? = null,
        var email: String? = null,
        var role: String,

        var friends: List<Member>? = null
        ){
        constructor() : this(0,"",null,null,"user", listOf())
}