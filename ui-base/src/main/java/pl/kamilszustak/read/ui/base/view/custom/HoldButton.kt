package pl.kamilszustak.read.ui.base.view.custom

import android.content.Context
import android.graphics.Color
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import pl.kamilszustak.read.ui.base.R
import pl.kamilszustak.read.ui.base.databinding.HoldButtonBinding

class HoldButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyleAttribute: Int = 0,
) : ConstraintLayout(context, attributeSet, defaultStyleAttribute) {

    private lateinit var binding: HoldButtonBinding

    private var delay: Int = 0
    private lateinit var timer: CountDownTimer

    private var onFinishListener: OnFinishListener? = null

    init {
        inflateLayout()
        initializeView(attributeSet)
        setListeners()
    }

    private fun inflateLayout() {
        val view = inflate(context, R.layout.hold_button, this)
        binding = HoldButtonBinding.bind(view)
    }

    private fun initializeView(attributeSet: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.HoldButton,
            0,
            0
        ).use { attributes ->
            delay = attributes.getInteger(R.styleable.HoldButton_delay, 0)
            binding.progressIndicator.max = delay
            createTimer()

            binding.textView.text = attributes.getString(R.styleable.HoldButton_text)

            val textColor = attributes.getColor(R.styleable.HoldButton_textColor, Color.BLACK)
            binding.textView.setTextColor(textColor)
        }
    }

    private fun createTimer() {
        timer = object : CountDownTimer(delay.toLong(), 1) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (delay - millisUntilFinished).toInt()
                binding.progressIndicator.setProgressCompat(progress, false)
            }

            override fun onFinish() {
                onFinishListener?.action()
            }
        }
    }

    private fun setListeners() {
        binding.button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> startCounting()
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE -> cancelCounting()
            }

            true
        }
    }

    private fun startCounting() {
        cancelCounting()
        timer.start()
    }

    private fun cancelCounting() {
        timer.cancel()
        binding.progressIndicator.setProgressCompat(0, true)
    }

    fun setText(text: String) {
        binding.textView.text = text
    }

    fun setOnFinishListener(listener: OnFinishListener) {
        onFinishListener = listener
    }

    fun interface OnFinishListener {
        fun action()
    }
}