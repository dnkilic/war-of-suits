package com.dnkilic.warofsuits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dnkilic.uicomponents.components.PlayCard
import com.dnkilic.warofsuits.navigation.WarOfSuitsNavigation
import com.dnkilic.uicomponents.theme.WarOfSuitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarOfSuitsTheme {
                val navController = rememberNavController()
                WarOfSuitsNavigation(navController = navController)
//                // A surface container using the 'background' color from the theme
//                Column(
//                    Modifier
//                        .fillMaxSize()
//                        .background(color = MaterialTheme.colors.background)) {
//                    CardDeck()
//                }
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