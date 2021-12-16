package com.pdstudios.timemanager.screens.to_do_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pdstudios.timemanager.database.to_do_list.ToDoListDao
import java.lang.IllegalArgumentException

class ToDoListViewModelFactory(
    private val toDoListDao: ToDoListDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoListViewModel::class.java)) {
            return ToDoListViewModel(toDoListDao) as T
        }
        throw IllegalArgumentException("ViewModel not found!")
    }

}
