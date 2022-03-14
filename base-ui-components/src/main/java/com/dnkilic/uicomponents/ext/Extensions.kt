package com.dnkilic.uicomponents.ext

import androidx.compose.ui.graphics.vector.ImageVector
import com.dnkilic.uicomponents.images.*
import com.dnkilic.warofsuits.data.model.CardType


fun CardType.icon(): ImageVector {
    return when (this) {
        CardType.CLUB -> Images.Club
        CardType.DIAMOND -> Images.Diamond
        CardType.HEARTH -> Images.Heart
        CardType.SPADE -> Images.Spade
    }
}

fun CardType.description(): String {
    return when (this) {
        CardType.CLUB -> "Club"
        CardType.DIAMOND -> "Diamond"
        CardType.HEARTH -> "Hearth"
        CardType.SPADE -> "Spade"
    }
}