package com.example.basicsyntax

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelected: TextView? = null
    private var tvAgeInMinutes:TextView?= null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button = findViewById(R.id.btn_DatePicker)

        tvSelected = findViewById(R.id.tv_selectedDate)
        tvAgeInMinutes = findViewById(R.id.tv_now)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    fun clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)

        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this, { view, selectedyear, selectedmonth, selecteddayOFmonth ->
                Toast.makeText(
                    this,
                    "Year was$selectedyear month was ${selectedmonth + 1}, day of month was $selecteddayOFmonth",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$selectedyear/${selectedmonth + 1}/$selecteddayOFmonth"
                tvSelected?.text = selectedDate
                val sdf = SimpleDateFormat("yyyyy/MM/dd", Locale.KOREAN)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 86400000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes = currentDate.time/86400000
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                    tvAgeInMinutes?.text = differenceInMinutes.toString()
                }

            },
            year, month, day
        ).show()

    }
}