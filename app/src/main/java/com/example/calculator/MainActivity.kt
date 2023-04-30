package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            binding.bSeven,
            binding.bPoint,
            binding.bPlus,
            binding.bMinus,
            binding.bMultiply
        )

        val numberStringBuilder = StringBuilder()

        buttonsList.forEach { button->
            button.setOnClickListener {
                numberStringBuilder.append(button.text)
                binding.tvResult.text = numberStringBuilder
            }
        }

        binding.bEqual.setOnClickListener {
            val expression = Expression(binding.tvResult.text.toString())
            binding.tvResult.text = expression.evaluate().numberValue.toString()
            numberStringBuilder.clear()
        }
    }
}