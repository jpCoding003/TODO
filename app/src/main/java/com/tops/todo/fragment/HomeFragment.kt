package com.tops.todo.fragment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.todo.adapter.MyAdapter
import com.tops.todo.databinding.FragmentHomeBinding
import com.tops.todo.model.todoTitlt


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var db: SQLiteDatabase
    private  var Task = mutableListOf<todoTitlt>()
    private lateinit var adapter: MyAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "ToDo's"
        db = requireActivity().openOrCreateDatabase("TodoTask", Context.MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS TodoTask(id INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR);")

        //Load Data
        val cursor1 = db.rawQuery("SELECT * FROM ToDoTask", null)
        while (cursor1.moveToNext()){
            val id = cursor1.getInt(0)
            val Title = cursor1.getString(1)
            if (Title.isNotEmpty()){
                Task.add(todoTitlt(id,Title))
            }
        }
        cursor1.close()


        binding.rvToDoList.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyAdapter(Task){ itemToDelete->
            db.execSQL("DELETE FROM ToDoTask WHERE id=?",arrayOf(itemToDelete.id))
            val position = Task.indexOf(itemToDelete)
            if (position != -1){
                Task.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }
        binding.rvToDoList.adapter = adapter


        binding.btnAddTask.setOnClickListener {
            if (binding.etTaskTitle.text.toString().isEmpty()){
                binding.etTaskTitle.setError("Task Can't be Empty")
                return@setOnClickListener
            }else{
                //insert into database
                db.execSQL("INSERT INTO ToDoTask(title) VALUES(?);", arrayOf(binding.etTaskTitle.text.toString().trim()))
               Task.add(todoTitlt(id,binding.etTaskTitle.text.toString().trim()))
                binding.rvToDoList.adapter?.notifyItemInserted(Task.size - 1)



                binding.etTaskTitle.text?.clear()
            }
        }
    }
}