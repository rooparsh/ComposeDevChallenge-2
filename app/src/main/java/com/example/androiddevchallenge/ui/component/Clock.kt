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
package com.example.androiddevchallenge.ui.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.data.Time

@ExperimentalAnimationApi
@Composable
fun Clock(time: Time) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Digit(value = (time.hour / 10).toString())
        Digit(value = (time.hour % 10).toString())
        Digit(value = ":")
        Digit(value = (time.minute / 10).toString())
        Digit(value = (time.minute % 10).toString())
        Digit(value = ":")
        Digit(value = (time.second / 10).toString())
        Digit(value = (time.second % 10).toString())
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun ClockPreview() {
    val time = Time(0, 4, 30)
    Clock(time)
}
