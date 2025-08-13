package com.example.mydemoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class DemoTexting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_texting)

        val customInputField = findViewById<CustomInputField>(R.id.custom_input)

//        customInputField.enablePasswordToggle(true)
        customInputField.setLabel("Enter Amount", true)
        customInputField.setPrefix("₦  ")
        customInputField.setMinLength(4)
        customInputField.setMaxLength(20)
        customInputField.setEnabledInput(true)
        customInputField.setInputType("number")



//        val customSwitch = findViewById<MyCustomSwitch>(R.id.customSwitch)
//        Log.e("DemoTexting", "onCreate Switch: $customSwitch" )








    }
}