package com.tops.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.todo.databinding.ItemTodoBinding
import com.tops.todo.model.todoTitlt

class MyAdapter(private val todolist: MutableList<todoTitlt>, private val onDelete: (todoTitlt) -> Unit): RecyclerView.Adapter<MyAdapter.TodoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TodoViewHolder,
        position: Int
    ) {
       // Code
        var list = todolist[position]
        holder.binding.tvTitle.setText(list.Tasktitle)

        holder.binding.btnDelete.setOnClickListener {
            onDelete(position)
        }
    }

    private fun onDelete(position: Int) {
        todolist.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, todolist.size)
        //notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todolist.size
    }

    class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {

    }
}