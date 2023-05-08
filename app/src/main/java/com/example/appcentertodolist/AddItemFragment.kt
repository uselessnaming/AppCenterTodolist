package com.example.appcentertodolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appcentertodolist.databinding.FragmentAddItemBinding

class AddItemFragment() : Fragment() {

    private lateinit var binding : FragmentAddItemBinding
    private val newContents : ArrayList<String> = arrayListOf()
    private val newDeadlines : ArrayList<String> = arrayListOf()
    private lateinit var onClickListener : OnClickListener
    private val mActivity by lazy{
        activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            // 추가 버튼 눌렀을 떄
            btnAdd.setOnClickListener{
                // 비어 있는 거 있으면 Toast 알림
                if(etContent.text.isEmpty() || etDeadline.text.isEmpty()){
                    showToast("값이 비어있습니다. 다 채워주세요")
                }
                // 없으면 todos에 추가 후 내용 비우기
                else {
                    val content = etContent.text.toString()
                    val deadline = etDeadline.text.toString()
                    onClickListener.onClick(content,deadline)
                    showToast("추가 완료")
                    etContent.text.clear()
                    etDeadline.text.clear()
                }
            }
            // 취소 버튼 눌렀을 때
            btnCancel.setOnClickListener{
                mActivity.onBackPressed()
            }
        }
    }
    fun setOnClickListener(onClickListener : OnClickListener){
        this.onClickListener = onClickListener
    }
    private fun showToast(s : String){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
    }
}