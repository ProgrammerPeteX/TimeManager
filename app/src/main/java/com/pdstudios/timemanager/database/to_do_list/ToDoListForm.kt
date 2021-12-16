package com.pdstudios.timemanager.database.to_do_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "to_do_list_table")
data class ToDoListForm(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo
    var name: String = "",

    @ColumnInfo
    var dateTimeCreated: Long = System.currentTimeMillis(),

    @ColumnInfo
    var isChecked: Boolean = false
)
