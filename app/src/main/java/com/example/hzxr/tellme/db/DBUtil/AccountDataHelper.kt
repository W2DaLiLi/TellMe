package com.example.hzxr.tellme.db.DBUtil

import android.content.Context
import com.example.hzxr.tellme.db.model.Account
import com.example.hzxr.tellme.db.model.Account_
import com.example.hzxr.tellme.db.model.Member
import io.objectbox.BoxStore
import io.objectbox.relation.ToMany

/**
 * Created by Hzxr on 2018/1/21.
 */
object AccountDataHelper {

    var currentAccount: Account? = null
        private set

    fun setCurrentAccountByName(boxStore: BoxStore, username: String) {
        currentAccount = queryAccountByUsername(boxStore, username)
    }

    fun add(boxStore: BoxStore, map: Map<String, Any?>): Boolean {
        if (map.isEmpty()) return false
        val accountBox = boxStore.boxFor(Account::class.java)
        accountBox.put(mapToAccountObject(map))
        return true
    }

    fun add(boxStore: BoxStore, account: Account): Boolean {
        val accountBox = boxStore.boxFor(Account::class.java)
        accountBox.put(account)
        return true
    }

    fun delete(boxStore: BoxStore, username: String) {
        val accountBox = boxStore.boxFor(Account::class.java)
        val account = queryAccountByUsername(boxStore, username) ?: return
        accountBox.remove(account)
    }

    fun queryAccountByUsername(boxStore: BoxStore, username: String): Account? {
        val accountBox = boxStore.boxFor(Account::class.java)
        return accountBox.query().equal(Account_.username, username).build().findFirst()
    }

    private fun mapToAccountObject(map: Map<String, Any?>): Account {
        val account = Account(username = map["username"] as String,
                nickname = map["nickname"] as String?,
                email = map["email"] as String,
                role = map["role"] as String,
                friends = listOf())
        if (map["friends"] != null)
            account.friends.toMutableList().addAll(map["friends"] as List<Member>)
        return account
    }
}