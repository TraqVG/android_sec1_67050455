package com.traqvg.lesson5pomodoro

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.traqvg.lesson5pomodoro.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_CONFIG = "config"

        fun newIntent(context: Context, config: Config): Intent =
            Intent(context, TimerActivity::class.java).putExtra(EXTRA_CONFIG, config)
    }

    private val binding: ActivityTimerBinding by lazy {
        ActivityTimerBinding.inflate(layoutInflater)
    }

    private var config: Config? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        restoreBundle()
        showConfig()
    }

    private fun restoreBundle() {
        config = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_CONFIG, Config::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CONFIG)
        }
    }

    private fun showConfig() {
        val value = config ?: return
        binding.textViewState.text = getString(R.string.ready)
        binding.textViewMinute.text = getString(R.string.time_value, value.workDuration)
        binding.textViewSecond.text = getString(R.string.time_value, 0)
        binding.textViewConfig.text = getString(
            R.string.timer_config,
            value.workDuration,
            value.shortBreakDuration,
            value.longBreakDuration
        )
    }
}
