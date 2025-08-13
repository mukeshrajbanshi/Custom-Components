package com.example.mydemoapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView


class BankCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {

    private val cardNumber: TextView
    private val cardHolder: TextView
    private val cardExpiry: TextView
//    private val visaLogo: TextView
    private var onCardClick: (() -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.mint_card_view, this, true)

        cardNumber = findViewById(R.id.card_number)
        cardHolder = findViewById(R.id.cardholder_name)
        cardExpiry = findViewById(R.id.card_expiry)
//        visaLogo = findViewById(R.id.visa_logo)
        // Set the view clickable
        this.isClickable = true
        this.isFocusable = true
        // Handle internal click event
        this.setOnClickListener {
            onCardClick?.invoke()
        }
    }

    fun setCardDetails(
        number: String,
        holder: String,
        expiry: String
    ) {
        cardNumber.text = number
        cardHolder.text = holder
        cardExpiry.text = "Expires on: $expiry"
    }

    // Set custom click listener from outside
    fun setOnCardClickListener(listener: () -> Unit) {
        onCardClick = listener
    }
}
