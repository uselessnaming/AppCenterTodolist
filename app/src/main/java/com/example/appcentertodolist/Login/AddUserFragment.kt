package com.example.appcentertodolist.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appcentertodolist.DataClasses.AddUserResponseBody
import com.example.appcentertodolist.DataClasses.PostAddUserModel
import com.example.appcentertodolist.MainActivity
import com.example.appcentertodolist.R
import com.example.appcentertodolist.Retrofit.RetrofitObject
import com.example.appcentertodolist.databinding.FragmentAddUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUserFragment : Fragment() {

    private lateinit var binding : FragmentAddUserBinding
    
    private val mActivity by lazy{
        activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply{
            //확인 버튼 클릭
            btnDone.setOnClickListener{
                //입력 안한 것이 있는지? & 비밀번호와 비밀번호 확인 칸이 동일한 값인지?
                if(etId.text.isEmpty()){
                    showToast("아이디를 입력해 주세요")
                } else if (etPasswd.text.isEmpty()){
                    showToast("비밀번호를 입력해 주세요")
                } else if (etPasswdCheck.text.isEmpty()) {
                    showToast("비밀번호 확인 란을 입력해주세요")
                } else if (etEmail.text.isEmpty()){
                    showToast("이메일을 입력해 주세요")
                } else if (etPasswd.text.toString() != etPasswdCheck.text.toString()) {
                    showToast("비밀 번호와 확인 값이 다릅니다. 확인해 주세요")
                } else {
                    val id = etId.text.toString()
                    val passwd = etPasswd.toString()
                    val email = etEmail.toString()

                    //서버에 유저 데이터 추가
                    val call = RetrofitObject.getUserRetrofitService().addUser(PostAddUserModel(email,passwd,id))
                    call.enqueue(object : Callback<AddUserResponseBody>{
                        override fun onResponse(
                            call: Call<AddUserResponseBody>,
                            response: Response<AddUserResponseBody>
                        ) {
                            if(response.isSuccessful){
                                showToast("회원 가입 완료")
                                //로그인 화면으로 돌아감
                                mActivity.switchFragment(LoginFragment())    
                            } else {
                                showToast("Error Code : {response.body()!!.errCode} \n {response.body()!!.errMsg}")
                            }
                        }
                        override fun onFailure(call: Call<AddUserResponseBody>, t: Throwable) {
                            showToast("${t.message}")
                        }
                    })
                }
            }
            //취소 버튼 클릭
            btnCancel.setOnClickListener{
                //뒤로 가기
                mActivity.onBackPressed()
            }
        }
    }

    private fun showToast(s : String){
        Toast.makeText(context,s, Toast.LENGTH_SHORT).show()
    }
}