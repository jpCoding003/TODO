package com.tops.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.todo.databinding.ItemTodoBinding
import com.tops.todo.model.TodoModel

class MyAdapter(private val tasks: MutableList<TodoModel>) : RecyclerView.Adapter<MyAdapter.TaskViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val task = tasks[position]
        holder.binding.tvTitle.text = task.Task
    }

    override fun getItemCount(): Int = tasks.size

    fun setData(newList: List<TodoModel>) {
        tasks.clear()
        tasks.addAll(newList)
        notifyDataSetChanged()
    }

    class TaskViewHolder (val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)


}