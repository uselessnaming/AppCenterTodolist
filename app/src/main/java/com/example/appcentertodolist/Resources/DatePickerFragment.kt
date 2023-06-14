package com.example.appcentertodolist

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(){

    private lateinit var onDatePickerListener : DatePickerDialog.OnDateSetListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            requireContext(),
            onDatePickerListener,
            year,
            month,
            day
        )
    }

    fun setOnDatePickerListener(onDatePickerListener : DatePickerDialog.OnDateSetListener){
        this.onDatePickerListener = onDatePickerListener
    }
}