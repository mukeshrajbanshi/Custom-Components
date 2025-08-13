package com.example.mydemoapplication

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat


class UserCardControls @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {

    private val iconImageView: ImageView
    private val titleTextView: TextView
    private var onCardClick: (() -> Unit)? = null


    init {
        LayoutInflater.from(context).inflate(R.layout.user_card_controls, this, true)
//        gravity = android.view.Gravity.CENTER

        iconImageView = findViewById(R.id.iconImageView)
        titleTextView = findViewById(R.id.titleTextView)
        // Set the view clickable
        this.isClickable = true
        this.isFocusable = true
//         Handle internal click event
        this.setOnClickListener {
            onCardClick?.invoke()
        }

//        attrs?.let {
//            val typedArray = context.obtainStyledAttributes(it, R.styleable.UserCardControls, 0, 0)
//            val title = typedArray.getString(R.styleable.UserCardControls_buttonTitle) ?: ""
//            val iconRes = typedArray.getResourceId(R.styleable.UserCardControls_buttonIcon, -1)
//
//            setTitle(title)
//            if (iconRes != -1) {
//                setIcon(iconRes)
//            }
//            typedArray.recycle()
//        }
    }





//     Set custom click listener from outside
//    fun setOnCardClickListener(listener: () -> Unit) {
//        onCardClick = listener
//    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }


fun setIcon(drawable: Drawable) {
    iconImageView.setImageDrawable(drawable)
}
}
