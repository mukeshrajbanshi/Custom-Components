package com.example.mydemoapplication

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.mydemoapplication.databinding.ViewCustomInputFieldBinding

class CustomInputField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: ViewCustomInputFieldBinding =
        ViewCustomInputFieldBinding.inflate(LayoutInflater.from(context), this, true)

    private var onTextChangedCallback: ((String) -> Unit)? = null
    private var isPasswordVisible = false
    private var minLength = 0
    private var maxLength = Int.MAX_VALUE

    init {
        orientation = VERTICAL

        // Optional: read custom attributes
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomInputField)
            val labelText = typedArray.getString(R.styleable.CustomInputField_inputType) ?: ""
            val required = typedArray.getBoolean(R.styleable.CustomInputField_inputType, false)
            binding.labelText.text = labelText
            binding.requiredAsterisk.visibility = if (required) VISIBLE else GONE
            typedArray.recycle()
        }

        binding.inputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                onTextChangedCallback?.invoke(s.toString())
                validateInput(input)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.eyeIcon.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility()
        }
        // Initial state
        binding.inputContainer.background =
            ContextCompat.getDrawable(context, R.drawable.bg_input_border)
    }

    fun setText(text: String) {
        Log.e("CustomInputField", "setText: $text", )
        binding.inputEditText.setText(text)
    }

    fun getText(): String {
        return binding.inputEditText.text.toString()
    }

    fun setOnTextChanged(callback: (String) -> Unit) {
        this.onTextChangedCallback = callback
    }
    fun setLabel(label: String, required: Boolean = false) {
        binding.labelText.text = label
        binding.requiredAsterisk.visibility = if (required) VISIBLE else GONE
    }

    fun enablePasswordToggle(enable: Boolean) {
        binding.eyeIcon.visibility = if (enable) VISIBLE else GONE
        if (enable) {
            binding.inputEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }
    private fun togglePasswordVisibility() {
        binding.inputEditText.inputType = if (isPasswordVisible) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.inputEditText.setSelection(binding.inputEditText.text.length)
        binding.eyeIcon.setImageResource(
            if (isPasswordVisible) R.drawable.eye_password else R.drawable.hide_eye
        )
    }
    private fun validateInput(input: String) {
        if (input.length < minLength) {
            showError("Minimum $minLength characters required")
        } else if (input.length > maxLength) {
            showError("Maximum $maxLength characters allowed")
        } else {
            clearError()
        }
    }


        fun showError(errorMsg: String) {
            binding.inputContainer.background =
            ContextCompat.getDrawable(context, R.drawable.bg_input_error)
             binding.errorText.visibility = VISIBLE
            binding.errorText.text = errorMsg
}

    fun clearError() {
        binding.inputContainer.background =
            ContextCompat.getDrawable(context, R.drawable.bg_input_border)
        binding.errorText.visibility = GONE
    }

    fun setMinLength(min: Int) {
        minLength = min
    }

    fun setMaxLength(max: Int) {
        maxLength = max
    }

    fun setPrefix(text: String) {
        binding.prefixText.visibility = VISIBLE
        binding.prefixText.text = text
    }

    fun setEnabledInput(enabled: Boolean) {
        binding.inputEditText.isEnabled = enabled
    }
    fun setInputType(type: String) {
        binding.inputEditText.inputType = when (type.lowercase()) {
            "text" -> InputType.TYPE_CLASS_TEXT
            "number" -> InputType.TYPE_CLASS_NUMBER
            "password" -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            "visible_password" -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            "phone" -> InputType.TYPE_CLASS_PHONE
            "email" -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            else -> InputType.TYPE_CLASS_TEXT
        }

        // Reset cursor to end
        binding.inputEditText.setSelection(binding.inputEditText.text.length)
    }




}
