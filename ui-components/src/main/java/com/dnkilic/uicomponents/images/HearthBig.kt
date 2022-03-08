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

public val Images.HearthBig: ImageVector
    get() {
        if (_hearthBig != null) {
            return _hearthBig!!
        }
        _hearthBig = Builder(name = "HearthBig", defaultWidth = 100.0.dp, defaultHeight = 100.0.dp,
                viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFF24E1E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(17.129f, 55.624f)
                curveTo(8.866f, 45.537f, 6.907f, 31.462f, 15.97f, 22.245f)
                curveTo(25.766f, 12.283f, 40.674f, 15.169f, 49.72f, 24.369f)
                curveTo(58.765f, 15.17f, 73.673f, 12.282f, 83.469f, 22.245f)
                curveTo(92.532f, 31.462f, 90.573f, 45.537f, 82.311f, 55.624f)
                curveTo(73.388f, 66.516f, 61.172f, 75.875f, 49.72f, 83.991f)
                curveTo(38.267f, 75.875f, 26.051f, 66.516f, 17.129f, 55.624f)
                close()
            }
        }
        .build()
        return _hearthBig!!
    }

private var _hearthBig: ImageVector? = null
