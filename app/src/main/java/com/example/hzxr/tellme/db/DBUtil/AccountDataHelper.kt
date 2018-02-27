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
    @Volatile
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

    fun removeAll(boxStore: BoxStore) {
        val accountBox = boxStore.boxFor(Account::class.java)
        accountBox.removeAll()
    }

    fun loadAccountFriend(boxStore: BoxStore, member: Member): Boolean {
        val account = currentAccount ?: return false
        if (account.friends.contains(member)) return false
        val accountBox = boxStore.boxFor(Account::class.java)
        account.friends.add(member)
        accountBox.put(account)
        return true
    }

    private fun mapToAccountObject(map: Map<String, Any?>): Account {
        val account = Account()
        account.username = map["username"] as String
        account.nickname = map["nickname"] as String?
        account.email = map["email"] as String
        account.role = map["role"] as String
        if (map["friends"] != null)
            account.friends.addAll(map["friends"] as List<Member>)
        return account
    }
}