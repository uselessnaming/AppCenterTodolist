package com.example.appcentertodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.appcentertodolist.Login.LoginFragment
import com.example.appcentertodolist.MainContent.MyPageFragment
import com.example.appcentertodolist.MainContent.SearchUserFragment
import com.example.appcentertodolist.Resources.LoginUser
import com.example.appcentertodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val transaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /** 원래의 코드
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, LoginFragment())
            .setReorderingAllowed(true)
            .commit()
        */

        //테스트
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, HomeFragment())
            .setReorderingAllowed(true)
            .commit()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        actMenu()
        setMenu()
    }

    fun login(){
        transaction.replace(R.id.fragmentContainerView, HomeFragment())
            .setReorderingAllowed(true)
            .commit()
    }
    fun switchFragment(fragment : Fragment){
        transaction.replace(R.id.fragmentContainerView,fragment)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
    fun showDatePicker(fragment : DatePickerFragment){
        fragment.show(supportFragmentManager,"datePicker")
    }

    //Menu 버튼 부분 활성화
    private fun actMenu(){
        val menu = binding.root.findViewById<ImageButton>(R.id.btnMenu)

        menu.setOnClickListener{
            if(!binding.drawerLayout.isDrawerOpen(GravityCompat.END)) binding.drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    //Menu 설정
    private fun setMenu(){
        binding.navBar.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                android.R.id.home -> {
                    onBackPressed() //deprecated >> 바꿔야 함
                    true
                }
                R.id.menu_my_page -> {
                    switchFragment(MyPageFragment())
                    true
                }
                R.id.menu_add_todo -> {
                    switchFragment(AddTodoFragment())
                    true
                }
                R.id.menu_user_search -> {
                    //admin 코드가 뭔지 아직 안나옴
                    //id가 admin 코드(0)일 경우 이용 가능
                    if (LoginUser.id == 0){
                        switchFragment(SearchUserFragment())
                        true
                    } else {
                        showToast("관리자만이 이용할 수 있습니다.")
                        false
                    }
                }
                R.id.menu_logout -> {
                    LoginUser.id = null
                    LoginUser.name = null
                    LoginUser.email = null
                    LoginUser.jwtToken = null
                    showToast("로그아웃 완료")
                    switchFragment(LoginFragment())
                    true
                }
                else -> {
                    throw IllegalStateException("Error : none of the selection")
                }
            }
        }
    }
    private fun showToast(s : String){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    //dialog를 띄워주는 함수
    fun showDialog(dialog : DialogFragment){
        dialog.show(supportFragmentManager, "dialog")
    }
}