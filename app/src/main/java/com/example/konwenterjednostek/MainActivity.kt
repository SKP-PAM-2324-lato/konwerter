package com.example.konwenterjednostek

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val conversionMap = mapOf("km/mi" to 0.6214, "kg/lb" to 2.2046)
        val units = conversionMap.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,units)
        val unitsSpinner = findViewById<Spinner>(R.id.spinner)
        unitsSpinner.adapter = adapter

        val leftEditText = findViewById<EditText>(R.id.editTextLeft)
        val rightEditText = findViewById<EditText>(R.id.editTextRight)
        var change = false

        unitsSpinner.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                /*
                if(leftEditText.text.toString() != ""){
                    val inputValue = leftEditText.text.toString().toDoubleOrNull()
                    val unit = unitsSpinner.selectedItem.toString()
                    val rate = conversionMap[unit]
                    val outputValue = inputValue?.times(rate!!)
                    rightEditText.setText(String.format("%.2f", outputValue))
                }

                 */
                leftEditText.text = leftEditText.text
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        })



        leftEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(!change){
                    change = true
                    val inputValue = s.toString().toDoubleOrNull()
                    val unit = unitsSpinner.selectedItem.toString()
                    val rate = conversionMap[unit]
                    val outputValue = inputValue?.times(rate!!)
                    rightEditText.setText(String.format("%.2f", outputValue))
                }else{
                    change = false
                }
            }
        })

        rightEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(!change){
                    change = true
                    val inputValue = s.toString().toDoubleOrNull()
                    val unit = unitsSpinner.selectedItem.toString()
                    var rate = conversionMap[unit]
                    rate = 1.0/ rate!!
                    val outputValue = inputValue?.times(rate)
                    leftEditText.setText(String.format("%.2f", outputValue))
                }else{
                    change = false
                }


            }
        })

    }

}