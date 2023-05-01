package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import com.ezylang.evalex.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val numberStringBuilder = StringBuilder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() = with(binding) {
        val buttonsList = listOf(
            bZero,
            bOne,
            bTwo,
            bThree,
            bFour,
            bFive,
            bSix,
            bSeven,
            bEight,
            bNine,
            bPoint,
            bPlus,
            bMinus,
            bMultiply,
            bDivide
        )


        buttonsList.forEach { button ->
            button.setOnClickListener {
                numberStringBuilder.append(button.text)
                tvResult.text = numberStringBuilder
            }
        }

        bEqual.setOnClickListener {
            calculate()
        }

        bClear.setOnClickListener {
            tvResult.text = "0"
            numberStringBuilder.clear()
        }

        bBack.setOnClickListener {
            numberStringBuilder.deleteCharAt(numberStringBuilder.lastIndex)
            tvResult.text = numberStringBuilder
        }
    }

    private fun ActivityMainBinding.calculate() {
        try {
            val expression = Expression(tvResult.text.toString())
            val expressionResult = expression.evaluate().numberValue.toInt().toString()
            tvResult.text = expressionResult
            numberStringBuilder.clear()
            numberStringBuilder.append(expressionResult)
        } catch (t: Throwable) {
            Toast.makeText(this@MainActivity, "Exception: $t", Toast.LENGTH_LONG).show()
        }
    }
}