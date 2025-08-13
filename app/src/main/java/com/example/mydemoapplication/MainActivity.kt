package com.example.mydemoapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaDrm.LogMessage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val card = findViewById<CustomCard>(R.id.custom_card)
        val firstCard = findViewById<CustomCard>(R.id.custom_card_one)
        val secondCard = findViewById<CustomCard>(R.id.custom_card_two)
        val thirdCard = findViewById<CustomCard>(R.id.custom_card_three)
        val bottomButton = findViewById<Button>(R.id.bottom_button)

//        val backButtonShow = findViewById<ImageButton>(R.id.back_icon)

        // Set icon, title, subtitle
        card.setTitle("1258 xxxx 1212 - Credit")
        card.setSubtitle("Mukesh")
        card.setIcon(ContextCompat.getDrawable(this, R.drawable.small_credit_card)!!)
        firstCard.setTitle("1223 xxxx 1239 - Debit")
        firstCard.setSubtitle("Mukesh")
        firstCard.setIcon(ContextCompat.getDrawable(this, R.drawable.small_credit_card)!!)
        secondCard.setTitle("1295 xxxx 1267 - Credit")
        secondCard.setSubtitle("Mukesh")
        secondCard.setIcon(ContextCompat.getDrawable(this, R.drawable.small_credit_card)!!)

        thirdCard.setTitle("1250 xxxx 1296 - Debit")
        thirdCard.setSubtitle("Mukesh")
        thirdCard.setIcon(ContextCompat.getDrawable(this, R.drawable.small_credit_card)!!)


        // ✅ Test 2: Custom click listener
        secondCard.setOnCardClickListener {
            val intent = Intent(this, InfoWidgetView::class.java)
            startActivity(intent)
        }

        bottomButton.setOnClickListener{
            Log.e("CardControlsActivity", "button clicked check in main!")
            val  value = Intent(this, InfoWidgetView ::class.java)
            startActivity(value)
        }

    }
}