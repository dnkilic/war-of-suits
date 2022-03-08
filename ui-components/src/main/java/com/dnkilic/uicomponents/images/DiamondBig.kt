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

public val Images.DiamondBig: ImageVector
    get() {
        if (_diamondBig != null) {
            return _diamondBig!!
        }
        _diamondBig = Builder(name = "DiamondBig", defaultWidth = 100.0.dp, defaultHeight =
                100.0.dp, viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFFF24E1E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(49.892f, 83.415f)
                lineTo(21.0f, 49.756f)
                lineTo(49.892f, 16.0f)
                lineTo(78.784f, 49.756f)
                lineTo(49.892f, 83.415f)
                close()
            }
        }
        .build()
        return _diamondBig!!
    }

private var _diamondBig: ImageVector? = null
