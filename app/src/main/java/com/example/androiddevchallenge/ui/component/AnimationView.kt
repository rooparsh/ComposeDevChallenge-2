package com.example.androiddevchallenge.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PlayingState

@Composable
fun AnimationView(playingState: PlayingState, progress: Float) {
    val animationSpec = remember { LottieAnimationSpec.RawRes(R.raw.globe) }

    val animationState = rememberLottieAnimationState(
        autoPlay = playingState == PlayingState.Playing,
        repeatCount = Int.MAX_VALUE
    )
    LottieAnimation(
        spec = animationSpec,
        animationState = animationState,
        modifier = Modifier.size(100.dp)
    )
}

@Preview
@Composable
fun PreviewAnimation() {
    AnimationView(playingState = PlayingState.Playing, progress = 100f)
}