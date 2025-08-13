package com.example.mydemoapplication

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialButton(context, attrs, defStyleAttr) {

    enum class ButtonVariant { PRIMARY, SECONDARY, OUTLINED, TEXT }
    enum class ButtonSize { SMALL, MEDIUM, LARGE }

    private var variant: ButtonVariant = ButtonVariant.PRIMARY
    private var size: ButtonSize = ButtonSize.MEDIUM

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomButton)

//            variant = ButtonVariant.values()[
//                    typedArray.getInt(R.styleable.CustomButton_buttonVariant, 0)
//            ]
//            size = ButtonSize.values()[
//                    typedArray.getInt(R.styleable.CustomButton_buttonSize, 1)
//            ]
            val variantIndex = typedArray.getInt(R.styleable.CustomButton_buttonVariant, 0)
            variant = ButtonVariant.values().getOrNull(variantIndex) ?: ButtonVariant.PRIMARY

            val sizeIndex = typedArray.getInt(R.styleable.CustomButton_buttonSize, 1)
            size = ButtonSize.values().getOrNull(sizeIndex) ?: ButtonSize.MEDIUM


            typedArray.recycle()
        }

        setupButton()
    }

    private fun setupButton() {
        when (variant) {
            ButtonVariant.PRIMARY -> {
                backgroundTintList = ColorStateList.valueOf(
                    try {
                        ContextCompat.getColor(context, R.color.primary)
                    } catch (e: Resources.NotFoundException) {
                        Color.BLUE // fallback
                    }

                )
                setTextColor(try {
                    ContextCompat.getColor(context, R.color.white)
                } catch (e: Resources.NotFoundException) {
                    Color.WHITE // fallback
                })
            }
            ButtonVariant.SECONDARY -> {
                backgroundTintList = ColorStateList.valueOf(
                    try {
                        ContextCompat.getColor(context, R.color.secondary)
                    } catch (e: Resources.NotFoundException) {
                        Color.BLUE // fallback
                    }
//                    ContextCompat.getColor(context, R.color.secondary)
                )
                setTextColor(try {
                    ContextCompat.getColor(context, R.color.white)
                } catch (e: Resources.NotFoundException) {
                    Color.WHITE // fallback
                })
            }
            ButtonVariant.OUTLINED -> {
                backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
                strokeColor = ColorStateList.valueOf(
                    try {
                        ContextCompat.getColor(context, R.color.primary)
                    } catch (e: Resources.NotFoundException) {
                        Color.BLUE // fallback
                    }
//                    ContextCompat.getColor(context, R.color.primary)
                )
                strokeWidth = 2.dpToPx()
                setTextColor(try {
                    ContextCompat.getColor(context, R.color.primary)
                } catch (e: Resources.NotFoundException) {
                    Color.WHITE // fallback
                })
            }
            ButtonVariant.TEXT -> {
                backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
                setTextColor(try {
                    ContextCompat.getColor(context, R.color.primary)
                } catch (e: Resources.NotFoundException) {
                    Color.WHITE // fallback
                })
            }
        }

        when (size) {
            ButtonSize.SMALL -> {
                textSize = 12f
                setPadding(12.dpToPx(), 8.dpToPx(), 12.dpToPx(), 8.dpToPx())
            }
            ButtonSize.MEDIUM -> {
                textSize = 14f
                setPadding(16.dpToPx(), 12.dpToPx(), 16.dpToPx(), 12.dpToPx())
            }
            ButtonSize.LARGE -> {
                textSize = 16f
                setPadding(20.dpToPx(), 16.dpToPx(), 20.dpToPx(), 16.dpToPx())
            }
        }
    }

    fun setVariant(newVariant: ButtonVariant) {
        variant = newVariant
        setupButton()
    }

    fun setSize(newSize: ButtonSize) {
        size = newSize
        setupButton()
    }
}



// Extension function for dp to px conversion
fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()