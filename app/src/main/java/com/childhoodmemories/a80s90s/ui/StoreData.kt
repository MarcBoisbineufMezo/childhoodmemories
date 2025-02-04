package com.childhoodmemories.a80s90s.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object StoreData {
    private val Context.storeData: DataStore<Preferences> by preferencesDataStore(name = "data")
    suspend fun storeImage(context: Context, value: String, id: String) {
        context.storeData.edit { preferences ->
            preferences[stringPreferencesKey(id)] = value
        }
    }

    suspend fun getImage(context: Context, id: String): Flow<String?> {
        return context.storeData.data.map { preferences ->
            preferences[stringPreferencesKey(id)]
        }
    }
}
