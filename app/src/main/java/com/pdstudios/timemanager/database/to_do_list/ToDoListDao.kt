package com.pdstudios.timemanager.database.to_do_list

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoListDao {
    @Insert
    fun insert(task: ToDoListForm)

    @Update
    fun update(task: ToDoListForm)

    @Query("SELECT * FROM to_do_list_table WHERE id=:id")
    fun get(id: Long): ToDoListForm

    @Query("SELECT * FROM to_do_list_table ORDER BY id")
    fun getAll(): LiveData<List<ToDoListForm>>

    @Query("DELETE FROM to_do_list_table WHERE id=:id")
    fun delete(id: Long)
}