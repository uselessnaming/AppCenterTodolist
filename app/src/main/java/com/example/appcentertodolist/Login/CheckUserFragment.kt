package com.example.appcentertodolist.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.appcentertodolist.R
import com.example.appcentertodolist.databinding.FragmentCheckUserBinding

class CheckUserFragment : Fragment() {

    private lateinit var binding : FragmentCheckUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckUserBinding.inflate(layoutInflater, container, false)

        val toolbar = binding.root.findViewById<Toolbar>(R.id.toolbar)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        //메뉴 설정 불가
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity.supportActionBar?.setDisplayShowHomeEnabled(false)

        return binding.root
    }
}