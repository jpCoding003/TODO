package com.tops.todo.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tops.todo.R
import com.tops.todo.databinding.FragmentAddTaskBinding
import com.tops.todo.viewmodels.TodoListViewModel


class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding

    private val todolistviewmodel: TodoListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddTask.setOnClickListener {
            val task = binding.etTask.text.toString()
            todolistviewmodel.addtask(requireContext(),task)
            findNavController().navigate(R.id.action_addTaskFragment_to_homeFragment)
        }

    }

}