package com.dnkilic.warofsuits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dnkilic.uicomponents.components.CardDeck
import com.dnkilic.uicomponents.components.PlayCard
import com.dnkilic.warofsuits.ui.theme.WarOfSuitsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarOfSuitsTheme {
                // A surface container using the 'background' color from the theme
                Column(Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)) {
                    CardDeck()
                }
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