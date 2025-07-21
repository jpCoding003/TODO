package com.tops.todo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Query(" SELECT * FROM todo")
    fun grtall(): LiveData<List<Todo>>

    @Insert
    suspend fun insertTodo(todo: Todo)    // if passing tomuch coloumns pass the model in it

    @Delete
    suspend fun deleteTodo(todo: Todo)
}