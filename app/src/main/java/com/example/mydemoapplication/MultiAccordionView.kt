package com.example.mydemoapplication

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MultiAccordionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val sectionViews = mutableListOf<View>()
    private var currentlyExpanded: View? = null

    init {
        orientation = VERTICAL
    }

    fun addSection(title: String, contentView: View) {
        val sectionView = LayoutInflater.from(context).inflate(R.layout.item_accordion_section, this, false)

        val headerLayout = sectionView.findViewById<LinearLayout>(R.id.headerLayout)
        val titleText = sectionView.findViewById<TextView>(R.id.titleText)
        val arrowIcon = sectionView.findViewById<ImageView>(R.id.arrowIcon)
        val contentContainer = sectionView.findViewById<FrameLayout>(R.id.contentContainer)

        titleText.text = title
        contentContainer.addView(contentView)

        headerLayout.setOnClickListener {
            val isAlreadyExpanded = contentContainer.visibility == View.VISIBLE

            // Collapse all
            sectionViews.forEach { view ->
                view.findViewById<FrameLayout>(R.id.contentContainer).visibility = View.GONE
                view.findViewById<ImageView>(R.id.arrowIcon).setImageResource(R.drawable.arrow_down)
                val otherHeader = view.findViewById<LinearLayout>(R.id.headerLayout)
                otherHeader.setBackgroundResource(R.drawable.bg_accordion_item)

            }

            if (!isAlreadyExpanded) {
                contentContainer.visibility = View.VISIBLE
                arrowIcon.setImageResource(R.drawable.arrow_up)
                headerLayout.setBackgroundResource(R.drawable.bg_active_accordion_item)
                currentlyExpanded = sectionView
            } else {
                currentlyExpanded = null
            }
        }

        sectionViews.add(sectionView)
        addView(sectionView)
    }
}

