package com.example.hzxr.tellme.db.DBUtil

import com.example.hzxr.tellme.db.model.Group
import com.example.hzxr.tellme.db.model.Group_
import com.example.hzxr.tellme.db.model.Member
import io.objectbox.BoxStore

/**
 * Created by Hzxr on 2018/2/25.
 */
object GroupDataHelper {

    fun add(boxStore: BoxStore, data: Map<String, Any?>): Boolean {
        if (data.isEmpty()) return false
        val groupBox = boxStore.boxFor(Group::class.java)
        groupBox.put(mapToGroupObject(data))
        return true
    }

    fun delete(boxStore: BoxStore, name: String) {
        val groupBox = boxStore.boxFor(Group::class.java)
        val target = queryGroupByName(boxStore, name) ?: return
        groupBox.remove(target)
    }

    private fun queryGroupByName(boxStore: BoxStore, name: String): Group? {
        val groupBox = boxStore.boxFor(Group::class.java)
        return groupBox.query().equal(Group_.name, name).build().findFirst()
    }

    private fun mapToGroupObject(data: Map<String, Any?>): Group {
        return Group(name = data["name"] as String,
                description = data["description"] as String?,
                admin = data["admin"] as String?)
    }
}