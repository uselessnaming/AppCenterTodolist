package com.example.appcentertodolist.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.appcentertodolist.DataClasses.LoginResponseBody
import com.example.appcentertodolist.DataClasses.PostLoginModel
import com.example.appcentertodolist.DataClasses.User
import com.example.appcentertodolist.HomeFragment
import com.example.appcentertodolist.MainActivity
import com.example.appcentertodolist.R
import com.example.appcentertodolist.Resources.LoginUser
import com.example.appcentertodolist.Retrofit.RetrofitObject
import com.example.appcentertodolist.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding

    private val mActivity by lazy{
        activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        val toolbar = binding.root.findViewById<Toolbar>(R.id.toolbar)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        //메뉴 설정 불가
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity.supportActionBar?.setDisplayShowHomeEnabled(false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        //로그인 버튼이 눌릴 경우
        binding.apply{
            btnLogin.setOnClickListener{
                //아이디 비밀번호가 비어 있는지
                if(etId.text.isEmpty()){
                    showToast("Id를 입력해 주세요")
                } else if (etPasswd.text.isEmpty()){
                    showToast("Password를 입력해 주세요")
                } else {
                    val passwd = etPasswd.text.toString()
                    val id = etId.text.toString()
                    val call = RetrofitObject.getUserRetrofitService().login(PostLoginModel(passwd,id))
                    call.enqueue(object : Callback<LoginResponseBody>{
                        override fun onResponse(call: Call<LoginResponseBody>, response: Response<LoginResponseBody>) {
                            if(response.isSuccessful){
                                
                                //성공 시 Login User 싱글톤 객체에 현재 로그인 된 유저 정보를 넣어줌
                                LoginUser.id = response.body()!!.data.id
                                LoginUser.name = response.body()!!.data.username
                                LoginUser.email = response.body()!!.data.email
                                LoginUser.jwtToken = response.body()!!.data.jwtToken
                                showToast(response.body()!!.msg)
                                
                                //로그인에 성공하면 HomeFragment로 이동
                                mActivity.login()
                            } else {
                                showToast("Error Code : ${response.body()!!.code} \n Message : ${response.body()!!.msg}")
                            }
                        }
                        override fun onFailure(call: Call<LoginResponseBody>, t: Throwable) {
                            showToast(t.message!!)
                        }
                    })
                }
            }
            //회원 가입 버튼 클릭 시
            btnAddUser.setOnClickListener{
                mActivity.switchFragment(AddUserFragment())
            }
            //아이디, 비밀번호 찾기 버튼 클릭 시
            btnCheckUser.setOnClickListener {
                mActivity.switchFragment(CheckUserFragment())
            }
        }
    }

    private fun showToast(s : String){
        Toast.makeText(context,s, Toast.LENGTH_SHORT).show()
    }
}