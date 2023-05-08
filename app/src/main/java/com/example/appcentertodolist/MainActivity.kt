package com.example.appcentertodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.appcentertodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val transaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, HomeFragment())
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