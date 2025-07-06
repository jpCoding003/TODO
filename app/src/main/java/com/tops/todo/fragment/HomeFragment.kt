package com.tops.todo.fragment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.todo.adapter.MyAdapter
import com.tops.todo.databinding.FragmentHomeBinding
import com.tops.todo.model.todoTitlt


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var db: SQLiteDatabase
    private  var Task = mutableListOf<todoTitlt>()

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
        db.execSQL("CREATE TABLE IF NOT EXISTS TodoTask(title VARCHAR);")

        binding.btnAddTask.setOnClickListener {
            if (binding.etTaskTitle.text.toString().isEmpty()){
                binding.etTaskTitle.setError("Task Can't be Empty")
            }else{
                db.execSQL("INSERT INTO ToDoTask(title) VALUES(?);", arrayOf(binding.etTaskTitle.text.toString()))
                binding.etTaskTitle.text?.clear()
                binding.rvToDoList.adapter?.notifyItemInserted(Task.size - 1)
                Log.i("Data"," Data in List ====${binding.etTaskTitle.text.toString()}")
            }
        }

        val cursor1 = db.rawQuery("SELECT * FROM ToDoTask", null)

        while (cursor1.moveToNext()){
            val Title = cursor1.getString(0)
            if (Title.isNotEmpty()){
                Task.add(todoTitlt(Title))
            }
        }
        cursor1.close()

        binding.rvToDoList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvToDoList.adapter = MyAdapter(Task)
    }

}