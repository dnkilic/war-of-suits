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

public val Images.ClubBig: ImageVector
    get() {
        if (_clubBig != null) {
            return _clubBig!!
        }
        _clubBig = Builder(name = "ClubBig", defaultWidth = 100.0.dp, defaultHeight = 100.0.dp,
                viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFF2C2C2C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(69.747f, 39.724f)
                lineTo(68.287f, 45.206f)
                lineTo(73.946f, 45.589f)
                curveTo(84.578f, 46.311f, 92.981f, 55.169f, 92.981f, 65.986f)
                curveTo(92.981f, 77.277f, 83.828f, 86.43f, 72.537f, 86.43f)
                curveTo(64.491f, 86.43f, 57.521f, 81.781f, 54.183f, 75.002f)
                lineTo(49.991f, 66.488f)
                lineTo(45.798f, 75.002f)
                curveTo(42.46f, 81.781f, 35.491f, 86.43f, 27.444f, 86.43f)
                curveTo(16.153f, 86.43f, 7.0f, 77.277f, 7.0f, 65.986f)
                curveTo(7.0f, 55.169f, 15.404f, 46.311f, 26.035f, 45.589f)
                lineTo(31.694f, 45.206f)
                lineTo(30.234f, 39.724f)
                curveTo(29.787f, 38.045f, 29.547f, 36.276f, 29.547f, 34.444f)
                curveTo(29.547f, 23.153f, 38.7f, 14.0f, 49.991f, 14.0f)
                curveTo(61.282f, 14.0f, 70.435f, 23.153f, 70.435f, 34.444f)
                curveTo(70.435f, 36.276f, 70.195f, 38.045f, 69.747f, 39.724f)
                close()
            }
        }
        .build()
        return _clubBig!!
    }

private var _clubBig: ImageVector? = null
