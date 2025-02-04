package com.childhoodmemories.a80s90s.data.database

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("StaticFieldLeak")
object MemoSharedPreferences {
    var context: Context? = null

    val sharedPref: SharedPreferences?
        get() = context?.getSharedPreferences("MemoSharedPreferences", Context.MODE_PRIVATE)

}