package com.example.androiddevchallenge.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.PlayingState

@Composable
fun CountDownIndicator(
    modifier: Modifier = Modifier,
    playingState: PlayingState,
    progress: Float,
    size: Int,
    stroke: Int
) {

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier
                    .height(size.dp)
                    .width(size.dp),
                color = lerp(Color.Red, Color.Green, progress),
                strokeWidth = stroke.dp,
            )
            AnimationView(playingState = playingState, progress = progress)

        }
    }
}

@Preview
@Composable
fun PreviewCountDownIndicator() {
    CountDownIndicator(progress = 20f, playingState = PlayingState.Playing, size = 300, stroke = 12)
}