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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.PlayingState
import com.example.androiddevchallenge.data.Time
import com.example.androiddevchallenge.ui.component.Clock
import com.example.androiddevchallenge.ui.component.ClockPreview
import com.example.androiddevchallenge.ui.component.Controller
import com.example.androiddevchallenge.ui.component.CountDownIndicator
import com.example.androiddevchallenge.ui.component.NumberPicker
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private var time = Time(0, 0, 0)

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val playingState = viewModel.playingState.observeAsState().value

                        Text(
                            text = "Relax Down",
                            style = MaterialTheme.typography.h1
                        )
                        Spacer(modifier = Modifier.height(30.dp))

                        if (playingState?.equals(PlayingState.Reset) == true) {
                            NumberPicker(
                                context = LocalContext.current,
                                selectedHour = time.hour,
                                selectedMinute = time.minute,
                                selectedSecond = time.second,
                                onHourSelectListener = { time.apply { hour = it.toLong() } },
                                onMinSelectListener = { time.apply { minute = it.toLong() } },
                                onSecSelectListener = { time.apply { second = it.toLong() } }
                            )
                        } else {
                            Clock(viewModel.time.observeAsState().value ?: time)

                            Spacer(modifier = Modifier.height(30.dp))

                            CountDownIndicator(
                                progress = viewModel.progress.observeAsState(1f).value.toFloat() /
                                    viewModel.timer,
                                playingState = playingState ?: PlayingState.Reset,
                                size = 400,
                                stroke = 12
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Controller(
                            playingState = playingState ?: PlayingState.Reset,
                            onStartClick = { viewModel.startTimer(time) },
                            onResumeClick = { viewModel.resumeTimer() },
                            onPauseClick = { viewModel.pauseTimer() },
                            onStopClick = { viewModel.stopTimer() },
                            onResetClick = { viewModel.resetTimer() }
                        )
                    }
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(content: @Composable () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        content()
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp {
            ClockPreview()
            Spacer(modifier = Modifier.height(30.dp))
            Controller(
                playingState = PlayingState.Playing,
                onStartClick = { },
                onResumeClick = { },
                onPauseClick = { },
                onStopClick = { },
                onResetClick = { }
            )
            Spacer(modifier = Modifier.height(30.dp))
            CountDownIndicator(
                progress = 50f,
                playingState = PlayingState.Playing,
                size = 250,
                stroke = 12
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp {
            ClockPreview()
            Spacer(modifier = Modifier.height(30.dp))
            Controller(
                playingState = PlayingState.Playing,
                onStartClick = { },
                onPauseClick = { },
                onResumeClick = { },
                onStopClick = { },
                onResetClick = { }
            )
            Spacer(modifier = Modifier.height(30.dp))
            CountDownIndicator(
                progress = 50f,
                playingState = PlayingState.Playing,
                size = 300,
                stroke = 12
            )
        }
    }
}
