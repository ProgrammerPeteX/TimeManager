package com.pdstudios.timemanager.screens.to_do_list

import android.service.autofill.Validators.not
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoListViewModel: ViewModel() {

    //OTHER VARS
    private val _toDoList = MutableLiveData<MutableList<String>>()
    val toDoList: MutableLiveData<MutableList<String>>
        get() = _toDoList

    var notifyListChange = MutableLiveData<Boolean>()

    private var n = 0

    //NAVIGATION VARS
    private val _isNavigateToHomeScreen = MutableLiveData<Boolean?>()

    val isNavigateToHomeScreen: LiveData<Boolean?>
        get() = _isNavigateToHomeScreen
    private val _isNavigateToTDLAddTask = MutableLiveData<Boolean?>()

    val isNavigateToTDLAddTask: LiveData<Boolean?>
        get() = _isNavigateToTDLAddTask

    init {
        _isNavigateToHomeScreen.value = null
        _isNavigateToTDLAddTask.value = null
        _toDoList.value =  mutableListOf()
        notifyListChange.value = false
    }

    //OTHER FUNS
    fun addTask() {
        toDoList.value!!.add("test ${n++}")
        Log.i("Test", toDoList.value!![n-1])
        notifyListChange()
    }

    fun notifyListChange() {
        notifyListChange.value?.let{notifyListChange.value = !it}
    }

    //NAVIGATION FUNS
    fun navigateToHomeScreen() {
        _isNavigateToHomeScreen.value = true
    }

    fun navigateToTDLAddTask() {
        _isNavigateToTDLAddTask. value = true
    }
}
