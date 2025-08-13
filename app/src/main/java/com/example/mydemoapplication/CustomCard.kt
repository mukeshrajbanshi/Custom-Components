package com.example.mydemoapplication

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class CustomCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val icon: ImageView
    private val title: TextView
    private val subtitle: TextView
    private val arrow: ImageView
    private var onCardClickListener: (() -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_custom_card, this, true)
        icon = findViewById(R.id.iv_icon)
        title = findViewById(R.id.tv_title)
        subtitle = findViewById(R.id.tv_subtitle)
        arrow = findViewById(R.id.iv_arrow)

        this.setOnClickListener {
            Log.d("CustomCard", "CardView clicked")
            onCardClickListener?.invoke()
        }
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun setSubtitle(text: String) {
        subtitle.text = text
    }

    fun setIcon(drawable: Drawable) {
        icon.setImageDrawable(drawable)
    }

    fun hideArrow() {
        arrow.visibility = GONE
    }

    fun showArrow() {
        arrow.visibility = VISIBLE
    }
    fun setOnCardClickListener(listener: () -> Unit) {
        onCardClickListener = listener
    }
}
