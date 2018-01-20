package com.example.hzxr.tellme.db.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Hzxr on 2018/1/20.
 */
@Entity
data class Member(
        @Id
        var username: String,
        var nickname: String,
        var parentId: String
)