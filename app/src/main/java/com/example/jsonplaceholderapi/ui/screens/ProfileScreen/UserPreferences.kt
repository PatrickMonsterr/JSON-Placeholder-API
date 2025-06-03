package com.example.jsonplaceholderapi.ui.screens.ProfileScreen

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val FIRST_NAME = stringPreferencesKey("first_name")
        val LAST_NAME = stringPreferencesKey("last_name")
        val PROFILE_IMAGE_PATH = stringPreferencesKey("profile_image_path")
    }

    val firstNameFlow: Flow<String?> = context.dataStore.data.map { it[FIRST_NAME] }
    val lastNameFlow: Flow<String?> = context.dataStore.data.map { it[LAST_NAME] }
    val profileImagePathFlow: Flow<String?> = context.dataStore.data.map { it[PROFILE_IMAGE_PATH] }
//
//    fun getFirstName(): String? = runBlocking {
//        firstNameFlow.first()
//    }
//
//    fun getLastName(): String? = runBlocking {
//        lastNameFlow.first()
//    }

    suspend fun saveUserData(firstName: String, lastName: String, imagePath: String) {
        context.dataStore.edit {
            it[FIRST_NAME] = firstName
            it[LAST_NAME] = lastName
            it[PROFILE_IMAGE_PATH] = imagePath
        }
    }
    suspend fun clearUserData() {
        context.dataStore.edit {
            it.remove(FIRST_NAME)
            it.remove(LAST_NAME)
            it.remove(PROFILE_IMAGE_PATH)
        }
    }

}