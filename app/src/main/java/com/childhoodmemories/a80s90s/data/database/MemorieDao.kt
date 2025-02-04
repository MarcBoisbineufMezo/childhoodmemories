package com.childhoodmemories.a80s90s.data.database

import com.childhoodmemories.a80s90s.model.Memory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MemorieDao {
    private val sharedPreferences = MemoSharedPreferences.sharedPref

    private val gson: Gson = Gson()

    var memories: List<Memory>
        get() {
            val json = sharedPreferences?.getString(MEMO_MEMO_LIST, null)
            return if (json != null) {
                val type = object : TypeToken<List<Memory>>() {}.type
                gson.fromJson(json, type)
            } else {
                emptyList()
            }
        }
        set(value) {
            val json = gson.toJson(value)
            sharedPreferences?.edit()?.putString(MEMO_MEMO_LIST, json)?.apply()
        }

    fun loadLikedMemories(idUser: String): List<Memory> {
        val json = sharedPreferences?.getString(idUser, null)
        return if (json != null) {
            val type = object : TypeToken<List<Memory>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun saveLikedMemories(idUser: String, memories: List<Memory>) {
        val json = gson.toJson(memories)
        sharedPreferences?.edit()?.putString(idUser, json)?.apply()
    }

    companion object {
        private const val MEMO_MEMO_LIST = "MEMO_MEMO_LIST"
    }
}