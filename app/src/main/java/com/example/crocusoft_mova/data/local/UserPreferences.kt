package com.example.crocusoft_mova.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

data class UserPreferences(
    val isDarkModeActive: Boolean
)

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

class UserPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    private object PreferencesKey {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val isDarkModeActive =
                preferences[PreferencesKey.DARK_MODE] ?: false

            UserPreferences(isDarkModeActive)
        }

    suspend fun updateIsDarkModeActive(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.DARK_MODE] = isDarkModeActive
        }
    }
}