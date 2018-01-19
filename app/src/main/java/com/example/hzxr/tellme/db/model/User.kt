package com.example.hzxr.tellme.db.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Hzxr on 2018/1/19.
 */
@Entity data class User (
    @Id var id: Long = 0,
    var username: String? = null,
    var password: String? = null
)