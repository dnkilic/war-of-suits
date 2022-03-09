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

public val Images.Spade: ImageVector
    get() {
        if (_spadeSmall != null) {
            return _spadeSmall!!
        }
        _spadeSmall = Builder(name = "SpadeSmall", defaultWidth = 26.0.dp, defaultHeight = 26.0.dp,
                viewportWidth = 26.0f, viewportHeight = 26.0f).apply {
            path(fill = SolidColor(Color(0xFF2C2C2C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.4534f, 11.5377f)
                curveTo(2.3051f, 14.1603f, 1.7958f, 17.8197f, 4.1522f, 20.2162f)
                curveTo(6.6991f, 22.8064f, 10.5753f, 22.0558f, 12.9271f, 19.664f)
                curveTo(15.2789f, 22.0558f, 19.155f, 22.8064f, 21.7019f, 20.2162f)
                curveTo(24.0584f, 17.8197f, 23.5491f, 14.1603f, 21.4007f, 11.5377f)
                curveTo(19.0808f, 8.7057f, 15.9047f, 6.2724f, 12.9271f, 4.1623f)
                curveTo(9.9494f, 6.2724f, 6.7733f, 8.7057f, 4.4534f, 11.5377f)
                close()
            }
        }
        .build()
        return _spadeSmall!!
    }

private var _spadeSmall: ImageVector? = null
