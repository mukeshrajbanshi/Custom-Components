package com.example.mydemoapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class AccordionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val headerLayout: LinearLayout
    private val titleText: TextView
    private val arrowIcon: ImageView
    private val contentLayout: LinearLayout

    var isExpanded = false
        private set

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.view_accordion, this, true)

        headerLayout = findViewById(R.id.headerLayout)
        titleText = findViewById(R.id.titleText)
        arrowIcon = findViewById(R.id.arrowIcon)
        contentLayout = findViewById(R.id.contentLayout)

        headerLayout.setOnClickListener {
            toggle()
        }

        // Custom attrs (optional for XML usage)
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.AccordionView)
            val title = a.getString(R.styleable.AccordionView_accordionTitle)
            titleText.text = title
            a.recycle()
        }
    }

    fun setAccordionTitle(title: String) {
        titleText.text = title
    }

    fun setAccordionContent(view: View) {
        contentLayout.removeAllViews()
        contentLayout.addView(view)
    }

    fun expand() {
        contentLayout.visibility = View.VISIBLE
        arrowIcon.setImageResource(R.drawable.arrow_up)
        isExpanded = true
    }

    fun collapse() {
        contentLayout.visibility = View.GONE
        arrowIcon.setImageResource(R.drawable.arrow_down)
        isExpanded = false
    }
    fun setOnHeaderClickListener(onClick: () -> Unit) {
        headerLayout.setOnClickListener {
            onClick()
        }
    }

    fun toggle() {
        if (isExpanded) collapse() else expand()
    }
}
