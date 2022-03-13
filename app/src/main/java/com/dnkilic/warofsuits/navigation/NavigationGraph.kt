package com.dnkilic.warofsuits.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dnkilic.player.ui.PlayerScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WarOfSuitsNavigation(
    navController: NavHostController,
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Player
    ) {
        composable(NavigationDestination.Player) {
            PlayerScreen { playerName ->

            }
        }
    }
}

object NavigationDestination {
    const val Player = "player"
    const val Game = "game"
}