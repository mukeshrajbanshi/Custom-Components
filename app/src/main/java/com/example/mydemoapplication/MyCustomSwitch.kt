package com.example.mydemoapplication

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet

import android.view.MotionEvent
import android.view.View

import androidx.interpolator.view.animation.FastOutSlowInInterpolator

class MyCustomSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var isChecked = false
    private var thumbPosition = 0f
    private var trackWidth = 0f
    private var trackHeight = 0f
    private var thumbRadius = 0f

    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val checkPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val checkPath = Path()

    private val trackColorOff = Color.parseColor("#CCCCCC")
    private val trackColorOn = Color.parseColor("#2196F3")
    private val thumbColor = Color.WHITE

    private var animator: ValueAnimator? = null
    private var onCheckedChangeListener: ((Boolean) -> Unit)? = null

    init {
        setupPaints()
        isClickable = true
        isFocusable = true
    }

    private fun setupPaints() {
        trackPaint.style = Paint.Style.FILL
        thumbPaint.style = Paint.Style.FILL
        thumbPaint.color = thumbColor

        checkPaint.style = Paint.Style.STROKE
        checkPaint.strokeWidth = 5f //Thicker stroke for larger checkmark
        checkPaint.strokeCap = Paint.Cap.ROUND
        checkPaint.strokeJoin = Paint.Join.ROUND
        checkPaint.color = Color.WHITE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = 80 // dp equivalent
        val height = 40 // dp equivalent

        trackWidth = width * resources.displayMetrics.density
        trackHeight = height * resources.displayMetrics.density
        thumbRadius = trackHeight * 0.35f // Slightly smaller to add padding

        setMeasuredDimension(trackWidth.toInt(), trackHeight.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw track
        val trackRect = RectF(0f, 0f, trackWidth, trackHeight)
        val trackColor = if (isChecked) trackColorOn else trackColorOff
        trackPaint.color = trackColor
        canvas.drawRoundRect(trackRect, trackHeight / 2, trackHeight / 2, trackPaint)

        // Draw checkmark on left side when checked
        if (isChecked && thumbPosition > 0.3f) {
            drawCheckMarkOnTrack(canvas)
        }

        // Calculate thumb position with padding
        val thumbCenterY = trackHeight / 2
        val padding = trackHeight * 0.1f  // 10% padding on each side
        val thumbCenterX = padding + thumbRadius + thumbPosition * (trackWidth - 2 * padding - 2 * thumbRadius)
//        val thumbCenterX = thumbRadius + thumbPosition * (trackWidth - 2 * thumbRadius)

        // Draw thumb
        canvas.drawCircle(thumbCenterX, thumbCenterY, thumbRadius, thumbPaint)
    }

    private fun drawCheckMarkOnTrack(canvas: Canvas) {
        val alpha = ((thumbPosition - 0.3f) / 0.7f).coerceIn(0f, 1f)
        checkPaint.alpha = (255 * alpha).toInt()
        checkPaint.color = Color.WHITE
        val padding = trackHeight * 0.1f
        val checkCenterX = padding + thumbRadius  // Same as thumb left position
        val checkCenterY = trackHeight / 2

        checkPath.reset()
        val size = trackHeight * 0.40f // Larger checkmark size
        checkPath.moveTo(checkCenterX - size * 0.4f, checkCenterY + size * 0.1f)
        checkPath.lineTo(checkCenterX - size * 0.1f, checkCenterY + size * 0.4f)
        checkPath.lineTo(checkCenterX + size * 0.4f, checkCenterY - size * 0.3f)
        canvas.drawPath(checkPath, checkPaint)
    }

    private fun drawCheckMark(canvas: Canvas, centerX: Float, centerY: Float) {
        val alpha = ((thumbPosition - 0.5f) * 2f).coerceIn(0f, 1f)
        checkPaint.alpha = (255 * alpha).toInt()

        checkPath.reset()
        val size = thumbRadius * 0.4f // Smaller size to fit inside thumb
        checkPath.moveTo(centerX - size * 0.4f, centerY + size * 0.1f)
        checkPath.lineTo(centerX - size * 0.1f, centerY + size * 0.4f)
        checkPath.lineTo(centerX + size * 0.4f, centerY - size * 0.3f)
        canvas.drawPath(checkPath, checkPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                toggle()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    fun toggle() {
        setChecked(!isChecked, true)
    }

    fun setChecked(checked: Boolean, animate: Boolean = false) {
        if (isChecked == checked) return

        isChecked = checked

        if (animate) {
            animateThumb()
        } else {
            thumbPosition = if (isChecked) 1f else 0f
            invalidate()
        }

        onCheckedChangeListener?.invoke(isChecked)
    }

    private fun animateThumb() {
        animator?.cancel()

        val startPosition = thumbPosition
        val endPosition = if (isChecked) 1f else 0f

        animator = ValueAnimator.ofFloat(startPosition, endPosition).apply {
            duration = 200
            interpolator = FastOutSlowInInterpolator()
            addUpdateListener { animation ->
                thumbPosition = animation.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    fun setOnCheckedChangeListener(listener: (Boolean) -> Unit) {
        onCheckedChangeListener = listener
    }

    fun isChecked(): Boolean = isChecked
}
