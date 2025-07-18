package com.tops.todo.viewmodels

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tops.todo.model.todoTitlt

class TodoListViewModel {

    private lateinit var db: SQLiteDatabase

    private val _todolist = MutableLiveData<todoTitlt>()
    val todolist: LiveData<todoTitlt> = _todolist

    fun loadTodoList(context: Context){

    }
}