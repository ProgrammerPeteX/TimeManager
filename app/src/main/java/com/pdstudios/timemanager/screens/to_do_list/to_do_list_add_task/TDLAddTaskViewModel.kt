package com.pdstudios.timemanager.screens.to_do_list.to_do_list_add_task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TDLAddTaskViewModel: ViewModel() {
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
