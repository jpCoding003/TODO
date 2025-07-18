package com.tops.todo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.todo.R
import com.tops.todo.adapter.MyAdapter
import com.tops.todo.databinding.FragmentHomeBinding
import com.tops.todo.model.TodoModel
import com.tops.todo.viewmodels.TodoListViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MyAdapter
    private val todoListViewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = MyAdapter(mutableListOf())
        binding.rvToDoList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvToDoList.adapter = adapter

        todoListViewModel.loadTodoList(requireContext())

        todoListViewModel.todolist.observe(viewLifecycleOwner, Observer{
        tasklist-> adapter.setData(tasklist)
        })

        binding.btnaddToDo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTaskFragment)
        }
    }

}