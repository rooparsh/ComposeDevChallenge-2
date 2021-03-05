package com.example.androiddevchallenge.ui.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalAnimationApi
@Composable
fun Digit(value: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .padding(4.dp)
            .background(color = MaterialTheme.colors.primary)
            .clip(RoundedCornerShape(percent = 10))
    ) {
        Text(text = value, fontSize = 20.sp, color = Color.White)
    }
}


@ExperimentalAnimationApi
@Composable
@Preview
fun DigitPreview() {
    Digit(value = "3")
}