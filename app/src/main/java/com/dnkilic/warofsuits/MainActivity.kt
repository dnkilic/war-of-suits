package com.dnkilic.warofsuits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.dnkilic.uicomponents.theme.WarOfSuitsTheme
import com.dnkilic.warofsuits.game.ui.GameViewModel
import com.dnkilic.warofsuits.navigation.WarOfSuitsNavigation
import com.dnkilic.warofsuits.player.ui.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val gameViewModel: GameViewModel by viewModels()
    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarOfSuitsTheme {
                val navController = rememberNavController()
                WarOfSuitsNavigation(
                    navController = navController,
                    gameViewModel = gameViewModel,
                    playerViewModel = playerViewModel,
                )
            }
        }
    }
}