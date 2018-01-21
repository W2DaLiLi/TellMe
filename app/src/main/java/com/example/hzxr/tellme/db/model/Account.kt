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
        var username: String,
        var nickname: String,
        var email: String,
        var role: String,

        var friends: ToMany<Member>
        )