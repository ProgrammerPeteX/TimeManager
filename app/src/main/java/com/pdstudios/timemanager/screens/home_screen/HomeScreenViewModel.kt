package com.pdstudios.timemanager.screens.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeScreenViewModel: ViewModel() {

    private val _isNavigateToToDoList = MutableLiveData<Boolean?>()
    val isNavigateToToDoList: LiveData<Boolean?>
        get() = _isNavigateToToDoList

    init {
        _isNavigateToToDoList.value = null
    }

    fun navigateToToDoList() {
        _isNavigateToToDoList.value = true
    }

}
