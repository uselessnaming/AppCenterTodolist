package com.example.appcentertodolist.MainContent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.appcentertodolist.R
import com.example.appcentertodolist.databinding.FragmentFloatDialogBinding

class FloatDialog : DialogFragment() {

    private lateinit var binding : FragmentFloatDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFloatDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}