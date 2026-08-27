package com.traqvg.lesson5pomodoro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.traqvg.lesson5pomodoro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var workDuration = PomodoroConfig.WORK_DURATION_DEFAULT
    private var shortBreakDuration = PomodoroConfig.SHORT_BREAK_DURATION_DEFAULT
    private var longBreakDuration = PomodoroConfig.LONG_BREAK_DURATION_DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    override fun onStart() {
        super.onStart()
        restorePomodoroConfigValue()
        updateAllDurations()
    }

    override fun onStop() {
        savePomodoroConfigValue()
        super.onStop()
    }

    private fun setupView() = with(binding) {
        buttonAddWorkDuration.setOnClickListener {
            workDuration = (workDuration + 5).coerceIn(PomodoroConfig.WORK_DURATION_MIN, PomodoroConfig.WORK_DURATION_MAX)
            updateWorkDuration()
        }
        buttonReduceWorkDuration.setOnClickListener {
            workDuration = (workDuration - 5).coerceIn(PomodoroConfig.WORK_DURATION_MIN, PomodoroConfig.WORK_DURATION_MAX)
            updateWorkDuration()
        }
        buttonAddShortBreakDuration.setOnClickListener {
            shortBreakDuration = (shortBreakDuration + 5).coerceIn(PomodoroConfig.SHORT_BREAK_DURATION_MIN, PomodoroConfig.SHORT_BREAK_DURATION_MAX)
            updateShortBreakDuration()
        }
        buttonReduceShortBreakDuration.setOnClickListener {
            shortBreakDuration = (shortBreakDuration - 5).coerceIn(PomodoroConfig.SHORT_BREAK_DURATION_MIN, PomodoroConfig.SHORT_BREAK_DURATION_MAX)
            updateShortBreakDuration()
        }
        buttonAddLongBreakDuration.setOnClickListener {
            longBreakDuration = (longBreakDuration + 5).coerceIn(PomodoroConfig.LONG_BREAK_DURATION_MIN, PomodoroConfig.LONG_BREAK_DURATION_MAX)
            updateLongBreakDuration()
        }
        buttonReduceLongBreakDuration.setOnClickListener {
            longBreakDuration = (longBreakDuration - 5).coerceIn(PomodoroConfig.LONG_BREAK_DURATION_MIN, PomodoroConfig.LONG_BREAK_DURATION_MAX)
            updateLongBreakDuration()
        }
        buttonReady.setOnClickListener {
            val config = Config(workDuration, shortBreakDuration, longBreakDuration)
            startActivity(TimerActivity.newIntent(this@MainActivity, config))
        }
    }

    private fun updateAllDurations() {
        updateWorkDuration()
        updateShortBreakDuration()
        updateLongBreakDuration()
    }

    private fun updateWorkDuration() {
        binding.textViewWorkDuration.text = getString(R.string.duration_value, workDuration)
    }

    private fun updateShortBreakDuration() {
        binding.textViewShortDuration.text = getString(R.string.duration_value, shortBreakDuration)
    }

    private fun updateLongBreakDuration() {
        binding.textViewLongDuration.text = getString(R.string.duration_value, longBreakDuration)
    }

    private fun savePomodoroConfigValue() {
        LocalStorage.saveConfig(this, workDuration, shortBreakDuration, longBreakDuration)
    }

    private fun restorePomodoroConfigValue() {
        val (work, shortBreak, longBreak) = LocalStorage.getConfig(this)
        workDuration = work
        shortBreakDuration = shortBreak
        longBreakDuration = longBreak
    }
}
