package com.example.mydemoapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import org.w3c.dom.Text
import android.widget.TextView


//import androidx.core.content.ContextCompat


class CardControlsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_controls)




//        val cardData = findViewById<CustomCard>(R.id.card_usage)
        val card = findViewById<CustomCard>(R.id.custom_card)
        val firstCard = findViewById<CustomCard>(R.id.custom_card_one)
        val secondCard = findViewById<CustomCard>(R.id.custom_card_two)
        val thirdCard = findViewById<CustomCard>(R.id.custom_card_three)
        val bottomButton = findViewById<Button>(R.id.material_buttons_One)
//        val cardHolder = findViewById<TextView>(R.id.material_buttons_One)
//        cardHolder.text = "Mukesh"

        card.setTitle("Change PIN")
        card.setSubtitle("Set or change card PIN")
        card.setIcon(ContextCompat.getDrawable(this, R.drawable.card_usage)!!) // PNG or SVG
        card.setOnClickListener{
            val  pass = Intent(this, MainActivity ::class.java)
            startActivity(pass)
        }

        firstCard.setTitle("Card Usage")
        firstCard.setSubtitle("Set your usage on card")
        firstCard.setIcon(ContextCompat.getDrawable(this, R.drawable.card_usage)!!)


        secondCard.setTitle("Card Limit")
        secondCard.setSubtitle("Set your usage limit on card")
        secondCard.setIcon(ContextCompat.getDrawable(this, R.drawable.card_limits) !!)

        thirdCard.setTitle("Replace Card")
        thirdCard.setSubtitle("Report lost, stolen card")
        thirdCard.setIcon(ContextCompat.getDrawable(this, R.drawable.card_limits)!!)

        secondCard.setOnCardClickListener {
            Log.d("MainActivity", "Card clicked")
            val navigate = Intent(this, MainActivity::class.java)
            startActivity(navigate)
        }

        bottomButton.setOnClickListener {
            Log.i("OtpInfo", "check clicked!")
            val showBackButton = Intent(this, OtpInfo::class.java)
            showBackButton.putExtra("SHOW_BACK_BUTTON", true)
            startActivity(showBackButton)
        }

    }


}






