package com.example.mydemoapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat


class InfoWidgetView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_widget_view)

        val mintCardView = findViewById<BankCard>(R.id.mint_card_view)

        val lockCardButton = findViewById<UserCardControls>(R.id.lock_card)

        val walletIcon = findViewById<UserCardControls>(R.id.wallet_card)

        val linkWallet = findViewById<UserCardControls>(R.id.wallet_link)
        val creditCard = findViewById<UserCardControls>(R.id.credit_card)
        val showPassword = findViewById<UserCardControls>(R.id.show_password)

        mintCardView.setCardDetails(
        number = "1234 xxxx xxxx 9325",
        holder = "Mukesh Rajbanshi",
        expiry = "10/29"
        )

        mintCardView.setOnCardClickListener {
        val intent = Intent(this, CardControlsActivity::class.java)
        startActivity(intent)
        }

        lockCardButton.setTitle("Lock Card")
        lockCardButton.setIcon(ContextCompat.getDrawable(this, R.drawable.lock)!!)

        walletIcon.setTitle("Manage Card")
        walletIcon.setIcon(ContextCompat.getDrawable(this,R.drawable.controls)!!)

        linkWallet.setTitle("Link to Wallet")
        linkWallet.setIcon(ContextCompat.getDrawable(this, R.drawable.wallet)!!)

        creditCard.setTitle("Create Virtual Card")
        creditCard.setIcon(ContextCompat.getDrawable(this, R.drawable.credit_card)!!)

        showPassword.setTitle("View Dynamic CVV")
        showPassword.setIcon(ContextCompat.getDrawable(this, R.drawable.password)!!)


        val bottomButton = findViewById<Button>(R.id.material_button)
        bottomButton.setOnClickListener{
        val  value = Intent(this, CardControlsActivity ::class.java)
        startActivity(value)
        }
    }

}