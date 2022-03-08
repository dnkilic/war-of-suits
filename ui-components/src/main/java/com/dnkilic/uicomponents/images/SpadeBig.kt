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

public val Images.SpadeBig: ImageVector
    get() {
        if (_spadeBig != null) {
            return _spadeBig!!
        }
        _spadeBig = Builder(name = "SpadeBig", defaultWidth = 100.0.dp, defaultHeight = 100.0.dp,
                viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFF2C2C2C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(17.129f, 44.376f)
                curveTo(8.866f, 54.463f, 6.907f, 68.538f, 15.97f, 77.755f)
                curveTo(25.766f, 87.717f, 40.674f, 84.831f, 49.72f, 75.631f)
                curveTo(58.765f, 84.83f, 73.673f, 87.718f, 83.469f, 77.755f)
                curveTo(92.532f, 68.538f, 90.573f, 54.463f, 82.311f, 44.376f)
                curveTo(73.388f, 33.484f, 61.172f, 24.125f, 49.72f, 16.009f)
                curveTo(38.267f, 24.125f, 26.051f, 33.484f, 17.129f, 44.376f)
                close()
            }
        }
        .build()
        return _spadeBig!!
    }

private var _spadeBig: ImageVector? = null
