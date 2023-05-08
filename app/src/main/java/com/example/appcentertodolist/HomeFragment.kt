package com.example.appcentertodolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentertodolist.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val mItemAdapter by lazy{
        ItemAdapter()
    }
    private val mActivity by lazy{
        activity as MainActivity
    }
    private val todos : MutableList<Todo> = mutableListOf(Todo("집에 가기","2023-05-02")
        ,Todo("예비군","2023-05-03"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // 버튼 눌러서 추가 화면 전환
        binding.btnAddItem.setOnClickListener{
            val newFragment = AddItemFragment()
            mActivity.switchFragment(newFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        fetchTodos()
    }
    private fun initRecyclerView(){
        binding.rcvRecyclerView.apply{
            adapter = mItemAdapter
            val layoutManager = LinearLayoutManager(context)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            mItemAdapter.submitList(todos)
        }
    }
    //start() 생명주기마다 새로 입력된 todo들을 todos에 추가
    private fun fetchTodos(){
        val check = arguments?.isEmpty ?: false
        if(!check){
            val contents = requireArguments().getStringArrayList("contents")
            val deadlines = requireArguments().getStringArrayList("deadlines") ?: arrayListOf()
            for(i in 1..contents!!.size){
                val newTodo = Todo(contents[i], deadlines[i])
                todos.add(newTodo)
            }
        }
    }
}