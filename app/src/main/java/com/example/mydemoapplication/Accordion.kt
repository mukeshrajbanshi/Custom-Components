package com.example.mydemoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class Accordion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accordian)

        val multiAccordion = findViewById<MultiAccordionView>(R.id.multiAccordion)

        val domesticContent = LayoutInflater.from(this).inflate(R.layout.accordion_content_domestic, null)
        val internationalContent = LayoutInflater.from(this).inflate(R.layout.accordion_content_international, null)
        val customInputField = domesticContent.findViewById<CustomInputField>(R.id.custom_input_field)
        customInputField.setEnabledInput(false)
        customInputField.setText("$ 50,000")

        multiAccordion.addSection("Domestic Usage", domesticContent)
        multiAccordion.addSection("International Usage", internationalContent)


//        val domesticAccordion = findViewById<AccordionView>(R.id.domesticAccordion)
//        val internationalAccordion = findViewById<AccordionView>(R.id.internationalAccordion)
//
//        val customContentView = LayoutInflater.from(this).inflate(R.layout.accordion_content_domestic, null)
//        val customInterContentView = LayoutInflater.from(this).inflate(R.layout.accordion_content_international, null)
//        domesticAccordion.setAccordionContent(customContentView)
//        internationalAccordion.setAccordionContent(customInterContentView)
//        val accordions = listOf(domesticAccordion, internationalAccordion)
//
//
//        accordions.forEach { accordion ->
//            accordion.setOnHeaderClickListener {
//                accordions.forEach {
//                    if (it != accordion) it.collapse()
//                }
//                accordion.toggle()
//            }
//        }


    }
}


