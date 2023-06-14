package com.example.appcentertodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.appcentertodolist.Login.LoginFragment
import com.example.appcentertodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val transaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, LoginFragment())
            .setReorderingAllowed(true)
            .commit()
    }

    fun login(){
        transaction.replace(R.id.fragment_home, HomeFragment())
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
}