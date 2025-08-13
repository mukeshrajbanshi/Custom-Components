package com.example.mydemoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class OtpInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_info)
        val buttonNav = findViewById<Button>(R.id.bottom_widget)
        val showBackButton = intent.getBooleanExtra("SHOW_BACK_BUTTON", false)

        val backButton = findViewById<ImageButton>(R.id.back_icon)
        backButton.visibility = if (showBackButton) View.VISIBLE else View.GONE


        backButton.setOnClickListener {
            Log.e("OtpInfo", "button clicked in info!", )
            onBackPressedDispatcher.onBackPressed() // Recommended for modern apps
        }

//        val firstNameInput = findViewById<CustomInputField>(R.id.custom_input)
//        val name = firstNameInput.getText()
//        val showUpdatedText = findViewById<TextView>(R.id.show_text)
//        firstNameInput.setLabel("First Name",true)
//        firstNameInput.setText("Malik")
//        firstNameInput.setOnTextChanged { text ->
//            Log.d("OtpInfo", "User typed: $text")
//            showUpdatedText.text = text
//        }
//
//        val showName = firstNameInput.getText()
//        Log.d("OtpInfo", "Final Name: $showName  $showUpdatedText")



        buttonNav.setOnClickListener {
            val showBankCard = Intent(this, SuccessOrFailure::class.java)
            showBankCard.putExtra("SHOW_BANK_CARD", false)
            showBankCard.putExtra("SHOW_HEADER_TEXT", true)
            showBankCard.putExtra("BACK_SIDE_CARD", false)
            showBankCard.putExtra("SHOW_NOTE_TEXT", false)

            showBankCard.putExtra("status", "SUCCESS") // or "FAILURE"
            showBankCard.putExtra("msg", "Account Verified!")
            showBankCard.putExtra("msgHeader", "You’ve successfully verified your account.")
            showBankCard.putExtra("btnText", "Go Home")
            showBankCard.putExtra("userBlocked", false)

            startActivity(showBankCard)
        }





    }
}


