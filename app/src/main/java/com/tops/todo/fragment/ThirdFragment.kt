package com.tops.todo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.todo.Todo
import com.tops.todo.adapter.ThirdAdapter
import com.tops.todo.databinding.FragmentThirdBinding
import com.tops.todo.viewmodels.NewTodoListViewModel


class ThirdFragment : Fragment() {

    private lateinit var  binding: FragmentThirdBinding
    private val newviewmodel: NewTodoListViewModel by viewModels()
    private lateinit var adapter: ThirdAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvViewTaskList.layoutManager = LinearLayoutManager(requireContext())

        adapter = ThirdAdapter(mutableListOf()){todo->
            newviewmodel.deleteTodo(todo)
        }
        binding.rvViewTaskList.adapter = adapter
        newviewmodel.todotask.observe(viewLifecycleOwner, Observer{
            task-> adapter.updateList(task.toMutableList())
        })

        binding.btnNewAddTAsk.setOnClickListener {
            val title = binding.etTaskTitle.text.toString()

            if (title.isEmpty()){
                binding.etTaskTitle.error = "Enter Todo"
            }else{
                newviewmodel.addTodo(title)
                binding.etTaskTitle.text?.clear()
            }
        }
    }
}