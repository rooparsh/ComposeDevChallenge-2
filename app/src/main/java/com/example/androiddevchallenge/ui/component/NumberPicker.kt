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

import android.content.Context
import android.widget.LinearLayout
import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NumberPicker(
    context: Context,
    selectedHour: Long,
    selectedMinute: Long,
    selectedSecond: Long,
    onHourSelectListener: (value: Int) -> Unit,
    onMinSelectListener: (value: Int) -> Unit,
    onSecSelectListener: (value: Int) -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Hrs", modifier = Modifier.padding(horizontal = 10.dp))
        Text(text = "Mins", modifier = Modifier.padding(horizontal = 10.dp))
        Text(text = "Secs", modifier = Modifier.padding(horizontal = 10.dp))
    }
    AndroidView(
        factory = {
            val linearLayout = LinearLayout(context)
            linearLayout.addView(
                NumberPicker(context).apply {
                    minValue = 0
                    maxValue = 24
                    wrapSelectorWheel = true
                    value = selectedHour.toInt()
                    setOnValueChangedListener { _, _, i2 -> onHourSelectListener(i2) }
                }
            )

            linearLayout.addView(
                NumberPicker(context).apply {
                    minValue = 0
                    maxValue = 59
                    wrapSelectorWheel = true
                    value = selectedMinute.toInt()
                    setOnValueChangedListener { _, _, i2 -> onMinSelectListener(i2) }
                }
            )

            linearLayout.addView(
                NumberPicker(context).apply {
                    minValue = 0
                    maxValue = 59
                    wrapSelectorWheel = true
                    value = selectedSecond.toInt()
                    setOnValueChangedListener { _, _, i2 -> onSecSelectListener(i2) }
                }
            )
            linearLayout
        }
    )
}
