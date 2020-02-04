package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

private const val FIRST_NAME = "FIRST_NAME"
private const val LAST_NAME = "LAST_NAME"
private const val ABOUT = "ABOUT"
private const val REPOSITORY = "REPOSITORY"
private const val RATING = "RATING"
private const val RESPECT = "RESPECT"
private const val APP_THEME = "APP_THEME"

object PreferencesRepository {

    private val preferences: SharedPreferences by lazy {
        val context = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getProfile(): Profile? = Profile(
        preferences.getString(FIRST_NAME, "")!!,
        preferences.getString(LAST_NAME, "")!!,
        preferences.getString(ABOUT, "")!!,
        preferences.getString(REPOSITORY, "")!!,
        preferences.getInt(RATING, 0),
        preferences.getInt(RESPECT, 0)
    )

    fun saveProfile(profile: Profile) {
        with(profile) {
            putValue(FIRST_NAME to firstName)
            putValue(LAST_NAME to lastName)
            putValue(ABOUT to about)
            putValue(REPOSITORY to repository)
            putValue(RATING to rating)
            putValue(RESPECT to respect)
        }
    }

    fun saveAppTheme(value: Int) {
        putValue(APP_THEME to value)
    }

    fun getAppTheme(): Int? = preferences.getInt(APP_THEME, AppCompatDelegate.MODE_NIGHT_NO)

    private fun putValue(pair: Pair<String, Any>) = with(preferences.edit()) {
        val key = pair.first

        when (val value = pair.second) {
            is String -> putString(key, value).apply()
            is Int -> putInt(key, value).apply()
            is Boolean -> putBoolean(key, value).apply()
            is Float -> putFloat(key, value).apply()
            is Long -> putLong(key, value).apply()
            else -> error("Only primitive types can be stored in Shared Preferences")
        }
    }
}