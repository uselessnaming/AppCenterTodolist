package com.example.appcentertodolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appcentertodolist.databinding.FragmentTodoItemBinding

class AddTodoFragment : Fragment() {

    private lateinit var binding : FragmentTodoItemBinding
    private lateinit var onClickListener : OnClickListener
    private val mActivity by lazy{
        activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var deadline = ""
        binding.apply{
            //Deadline을 설정하는 EditText Click 시
            tvDeadline.setOnClickListener{
                val datePickerFragment = DatePickerFragment()
                //tvDeadline 클릭 시 Calendar에서 날짜 선택
                datePickerFragment.setOnDatePickerListener { _, year, month, dayOfMonth ->
                    deadline = "$year-"
                    if (month+1 < 10){
                        deadline += "0"
                    }
                    deadline += "${month+1}-"
                    if (dayOfMonth < 10){
                        deadline += "0"
                    }
                    deadline += "$dayOfMonth"
                    tvDeadline.text = deadline
                }
                mActivity.showDatePicker(datePickerFragment)
            }

            // 추가 버튼 눌렀을 떄
            btnAdd.setOnClickListener{
                // 비어 있는 거 있으면 Toast 알림
                if(etTitle.text.isEmpty()){
                    showToast("제목이 비어 있습니다.")
                }
                else if (etContent.text.isEmpty()){
                    showToast("내용이 비어 있습니다.")
                }
                // deadline이 ""이라면 날짜를 선택하라는 Toast 알림
                else if (deadline == ""){
                    showToast("날짜를 선택해 주세요")
                }
                // 없으면 todos에 추가 후 내용 비우기
                else {
                    val title = etTitle.text.toString()
                    val content = etContent.text.toString()
                    onClickListener.onClick(title,content,deadline)
                    showToast("추가 완료")
                    etContent.text.clear()
                    tvDeadline.text = "Deadline 선택"
                }
            }
            // 취소 버튼 눌렀을 때
            btnCancel.setOnClickListener{
                mActivity.onBackPressedDispatcher.onBackPressed()
            }
        }
    }
    private fun showToast(s : String){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
    }
}