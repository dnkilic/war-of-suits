package com.dnkilic.uicomponents.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Images.DiamondSmall: ImageVector
    get() {
        if (_diamondSmall != null) {
            return _diamondSmall!!
        }
        _diamondSmall = Builder(name = "DiamondSmall", defaultWidth = 26.0.dp, defaultHeight =
                26.0.dp, viewportWidth = 26.0f, viewportHeight = 26.0f).apply {
            path(fill = SolidColor(Color(0xFFF24E1E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.972f, 21.6881f)
                lineTo(5.4601f, 12.9366f)
                lineTo(12.972f, 4.1602f)
                lineTo(20.484f, 12.9366f)
                lineTo(12.972f, 21.6881f)
                close()
            }
        }
        .build()
        return _diamondSmall!!
    }

private var _diamondSmall: ImageVector? = null
