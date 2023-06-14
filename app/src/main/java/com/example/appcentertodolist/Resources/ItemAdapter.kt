package com.example.appcentertodolist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentertodolist.databinding.ItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private lateinit var binding : ItemBinding
    private var todos : MutableList<Task> = mutableListOf()

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    fun submitList(todos : ArrayList<Task>){
        this.todos.clear()
        this.todos.addAll(todos)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private var binding : ItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(todo : Task){

        }
    }
    private fun checkDeadline(deadline : LocalDate, today : LocalDate) : Boolean{
        if(deadline.isBefore(today)) return true
        return false
    }
}