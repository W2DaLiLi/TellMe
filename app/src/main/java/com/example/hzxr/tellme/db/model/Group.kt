package com.example.hzxr.tellme.db.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

/**
 * Created by Hzxr on 2018/1/20.
 */
@Entity
data class Group(
        @Id
        var id: Long = 0,
        var name: String,
        var description: String? = null,
        var admin: String? = null,
        var members: List<Member>? = null
) {
        constructor() : this(0, "", null, null, listOf())
}