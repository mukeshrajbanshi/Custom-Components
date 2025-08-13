package com.example.mydemoapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout

class CustomHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val icon: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_header, this, true)
        icon = findViewById(R.id.phone_icon)
    }

    // Optional: expose a method to change the icon programmatically
//    fun setIcon(resId: Int) {
//        icon.setImageResource(resId)
//    }
}
