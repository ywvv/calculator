package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import com.ezylang.evalex.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonsList = listOf(
            binding.bZero,
            binding.bOne,
            binding.bTwo,
            binding.bThree,
            binding.bFour,
            binding.bFive,
            binding.bSix,
            binding.bSeven,
            binding.bEight,
            binding.bNine,
            binding.bPoint,
            binding.bPlus,
            binding.bMinus,
            binding.bMultiply,
            binding.bDivide
        )

        val numberStringBuilder = StringBuilder()

        buttonsList.forEach { button ->
            button.setOnClickListener {
                numberStringBuilder.append(button.text)
                binding.tvResult.text = numberStringBuilder
            }
        }

        binding.bEqual.setOnClickListener {
            try {
                val expression = Expression(binding.tvResult.text.toString())
                val expressionResult = expression.evaluate().numberValue.toString()
                binding.tvResult.text = expressionResult
                numberStringBuilder.clear()
                numberStringBuilder.append(expressionResult)
            } catch (t: Throwable) {
                Toast.makeText(this@MainActivity, "Exception: $t", Toast.LENGTH_LONG).show()
            }
        }

        binding.bClear.setOnClickListener {
            binding.tvResult.text = "0"
            numberStringBuilder.clear()
        }

        binding.bBack.setOnClickListener {
            numberStringBuilder.deleteCharAt(numberStringBuilder.lastIndex)
            binding.tvResult.text = numberStringBuilder
        }
    }
}