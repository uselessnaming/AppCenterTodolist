package com.example.appcentertodolist

import android.accounts.NetworkErrorException
import android.net.Network
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentertodolist.Data_Classes.PostModel
import com.example.appcentertodolist.Data_Classes.PostResult
import com.example.appcentertodolist.Retrofit.RetrofitObject
import com.example.appcentertodolist.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val mItemAdapter by lazy{
        ItemAdapter()
    }
    private val mActivity by lazy{
        activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // 버튼 눌러서 추가 화면 전환
        binding.btnAddItem.setOnClickListener{
            val newFragment = AddTodoFragment()
            newFragment.setOnClickListener(object : OnClickListener{
                override fun onClick(title : String, content: String, deadline: String) {
                    //todo 서버에 추가
                    RetrofitObject.getTaskRetrofitService().insertTodo(PostModel(deadline,content,title)).enqueue(object : Callback<PostResult>{
                        override fun onResponse(
                            call: Call<PostResult>,
                            response: Response<PostResult>
                        ) {
                            if(response.isSuccessful){
                                showToast("할 일 추가 성공")
                            } else {
                                showToast(response.body()!!.result!!)
                            }
                        }

                        override fun onFailure(call: Call<PostResult>, t: Throwable) {
                            throw NetworkErrorException(t.message)
                        }
                    })
                }
            })
            mActivity.switchFragment(newFragment)
        }
    }
    private fun initRecyclerView(){
        binding.rcvRecyclerView.apply{
            adapter = mItemAdapter

            //recycler 초기화 시 데이터를 받아와 adapter에 submit로 넘겨줌
            val call = RetrofitObject.getTaskRetrofitService().getTodo(null)
            call.enqueue(object : Callback<TaskList>{
                override fun onResponse(call: Call<TaskList>, response: Response<TaskList>) {
                    if (response.isSuccessful){
                        val result = response.body()!!.tasks
                        mItemAdapter.submitList(result)
                    } else {
                        throw NetworkErrorException("response is not successful")
                    }
                }

                override fun onFailure(call: Call<TaskList>, t: Throwable) {
                    throw NetworkErrorException(t.message)
                }
            })
            
            val layoutManager = LinearLayoutManager(context)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
        }
    }

    private fun showToast(s : String){
        Toast.makeText(context,s, Toast.LENGTH_SHORT).show()
    }
}