package com.example.mydemoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class SuccessOrFailure : AppCompatActivity() {

    companion object {
        const val STATUS_KEY = "status"
        const val MSG_KEY = "msg"
        const val HEADER_KEY = "msgHeader"
        const val BUTTON_TEXT_KEY = "btnText"
        const val USER_BLOCKED_KEY = "userBlocked"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_or_failure)
        val status = intent.getStringExtra(STATUS_KEY) ?: "FAILURE"
        val msg = intent.getStringExtra(MSG_KEY) ?: "Something went wrong."
        val msgHeader = intent.getStringExtra(HEADER_KEY) ?: ""
        val buttonText = intent.getStringExtra(BUTTON_TEXT_KEY) ?: "Go Back"
        val userBlocked = intent.getBooleanExtra(USER_BLOCKED_KEY, false)

        val imageIcon = findViewById<ImageView>(R.id.status_icon)
        val titleText = findViewById<TextView>(R.id.msg_text)
        val subText = findViewById<TextView>(R.id.msg_header_text)
        val actionButton = findViewById<Button>(R.id.button_success)

        if (status == "SUCCESS") {
            imageIcon.setImageResource(R.drawable.success)
        } else {
            imageIcon.setImageResource(R.drawable.error)
        }
        titleText.text = msg
        subText.text = msgHeader
        actionButton.text = buttonText

        actionButton.setOnClickListener {
            Log.d("SuccessOrFailure", "Button clicked - status: $status")

            if (userBlocked) {
                // Future: logout logic can be placed here
                Toast.makeText(this, "User is blocked. Logging out...", Toast.LENGTH_SHORT).show()
                // You can call logout logic here
            } else {
                // Default behavior: go back to MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }


        val buttonMessage = findViewById<Button>(R.id.button_success)
        val showBankCard = intent.getBooleanExtra("SHOW_BANK_CARD", false)
        val showHeaderText = intent.getBooleanExtra("SHOW_HEADER_TEXT", false)
        val showBackSideCard = intent.getBooleanExtra("BACK_SIDE_CARD", false)
        val showNoteText = intent.getBooleanExtra("SHOW_NOTE_TEXT", false)

        val buttonClicked = findViewById<BankCard>(R.id.mint_bank_card)
        val headerText = findViewById<TextView>(R.id.msg_header_text)
        val noteText = findViewById<TextView>(R.id.note_text)
        val backSideText = findViewById<ImageView>(R.id.card_back_side)

        buttonClicked.visibility = if (showBankCard) View.VISIBLE else View.GONE
        headerText.visibility = if(showHeaderText) View.VISIBLE else View.GONE
        noteText.visibility = if(showNoteText) View.VISIBLE else View.GONE
        backSideText.visibility = if(showBackSideCard) View.VISIBLE else View.GONE


        Log.d("SuccessOrFailure", "SHOW_BANK_CARD = $showBankCard")

        buttonClicked.setOnClickListener {
            Log.e("SuccessOrFailure", "button clicked in SuccessOrFailure!", )
            onBackPressedDispatcher.onBackPressed() // Recommended for modern apps
        }
        headerText.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        backSideText.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        noteText.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }



//        buttonMessage.setOnClickListener{
//            val  value = Intent(this, MainActivity ::class.java)
//            startActivity(value)
//        }
        buttonMessage.setOnClickListener{
            val  value = Intent(this, DemoTexting ::class.java)
            startActivity(value)
        }
    }



}