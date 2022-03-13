package com.dnkilic.warofsuits.game.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun GameScreen(
    userName: String,
) {
    Scaffold(
        content = {
            GameScreenContent(userName = userName)
        }
    )
}

@Composable
private fun GameScreenContent(
    userName: String
) {

}