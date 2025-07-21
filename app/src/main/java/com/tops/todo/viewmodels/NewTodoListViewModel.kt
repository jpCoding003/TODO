package com.tops.todo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tops.todo.MainApplication
import com.tops.todo.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTodoListViewModel : ViewModel() {

    val todoDao = MainApplication.todoDatabase.getTodoDao()

    val todotask : LiveData<List<Todo>> = todoDao.grtall()

    fun addTodo(title: String, check: Boolean = false){
        viewModelScope.launch(Dispatchers.IO) {
            val insert = Todo(title = title, check = check)
            todoDao.insertTodo(insert)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(todo)
        }
    }
}