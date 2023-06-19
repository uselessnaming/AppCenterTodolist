package com.example.appcentertodolist

import android.accounts.NetworkErrorException
import android.net.Network
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentertodolist.Data_Classes.PostModel
import com.example.appcentertodolist.Data_Classes.PostResult
import com.example.appcentertodolist.MainContent.FloatDialog
import com.example.appcentertodolist.Resources.Interface.OnItemClickListener
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
    }
    private fun initRecyclerView(){
        binding.rcvRecyclerView.apply{
            adapter = mItemAdapter

            //recycler 초기화 시 데이터를 받아와 adapter에 submit로 넘겨줌
            /*
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
             */

            //recyclerView 내부 Item Event 설정
            mItemAdapter.setOnClickListener(object : OnItemClickListener {
                override fun onClick(id: Int) {
                    showDialog()
                }
            })

            val layoutManager = LinearLayoutManager(context)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
        }
    }

    //dialog를 띄워주는 함수
    private fun showDialog(){
        val dialog = FloatDialog()
        mActivity.showDialog(dialog)
    }

    private fun showToast(s : String){
        Toast.makeText(context,s, Toast.LENGTH_SHORT).show()
    }

}