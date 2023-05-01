package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.calculator.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historyList = intent.getStringArrayExtra("history_list") ?: arrayOf()

        val itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historyList)

        binding.lvHistory.adapter = itemsAdapter
    }
}