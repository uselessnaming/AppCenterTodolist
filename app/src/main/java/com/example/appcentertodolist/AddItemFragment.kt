package com.example.appcentertodolist

import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import com.example.appcentertodolist.databinding.FragmentAddItemBinding
import java.util.*

class AddItemFragment : Fragment() {

    private lateinit var binding : FragmentAddItemBinding
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
        var deadline = ""
        binding.apply{
            //Deadline을 설정하는 editText를 클릭했을 떄
            tvDeadline.setOnClickListener{
                val datePickerFragment = DatePickerFragment()
                //tvDeadline 클릭 시 Calendar 사용 날짜 선택
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
                if(etContent.text.isEmpty()){
                    showToast("내용이 비어있습니다.")
                }
                // deadline이 ""이라면 날짜를 선택하라는 Toast 알림
                else if (deadline == ""){
                    showToast("날짜를 선택해주세요")
                }
                // 없으면 todos에 추가 후 내용 비우기
                else {
                    val content = etContent.text.toString()
                    onClickListener.onClick(content,deadline)
                    showToast("추가 완료")
                    etContent.text.clear()
                    tvDeadline.text = "Deadline 선택"
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