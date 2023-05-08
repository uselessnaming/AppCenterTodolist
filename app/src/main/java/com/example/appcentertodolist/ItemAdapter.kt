package com.example.appcentertodolist

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentertodolist.databinding.ItemBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private lateinit var binding : ItemBinding
    private var todos : MutableList<Todo> = mutableListOf()

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    fun submitList(todos : MutableList<Todo>){
        this.todos.clear()
        this.todos.addAll(todos)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private var binding : ItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(todo : Todo){
            binding.tvContent.text = todo.content
            binding.tvDeadline.text = todo.deadline


            //만약 daedline이 오늘 날짜보다 뒤라면 빨간 글씨
            val today = LocalDate.now()
            val deadline = LocalDate.parse(todo.deadline,formatter)
            if (checkDeadline(deadline,today)){
                binding.tvDeadline.setTextColor(Color.RED)
            }
        }
    }
    private fun checkDeadline(deadline : LocalDate, today : LocalDate) : Boolean{
        if(deadline.isBefore(today)) return true
        return false
    }
}