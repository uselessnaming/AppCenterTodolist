package com.example.appcentertodolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentertodolist.databinding.ItemBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private lateinit var binding : ItemBinding
    private var todos : List<Todo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    fun submitList(todos : MutableList<Todo>){
        this.todos = todos
    }

    inner class ItemViewHolder(private val binding : ItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(todo : Todo){
            binding.tvContent.text = todo.content
            binding.tvDeadline.text = todo.deadline
        }
    }
}