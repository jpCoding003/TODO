package com.tops.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.todo.Todo
import com.tops.todo.databinding.ItemTodoBinding

class ThirdAdapter(private var tasklist: MutableList<Todo>): RecyclerView.Adapter<ThirdAdapter.ThirdViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThirdViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ThirdViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ThirdViewHolder,
        position: Int
    ) {
        val list = tasklist[position]
        holder.binding.tvTitle.text = list.title

    }

    override fun getItemCount(): Int = tasklist.size

    fun updateList(newList: MutableList<Todo>) {
        tasklist = newList
        notifyDataSetChanged()
    }

    class ThirdViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)
}