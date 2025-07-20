package com.tops.todo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    companion object{
        const val Name = "Todo_DB"
    }
    abstract fun getTodoDao(): TodoDao

}