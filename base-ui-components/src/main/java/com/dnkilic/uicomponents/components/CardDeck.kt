package com.dnkilic.uicomponents.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val CARD_COUNT = 52

@Composable
fun CardDeck(
    modifier: Modifier = Modifier,
    cardCount: Int = CARD_COUNT,
    onCardDeckClick: () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize().clickable {
        onCardDeckClick()
    }) {
        (1..cardCount).forEach {
            PlayCard(
                modifier = Modifier.align(Alignment.Center).offset(it.dp, it.dp),
                isClosed = true,
            )
        }
    }
}

@Preview
@Composable
private fun CardDeckPreview() {
    CardDeck()
}