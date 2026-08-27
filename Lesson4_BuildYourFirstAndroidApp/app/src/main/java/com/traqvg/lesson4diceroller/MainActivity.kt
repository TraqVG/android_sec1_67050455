package com.traqvg.lesson4diceroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.traqvg.lesson4diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rollButton.setOnClickListener { rollDice() }
        rollDice()
    }

    private fun rollDice() {
        val result = (1..6).random()
        val drawable = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        binding.diceImage.setImageResource(drawable)
        binding.resultText.text = getString(R.string.rolled_value, result)
        binding.diceImage.contentDescription = getString(R.string.dice_description, result)
    }
}
