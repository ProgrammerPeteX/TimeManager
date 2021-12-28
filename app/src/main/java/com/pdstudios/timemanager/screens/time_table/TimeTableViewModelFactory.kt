package com.pdstudios.timemanager.screens.time_table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TimeTableViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeTableViewModel::class.java)) {
            return TimeTableViewModel() as T
        }
        throw IllegalArgumentException ("ViewModel not found!")
    }

}
