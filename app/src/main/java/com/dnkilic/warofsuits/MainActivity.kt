package com.dnkilic.warofsuits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dnkilic.uicomponents.components.PlayCard
import com.dnkilic.warofsuits.navigation.WarOfSuitsNavigation
import com.dnkilic.uicomponents.theme.WarOfSuitsTheme
import com.dnkilic.warofsuits.game.ui.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarOfSuitsTheme {
                val navController = rememberNavController()
                WarOfSuitsNavigation(
                    navController = navController,
                    gameViewModel = gameViewModel
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WarOfSuitsTheme {
        Column(Modifier.fillMaxSize()) {
            PlayCard()
        }
    }
}