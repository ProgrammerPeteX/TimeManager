package com.pdstudios.timemanager.screens.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeScreenViewModel: ViewModel() {


    //NAVIGATE VARS
    private val _isNavigateToToDoList = MutableLiveData<Boolean?>()
    val isNavigateToToDoList: LiveData<Boolean?>
        get() = _isNavigateToToDoList

    private val _isNavigateToTimeTable = MutableLiveData<Boolean>()
    val isNavigateToTimeTable: LiveData<Boolean>
        get() = _isNavigateToTimeTable

    init {
        _isNavigateToToDoList.value = null
        _isNavigateToTimeTable.value = null
    }

    //NAVIGATE
    fun navigateToToDoList() {
        _isNavigateToToDoList.value = true
    }

    fun navigateToTimeTable() {
        _isNavigateToTimeTable.value = true

    }

}
