package com.pdstudios.timemanager.screens.to_do_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdstudios.timemanager.database.to_do_list.ToDoListDao
import com.pdstudios.timemanager.database.to_do_list.ToDoListForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ToDoListViewModel(
    private val toDoListDao: ToDoListDao
) : ViewModel() {

    //OTHER VARS
    val toDoList = toDoListDao.getAll()

    private var n = 0

    //NAVIGATION VARS
    private val _isNavigateToHomeScreen = MutableLiveData<Boolean?>()
    val isNavigateToHomeScreen: LiveData<Boolean?>
        get() = _isNavigateToHomeScreen

    private val _isCreateNewTask = MutableLiveData<Boolean>()
    val isCreateNewTask: LiveData<Boolean>
        get() = _isCreateNewTask


    init {
        _isNavigateToHomeScreen.value = null
        _isCreateNewTask.value = null

    }

    //OTHER FUNS

    //DATABASE
    fun addTask() {
        viewModelScope.launch {
            val form = ToDoListForm()
            insertTask(form)
            _isCreateNewTask.value = true
        }
    }

    private suspend fun insertTask(form: ToDoListForm) {
        withContext(Dispatchers.IO) {
            toDoListDao.insert(form)
            Log.i("Test", "listSize = ${toDoList.value?.size ?: "null"}")
        }
    }

    fun deleteFromToDoList(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                toDoListDao.delete(id)
            }
        }
    }

    fun updateTask(form: ToDoListForm) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                toDoListDao.update(form)
            }
        }
    }

    //NAVIGATION FUNS
    fun navigateToHomeScreen() {
        _isNavigateToHomeScreen.value = true
    }

}
