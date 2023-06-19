package com.example.appcentertodolist.MainContent

import android.accounts.NetworkErrorException
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appcentertodolist.Login.LoginFragment
import com.example.appcentertodolist.MainActivity
import com.example.appcentertodolist.R
import com.example.appcentertodolist.Resources.LoginUser
import com.example.appcentertodolist.Retrofit.RetrofitObject
import com.example.appcentertodolist.databinding.FragmentMyPageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMyPageBinding

    private val mActivity by lazy{
        activity as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        //로그인 되어 있는 계정으로 textView 세팅
        binding.tvName.text = LoginUser.name
        binding.tvEmail.text = LoginUser.email
        
        //회원 탈퇴 시 계정을 삭제하고 Login화면으로 이동
        binding.tvLogout.setOnClickListener{
            //정말 삭제할 지 물어보는 dialog
            showDialog()
        }
    }

    private fun showDialog(){
        val dialog = AlertDialog.Builder(requireContext())
        dialog.apply{
            setTitle("주의")
            setMessage("정말 삭제하시겠습니까?")
            setPositiveButton("확인") { _, _ ->
                val call = RetrofitObject.getUserRetrofitService().deleteUser(LoginUser.id!!)
                call.enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if(response.isSuccessful){
                            showToast("${response.body()}")
                            LoginUser.id = null
                            LoginUser.name = null
                            LoginUser.email = null
                            LoginUser.jwtToken = null
                            mActivity.switchFragment(LoginFragment())
                        } else {
                            showToast("Error : ${response.body()}")
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        throw NetworkErrorException(t.message)
                    }
                })
            }
            setNegativeButton("취소",null)
            show()
        }
    }

    private fun showToast(s : String){
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }
}