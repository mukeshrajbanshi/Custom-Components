package com.example.mydemoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button


class DemoTexting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_texting)

        val customInputField = findViewById<CustomInputField>(R.id.custom_input)
        val bottomButton = findViewById<Button>(R.id.custom_button)

//        customInputField.enablePasswordToggle(true)
        customInputField.setLabel("Enter Amount", true)
        customInputField.setPrefix("₦  ")
        customInputField.setMinLength(4)
        customInputField.setMaxLength(20)
        customInputField.setEnabledInput(true)
        customInputField.setInputType("number")

        bottomButton.setOnClickListener{
            Log.e("CardControlsActivity", "button clicked check in main!")
            val  value = Intent(this, Accordion ::class.java)
            startActivity(value)
        }



//        val customSwitch = findViewById<MyCustomSwitch>(R.id.customSwitch)
//        Log.e("DemoTexting", "onCreate Switch: $customSwitch" )








    }
}