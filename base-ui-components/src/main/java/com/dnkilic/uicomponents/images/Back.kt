package com.dnkilic.uicomponents.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Images.Back: ImageVector
    get() {
        if (_back != null) {
            return _back!!
        }
        _back = Builder(name = "Back", defaultWidth = 280.0.dp, defaultHeight = 395.0.dp,
            viewportWidth = 280.0f, viewportHeight = 395.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFC4C4C4)), stroke = SolidColor(Color(0xFF8E8E8E)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(19.5f, 356.5f)
                    lineTo(260.5f, 356.5f)
                    arcTo(19.0f, 19.0f, 0.0f, false, true, 279.5f, 375.5f)
                    lineTo(279.5f, 375.5f)
                    arcTo(19.0f, 19.0f, 0.0f, false, true, 260.5f, 394.5f)
                    lineTo(19.5f, 394.5f)
                    arcTo(19.0f, 19.0f, 0.0f, false, true, 0.5f, 375.5f)
                    lineTo(0.5f, 375.5f)
                    arcTo(19.0f, 19.0f, 0.0f, false, true, 19.5f, 356.5f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFF2F2F2)), stroke = SolidColor(Color(0xFFD2D2D2)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(20.0f, 0.5f)
                    lineTo(260.0f, 0.5f)
                    arcTo(19.5f, 19.5f, 0.0f, false, true, 279.5f, 20.0f)
                    lineTo(279.5f, 374.0f)
                    arcTo(19.5f, 19.5f, 0.0f, false, true, 260.0f, 393.5f)
                    lineTo(20.0f, 393.5f)
                    arcTo(19.5f, 19.5f, 0.0f, false, true, 0.5f, 374.0f)
                    lineTo(0.5f, 20.0f)
                    arcTo(19.5f, 19.5f, 0.0f, false, true, 20.0f, 0.5f)
                    close()
                }
            }
            group {
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF2F80ED)),
                    strokeLineWidth = 440.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(320.5f, 378.5f)
                    lineTo(-42.0f, 16.0f)
                }
            }
        }
            .build()
        return _back!!
    }

private var _back: ImageVector? = null
