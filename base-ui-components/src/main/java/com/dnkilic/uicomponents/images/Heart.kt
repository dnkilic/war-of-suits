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

public val Images.Heart: ImageVector
    get() {
        if (_heartSmall != null) {
            return _heartSmall!!
        }
        _heartSmall = Builder(name = "HeartSmall", defaultWidth = 26.0.dp, defaultHeight = 26.0.dp,
                viewportWidth = 26.0f, viewportHeight = 26.0f).apply {
            path(fill = SolidColor(Color(0xFFF24E1E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.4534f, 14.4623f)
                curveTo(2.3051f, 11.8397f, 1.7958f, 8.1803f, 4.1522f, 5.7838f)
                curveTo(6.6991f, 3.1936f, 10.5753f, 3.9442f, 12.9271f, 6.336f)
                curveTo(15.2789f, 3.9442f, 19.155f, 3.1936f, 21.7019f, 5.7838f)
                curveTo(24.0584f, 8.1803f, 23.5491f, 11.8397f, 21.4007f, 14.4623f)
                curveTo(19.0808f, 17.2943f, 15.9047f, 19.7276f, 12.9271f, 21.8377f)
                curveTo(9.9494f, 19.7276f, 6.7733f, 17.2943f, 4.4534f, 14.4623f)
                close()
            }
        }
        .build()
        return _heartSmall!!
    }

private var _heartSmall: ImageVector? = null
