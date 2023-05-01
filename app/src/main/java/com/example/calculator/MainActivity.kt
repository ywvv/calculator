package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import com.ezylang.evalex.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val numberStringBuilder = StringBuilder()
    private val historyList = mutableListOf<String>()
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
            saveToHistory()
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

        bHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            intent.putExtra("history_list", historyList.toTypedArray())
            startActivity(intent)
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

    private fun saveToHistory() {
        val stringExpression = numberStringBuilder.toString()
        historyList.add(stringExpression)
    }
}