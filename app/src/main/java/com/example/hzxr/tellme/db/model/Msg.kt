package com.example.hzxr.tellme.db.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Hzxr on 2018/1/20.
 */
@Entity
data class Msg(
        @Id
        var id: Long,
        var type: String,
        var to: String,
        var from: String,
        var content: String,
        var data: String
)