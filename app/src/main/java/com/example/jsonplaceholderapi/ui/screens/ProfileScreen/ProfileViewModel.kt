package com.example.jsonplaceholderapi.ui.screens.ProfileScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.ui.screens.ProfileScreen.UserPreferences
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val userPrefs = UserPreferences(application.applicationContext)

    val firstName = userPrefs.firstNameFlow.stateIn(viewModelScope, SharingStarted.Lazily, "")
    val lastName = userPrefs.lastNameFlow.stateIn(viewModelScope, SharingStarted.Lazily, "")
    val imagePath = userPrefs.profileImagePathFlow.stateIn(viewModelScope, SharingStarted.Lazily, "")

    fun saveUserData(firstName: String, lastName: String, imagePath: String) {
        viewModelScope.launch {
            userPrefs.saveUserData(firstName, lastName, imagePath)
        }
    }
    fun clearUserData() {
        viewModelScope.launch {
            userPrefs.clearUserData()
        }
    }

}
