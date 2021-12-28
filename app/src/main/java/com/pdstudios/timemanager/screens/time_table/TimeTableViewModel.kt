package com.pdstudios.timemanager.screens.time_table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimeTableViewModel(): ViewModel() {

    //VARS
    private val _isNavigateToHomeScreen = MutableLiveData<Boolean>()
    val isNavigateToHomeScreen: LiveData<Boolean>
        get() = _isNavigateToHomeScreen

    init {
        _isNavigateToHomeScreen.value = null
    }

    //NAVIGATION
    fun navigateToHomeScreen() {
        _isNavigateToHomeScreen.value = true
    }

}