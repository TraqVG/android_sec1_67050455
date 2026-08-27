package com.traqvg.lesson5pomodoro

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Config(
    val workDuration: Int,
    val shortBreakDuration: Int,
    val longBreakDuration: Int
) : Parcelable
