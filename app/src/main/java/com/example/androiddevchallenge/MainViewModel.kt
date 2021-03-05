/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.PlayingState
import com.example.androiddevchallenge.data.Time
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    companion object {
        private const val TIME_ONE_SEC_IN_MILLIS = 1000L
    }

    private val defaultTime = Time(0, 0, 0)

    var timer: Long = 0L
        private set

    private var countDownTimer: CountDownTimer? = null

    private val _time = MutableLiveData(defaultTime)
    val time: LiveData<Time> = _time

    private val _progress = MutableLiveData(timer)
    val progress: LiveData<Long> = _progress

    private val _playingState = MutableLiveData<PlayingState>(PlayingState.Reset)
    val playingState: LiveData<PlayingState> = _playingState

    fun stopTimer() {
        _progress.value = timer
        _time.value = defaultTime
        _playingState.value = PlayingState.Stopped
        countDownTimer?.cancel()
    }

    fun pauseTimer() {
        _playingState.value = PlayingState.Paused
        countDownTimer?.cancel()
    }

    fun startTimer(time: Time) {
        _playingState.value = PlayingState.Playing

        timer = (
            TimeUnit.HOURS.toMinutes(time.hour) +
                TimeUnit.MINUTES.toMillis(time.minute) +
                TimeUnit.SECONDS.toMillis(time.second)
            )
        timeTask(timer)
    }

    private fun timeTask(timer: Long) {
        countDownTimer = object : CountDownTimer(timer, TIME_ONE_SEC_IN_MILLIS) {
            override fun onTick(millis: Long) {
                val hour = TimeUnit.MILLISECONDS.toHours(millis)
                val min = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(millis)
                )

                val sec = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                )

                _progress.value = millis
                _time.value = Time(hour, min, sec)
            }

            override fun onFinish() {
                stopTimer()
            }
        }.start()
    }

    fun resumeTimer() {
        _playingState.value = PlayingState.Playing
        timeTask(_progress.value!!)
    }

    fun resetTimer() {
        stopTimer()
        _playingState.value = PlayingState.Reset
    }
}
