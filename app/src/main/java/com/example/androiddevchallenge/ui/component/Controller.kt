package com.example.androiddevchallenge.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PlayingState

@ExperimentalAnimationApi
@Composable
fun Controller(
    playingState: PlayingState? = PlayingState.Stopped,
    onStartClick: () -> Unit,
    onResumeClick: () -> Unit,
    onPauseClick: () -> Unit,
    onStopClick: () -> Unit,
    onResetClick: () -> Unit
) {

    AnimatedVisibility(visible = playingState == PlayingState.Reset) {
        FloatingActionButton(onClick = { onStartClick() }) {
            Image(painter = painterResource(id = R.drawable.ic_play), contentDescription = "Start")
        }
    }

    AnimatedVisibility(visible = playingState == PlayingState.Paused) {
        FloatingActionButton(onClick = { onResumeClick() }) {
            Image(painter = painterResource(id = R.drawable.ic_play), contentDescription = "Resume")
        }

    }
    Spacer(modifier = Modifier.height(10.dp))

    AnimatedVisibility(visible = playingState == PlayingState.Playing) {
        FloatingActionButton(onClick = { onPauseClick() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_pause),
                contentDescription = "Pause"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }

    Spacer(modifier = Modifier.height(20.dp))

    AnimatedVisibility(visible = playingState != PlayingState.Reset) {
        Button(onClick = { onResetClick() }) {
            Text(text = "RESET")
        }
    }

}