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
                if(etContent.text.isEmpty() || etDeadline.text.isEmpty()){
                    showToast("값이 비어있습니다. 다 채워주세요")
                } else {
                    val content = etContent.text.toString()
                    val deadline = etDeadline.text.toString()
                    newContents.add(content)
                    newDeadlines.add(deadline)
                    showToast("추가 완료")
                }
            }
            // 취소 버튼 눌렀을 때
            btnCancel.setOnClickListener{

            }
        }
    }

    override fun onPause() {
        super.onPause()
        val bundle = Bundle()
        val homeFragment = HomeFragment()
        bundle.putStringArrayList("contents",newContents)
        bundle.putStringArrayList("deadlines",newDeadlines)
        homeFragment.arguments = bundle
    }
    private fun showToast(s : String){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
    }
}