package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import com.ezylang.evalex.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) = with(binding) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        val numberStringBuilder = StringBuilder()

        buttonsList.forEach { button ->
            button.setOnClickListener {
                numberStringBuilder.append(button.text)
                tvResult.text = numberStringBuilder
            }
        }

        bEqual.setOnClickListener {
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

        bClear.setOnClickListener {
            tvResult.text = "0"
            numberStringBuilder.clear()
        }

        bBack.setOnClickListener {
            numberStringBuilder.deleteCharAt(numberStringBuilder.lastIndex)
            tvResult.text = numberStringBuilder
        }
    }
}