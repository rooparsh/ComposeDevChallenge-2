package com.example.androiddevchallenge.data

sealed class PlayingState {
    object Stopped : PlayingState()
    object Playing : PlayingState()
    object Paused : PlayingState()
    object Reset : PlayingState()
}
