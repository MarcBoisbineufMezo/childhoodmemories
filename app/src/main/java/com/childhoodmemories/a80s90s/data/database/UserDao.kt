package com.childhoodmemories.a80s90s.data.database

import androidx.core.content.edit
import com.childhoodmemories.a80s90s.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserDao() {
    private val sharedPreferences = MemoSharedPreferences.sharedPref

    private val gson: Gson = Gson()

    var currentUser: User?
        get() {
            val result = sharedPreferences?.getString(MEMO_USER_DATA, null)
            return result?.let { gson.fromJson(it, User::class.java) }
        }
        set(value) {
            sharedPreferences?.edit { putString(MEMO_USER_DATA, gson.toJson(value)) }
        }

    var userList: List<User>
        get() {
            val json = sharedPreferences?.getString(MEMO_USER_LIST, null)
            return if (json != null) {
                val type = object : TypeToken<List<User>>() {}.type
                gson.fromJson(json, type)
            } else {
                emptyList()
            }
        }
        set(value) {
            val json = gson.toJson(value)
            sharedPreferences?.edit()?.putString(MEMO_USER_LIST, json)?.apply()
        }

    companion object {
        private const val MEMO_USER_LIST = "MEMO_USER_LIST"
        private const val MEMO_USER_DATA = "MEMO_USER_DATA"
    }

}