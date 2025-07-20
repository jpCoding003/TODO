package com.tops.todo.viewmodels

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tops.todo.model.TodoModel

class TodoListViewModel : ViewModel() {

    private lateinit var db: SQLiteDatabase

    private val _todolist = MutableLiveData<List<TodoModel>>()
    val todolist: LiveData<List<TodoModel>> = _todolist

    fun loadTodoList(context: Context) {

        val tabelData = mutableListOf<TodoModel>()
        db = context.openOrCreateDatabase("TodoList", Context.MODE_PRIVATE,null)
        db.execSQL("""CREATE TABLE IF NOT EXISTS todo(ID INTEGER PRIMARY KEY AUTOINCREMENT , TODOTASK VARCHAR, COMPLETED INTEGER DEFAULT 0)""".trimIndent())

        val cursor = db.rawQuery("SELECT * FROM todo", null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(0)
                val task = cursor.getString(1)
                val complete = cursor.getInt(2)
                val data = TodoModel(id,task,complete)

                tabelData.add(data)

            }while (cursor.moveToNext())
            cursor.close()
        }
        _todolist.value = tabelData
    }

    fun addtask(context: Context, task: String){
        db = context.openOrCreateDatabase("TodoList", Context.MODE_PRIVATE,null)
        db.execSQL("CREATE TABLE IF NOT EXISTS todo(ID INTEGER PRIMARY KEY AUTOINCREMENT , TODOTASK VARCHAR, COMPLETED INTEGER DEFAULT 0)")

        val contentvalues = ContentValues().apply {
            put("TODOTASK", task)
        }
        db.insert("todo", null, contentvalues)

    }



}