package com.example.pokevault.data.local.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Singleton
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Singleton
class AppPreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

    private val lastApiCallTimeKey = longPreferencesKey("last_api_call_time")

    /**
     * Retrieves the timestamp of the last API call.
     */
    fun getLastApiCallTime(): Flow<Long> {
        return context.dataStore.data.map { preferences ->
            preferences[lastApiCallTimeKey] ?: 0L
        }
    }

    /**
     * Updates the timestamp of the last API call to the current system time.
     */
    suspend fun updateLastApiCallTime() {
        context.dataStore.edit { preferences ->
            preferences[lastApiCallTimeKey] = System.currentTimeMillis()
        }
    }

    /**
     * Checks whether the cache is still valid based on calendar day (Turkey timezone).
     * Cache is considered valid if the last API call was made on the same calendar day.
     *
     * @return true if the cache is still valid (same day), false otherwise.
     */
    suspend fun isCacheValid(): Boolean {
        val lastCallTime = context.dataStore.data.map { preferences ->
            preferences[lastApiCallTimeKey] ?: 0L
        }.first()

        if (lastCallTime == 0L) return false
        val turkeyTimeZone = TimeZone.getTimeZone("Europe/Istanbul")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
            timeZone = turkeyTimeZone
        }

        val lastCallDateStr = dateFormat.format(Date(lastCallTime))
        val currentDateStr = dateFormat.format(Date(System.currentTimeMillis()))
        Log.e("AppPreferencesDataStore", "Last Call Date: $lastCallDateStr, Current Date: $currentDateStr")

        return lastCallDateStr == currentDateStr
    }
}

